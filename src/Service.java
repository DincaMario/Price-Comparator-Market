import java.util.*;

public class Service {
    Parser ProductParser = new Parser();
    List<Product> products = ProductParser.parseAllCSV();
    Scanner sc = new Scanner(System.in);
    List<Product> cos = new ArrayList<>();

    void ShowProducts() {

        /// A list where we store every product based on a certain criteria
        List<Product> tempList = new ArrayList<>();
        System.out.println("What to search by: ");
        System.out.println("0. Exit");
        System.out.println("1. By brand");
        System.out.println("2. By name");
        System.out.println("3. By price");
        System.out.println("4. By Store");
        System.out.println("5. Show all");
        System.out.print("Option: ");

        int option = sc.nextInt();
        sc.nextLine();

        /// Search based on the brand of a product
        if (option == 1) {
            System.out.print("Brand: ");
            String brand = sc.nextLine().toLowerCase();
            for (Product p : products) {
                if (p.getBrand().toLowerCase().contains(brand)) {
                    tempList.add(p);
                }
            }
        } else if (option == 2) {
            ///  Search based on  the name of a product
            System.out.print("Name: ");
            String name = sc.nextLine().toLowerCase();
            for (Product p : products) {
                if (p.getName().toLowerCase().contains(name)) {
                    tempList.add(p);
                }
            }
        } else if (option == 3) {
            /// Search within a ceratin price range
            System.out.print("Minimum price: ");
            float minPrice = sc.nextFloat();
            System.out.print("Maximum price: ");
            float maxPrice = sc.nextFloat();
            for (Product p : products) {
                if (p.getPrice() <= maxPrice && p.getPrice() >= minPrice) {
                    tempList.add(p);
                }
            }
        } else if (option == 4) {
            /// Search within a store
            System.out.print("Store: ");
            String store = sc.nextLine().toLowerCase();
            for (Product p : products) {
                if (p.getStore().toLowerCase().contains(store)) {
                    tempList.add(p);
                }
            }
        } else if (option == 5) {
            ///  We get all the products, from every store, every price range, etc.
            tempList.addAll(products);
        } else if (option == 0) {
            System.out.println("Exited");
            return;
        } else {

            return;
        }
        System.out.println();
        System.out.println("Products:");
        int index = 1;
        /// We print out every product that we got based on the criteria above
        for (Product p : tempList) {
            System.out.print("ID: " + index + " | ");
            System.out.print(p.show());
            System.out.println();
            index++;
        }
        if (index == 1) {
            return;
        }

        /// Afterwards, we dedice what we want to do with it

        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("0. Exit");
        System.out.println("1. Add Item to the basket");
        System.out.println("2. See more details about a item");
        System.out.println("Option: ");

        int second_option = sc.nextInt();

        while (second_option != 0) {

            /// We select the item that we want to add to our basket by its ID
            if (second_option == 1) {
                System.out.print("Enter the ID of the item: ");
                int id = sc.nextInt();
                if (id >= 1 && id <= tempList.size()) {
                    cos.add(tempList.get(id - 1));
                    System.out.println("Item added to basket.");
                } else {
                    System.out.println("Invalid ID.");
                }
            } else if (second_option == 2) {

                /// We print out more information about the product we selected based on it's ID
                System.out.print("Enter the ID of the item: ");
                int id = sc.nextInt();
                if (id >= 1 && id <= tempList.size()) {
                    System.out.println(tempList.get(id - 1).showDetails());
                } else {
                    System.out.println("Invalid ID.");
                }
            }

            System.out.println("What do you want to do?");
            System.out.println("0. Exit");
            System.out.println("1. Add Item to the basket");
            System.out.println("2. See more details about a item");
            System.out.println("Option: ");
            second_option = sc.nextInt();
        }
    }

    /// We print the items, in descending order, based on their Discount
    void ShowBestDiscounts(){
        List<Product> discountedProducts = new ArrayList<>();

        ///  We select the products that have discounts
        for( Product p : products){
            if( p.getDiscount() > 0){
                discountedProducts.add(p);
            }
        }

        if( discountedProducts.isEmpty()){
            System.out.println("There are no products with discounts");
            return;
        }

        discountedProducts.sort((p1, p2) -> Integer.compare(p2.getDiscount(), p1.getDiscount()) );

        for( int i = 0; i < discountedProducts.size(); i++){
            System.out.println((i + 1) + " | " + discountedProducts.get(i));
        }
    }

    ///  Print out the Items that went on sale in the last 24 hours
    void showNewDiscounts(){
        Date now = new Date();
        /// The number of ms in a day
        long oneDayMiliseconds = 24 * 60 * 60 * 1000;
        boolean found = false;

        for( Product p: products){
            Date start = p.getStartDiscount();
            if( start != null){
                long timeDiff = now.getTime() - start.getTime();
                if( timeDiff >= 0 && timeDiff <= oneDayMiliseconds){
                    System.out.println(p);
                    found = true;
                }
            }
        }

        if( !found ){
            System.out.println("No discounts were added in the past 24 hours");
        }
    }

    void basketOption(){
        System.out.println("What do you want to do: ");
        System.out.println("0. Exit");
        System.out.println("1. Show items in basket");
        System.out.println("2. Remove an item from basket");
        System.out.println("3. Remove all items from basket");
        System.out.println("Option: ");

        int option = sc.nextInt();

        while ( option != 0)
        {
            switch (option){
                case 1:{
                    if( cos.isEmpty()){
                        System.out.println("Basket is empty");
                    }
                    else{
                        int index = 1;
                        for( Product p : cos )
                        {
                            System.out.print("ID: " + index + " | ");
                            System.out.print(p.show());
                            System.out.println();
                            index++;
                        }
                    }
                    break;
                }
                case 2:{
                    /// We remove an item from the basket, based on its ID;
                    if( cos.isEmpty()){
                        System.out.println("Basket is already empty");
                    }
                    else {
                        System.out.println("The ID of the item to remove: ");
                        int id = sc.nextInt();

                        try{
                            cos.remove(id - 1);
                            System.out.println("Item removed");
                        }catch (Exception e){
                            System.out.println("Item with ID: " + id + " was not found");
                        }

                    }
                    break;
                }
                /// We clear the whole
                case 3:{
                    cos.clear();
                    System.out.println("All items removed from basket");
                    break;
                }
                default:
                    System.out.println("Invalid Option");
            }
            System.out.println("What do you want to do: ");
            System.out.println("0. Exit");
            System.out.println("1. Show items in basket");
            System.out.println("2. Remove an item from basket");
            System.out.println("3. Remove all items from basket");
            System.out.println("Option: ");
            option = sc.nextInt();
        }
    }

}
