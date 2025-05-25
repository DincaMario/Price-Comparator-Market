import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private static final String DATA_FOLDER = "src/Data";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Product> parseAllCSV(){
        List<Product> products = new ArrayList<>();
        Map<String, Product> productMap = new HashMap<>(); // key is store + id

        File folder = new File(DATA_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        if( files == null)
            return products;

        /// We go through all the files that are not discounts
        for( File file : files){
            String fileName = file.getName();
            if(fileName.contains("discount"))
                continue;

            try( CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();){
                List<String[]> rows = reader.readAll();
                rows.remove(0);

                for( String[] row: rows){
                    int id = Integer.parseInt(row[0].substring(1)); // P001 → 1
                    String name = row[1];
                    String category = row[2];
                    String brand = row[3];
                    float qty = Float.parseFloat(row[4]);
                    String unit = row[5];
                    float price = Float.parseFloat(row[6]);
                    String currency = row[7];
                    String store = getStoreName(fileName);

                    String key = store + "_" + row[0]; // ex: "Lidl_P001"
                    ///  We check to see if a product is already in the list
                    ///  If yes, we change his price and ratio
                    if (productMap.containsKey(key)) {

                        Product existing = productMap.get(key);
                        float old_price = existing.getPrice();
                        float percentageChange = ((price - old_price) / old_price) * 100;
                        existing.setPercentageChange(percentageChange);
                        existing.setPrice(price);
                        existing.setRatio(price / qty);
                    } else {

                        Product product = new Product(id, name, category, brand, qty, unit, price, currency, store);
                        productMap.put(key, product);
                        products.add(product);
                    }
                }

            }catch (Exception e){
                System.err.println("Eroare la citirea fisierului: " + fileName);
                e.printStackTrace();
            }
        }

        // Now we go through the discount files to update the products
        for( File file: files){
            String fileName = file.getName();
            if( !fileName.contains("discount"))
                continue;

            try( CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();){
                List<String[]> rows = reader.readAll();
                rows.remove(0);

                for( String[] row: rows){
                    String productId = row[0];
                    String store = getStoreName(fileName);
                    String key = store + "_" + productId;

                    if( productMap.containsKey(key)){
                        Product product = productMap.get(key);
                        product.setStartDate(DATE_FORMAT.parse(row[6]));
                        product.setEndDate(DATE_FORMAT.parse(row[7]));
                        product.setDiscount(Integer.parseInt(row[8]));
                    }
                }
            }catch (Exception e){
                System.err.println("Eroare la citirea fisierului: " + fileName);
                e.printStackTrace();
            }
        }
        return products;
    }

    private static String getStoreName(String fileName) {
        if (fileName.startsWith("lidl")) return "Lidl";
        if (fileName.startsWith("kaufland")) return "Kaufland";
        if (fileName.startsWith("profi")) return "Profi";
        return "Unknown";
    }

}
