import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();


        afis();
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        while( option != 0 )
        {
            if( option == 1 )
            {
                service.ShowProducts();
            }
            if( option == 2)
            {
                service.ShowBestDiscounts();
            }
            if( option == 3)
            {
                service.showNewDiscounts();
            }
            if( option == 4 )
            {
                service.basketOption();
            }
            else{
                System.out.println("Invalid Option");
            }
            afis();
            option = sc.nextInt();
        }
    }

    public static void afis(){
        System.out.println("What do you want to do: ");
        System.out.println("0. Exit");
        System.out.println("1. Search for Products");
        System.out.println("2. Show the Best Discounts");
        System.out.println("3. Show the new Discounts");
        System.out.println("4. Show Basket options");
        System.out.println("Option: ");
    }
}