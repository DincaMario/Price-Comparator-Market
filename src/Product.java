import java.util.Date;

public class Product {


    /// Attributes of a Product
   private int global_id;
   private static int next_id = 0;
   private int id;
   private String name;
   private String category;
   private String brand;
   private float quantity;
   private String unit;
   private float price;
   private String currency;
   private float ratio;
   private String store;
   private Date start_discount;
   private Date end_discount;
   private int discount = 0;
   private float percentageChange;

   Product(int id, String name, String category, String brand, float quantity, String unit, float price, String currency, String store)
   {
        this.global_id = next_id++;
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.currency = currency;
        ///  Price per quantity
        this.ratio = price / quantity;
        this.store = store;

   }

   String getName(){
       return this.name;
   }

   float getPercentageChange(){
       return this.percentageChange;
   }

   void setPercentageChange( float per){
       this.percentageChange = per;
   }

   String getBrand(){
       return this.brand;
   }

   float getPrice(){
       return this.price;
   }

   void setDiscount( int nr ){
       this.discount = nr;
   }

   int getId(){
       return this.id;
   }

   String getStore(){
       return this.store;
   }

   void setStartDate(Date data)
   {
       this.start_discount = data;
   }

   void setPrice( float price)
   {
       this.price = price;
   }

   void setRatio( float ratio ){
       this.ratio = ratio;
   }


    void setEndDate(Date data)
    {
        this.end_discount = data;
    }

    /// Show elements without their ID;
    /// so that we can assign an ID when using ArrayList
    String show(){
        if( discount > 0)
            return  this.name + " | " + this.brand + " | " + this.price + " " + this.currency +  " | " + this.ratio + " | " + this.store + " | Discount: " + this.discount + "% from: " + this.start_discount + " " + this.end_discount;
        return  this.name + " | " + this.brand + " | " + this.price + " " + this.currency +  " | " + this.ratio + " | " + this.store;
    }

    public int getDiscount() {
        return this.discount;
    }

    public Date getStartDiscount() {
        return this.start_discount;
    }

    /// A detailed look at every attribute of a product
    /// Including their price per quantity ratio and how much % price increase/decrease since last week
    String showDetails() {
        return  "Product Details:\n" +
                "Global ID: " + this.global_id + "\n" +
                "Local ID: " + this.id + "\n" +
                "Name: " + this.name + "\n" +
                "Category: " + this.category + "\n" +
                "Brand: " + this.brand + "\n" +
                "Quantity: " + this.quantity + " " + this.unit + "\n" +
                "Price: " + this.price + " " + this.currency + "\n" +
                "Ratio (price per quantity): " + this.ratio + "\n" +
                "Price change since last week: " + this.percentageChange + " % \n" +
                "Store: " + this.store + "\n" +
                "Discount: " + (this.discount > 0 ? (this.discount + "% from " + this.start_discount + " to " + this.end_discount) : "No discount") + "\n";
    }


    @Override
    public String toString() {
       if( discount > 0)
        return "Produsul: " + this.global_id + " | " + this.name + " | " + this.brand + " | " + this.price + " " + this.currency +  " | " + this.ratio + " | " + this.store + " | Discount: " + this.discount + "% from: " + this.start_discount + " " + this.end_discount;
       return "Produsul: " + this.global_id + " | " + this.name + " | " + this.brand + " | " + this.price + " " + this.currency +  " | " + this.ratio + " | " + this.store;
   }
}
