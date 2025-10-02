import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

//MAIN CLASS
public class Main{
    public static void main(String[] args){
        Scanner scan= new Scanner(System.in);
        CurrencyStore storage= new CurrencyStore(); //Available currencies
        ConversionManager manager= new ConversionManager(); //Manager for conversion history and undo/redo

        while(true){
            //Display menu options
            System.out.println("\nChoose an action:");
            System.out.println("1. Convert currency");
            System.out.println("2. Undo last action");
            System.out.println("3. Redo last action");
            System.out.println("4. View conversion history");
            System.out.println("5. View available currencies");
            System.out.println("6. Add a new currency");
            System.out.println("7. Remove a currency");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            
            int option;

            //Validate menu option input
            if(scan.hasNextInt()){
                option= scan.nextInt();
                scan.nextLine(); //Consume newline
            }
            else{
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); //Clear invalid input
                continue;
            }

            switch(option){
                case 1:
                    System.out.print("Enter source currency (e.g., USD, INR): ");
                    String src= scan.nextLine().toUpperCase();

                    //Validate source currency - if missing, offer to add it
                    while(!storage.isValid(src)){
                        System.out.println("Source currency not found: " + src);
                        System.out.print("Would you like to add it? (Y/N): ");
                        String choice= scan.nextLine().toUpperCase();
                        if(choice.equals("Y")){
                            System.out.print("Enter exchange rate relative to USD: ");
                            double rate;
                            if(scan.hasNextDouble()){
                                rate= scan.nextDouble();
                                scan.nextLine();
                                storage.addCurrency(src, rate);
                            }
                            else{
                                System.out.println("Invalid rate. Skipping addition.");
                                scan.nextLine();
                            }
                        } 
                        else{
                            System.out.print("Re-enter source currency: ");
                            src= scan.nextLine().toUpperCase();
                        }
                    }

                    System.out.print("Enter target currency: ");
                    String fin= scan.nextLine().toUpperCase();

                    //Validate final currency- if missing, offer to add it
                    while(!storage.isValid(fin)){
                        System.out.println("Target currency not found: " + fin);
                        System.out.print("Would you like to add it? (Y/N): ");
                        String choice= scan.nextLine().toUpperCase();
                        if(choice.equals("Y")){
                            System.out.print("Enter exchange rate relative to USD: ");
                            double rate;
                            if(scan.hasNextDouble()){
                                rate= scan.nextDouble();
                                scan.nextLine();
                                storage.addCurrency(fin, rate);
                            } 
                            else{
                                System.out.println("Invalid rate. Skipping addition.");
                                scan.nextLine();
                            }
                        } 
                        else{
                            System.out.print("Re-enter target currency: ");
                            fin= scan.nextLine().toUpperCase();
                        }
                    }

                    //Prevent converting the same currency
                    if(src.equals(fin)){
                        System.out.println("Source and target currencies cannot be the same.");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    double amt= 0;
                    boolean validAmount= false;

                    //Validate amount
                    while(!validAmount){
                        if(scan.hasNextDouble()){
                            amt= scan.nextDouble();
                            scan.nextLine(); // Consume newline
                            validAmount= true;
                        }
                        else{
                            System.out.println("Invalid input. Please enter a valid number.");
                            scan.nextLine(); // Clear invalid input
                        }
                    }

                    //Conversion
                    BigDecimal result= storage.convert(src, fin, amt);
                    double exRate= storage.getRate(fin) / storage.getRate(src);
                    Conversion conversion= new Conversion(src, fin, amt, result, exRate);
                    manager.addConversion(conversion);

                    // AFTER conversion, offer to view the reverse conversion
                    System.out.print("Would you like to switch the conversion direction? (Y/N): ");
                    String swap= scan.nextLine().toUpperCase();
                    if(swap.equals("Y")){
                        BigDecimal switchedResult= storage.convert(fin, src, amt);
                        double switchedExRate= storage.getRate(src) / storage.getRate(fin);
                        Conversion switchedConversion= new Conversion(fin, src, amt, switchedResult, switchedExRate);
                        manager.addConversion(switchedConversion);
                        System.out.println("Switched conversion result: " + switchedConversion);
                    }
                    break;

                case 2:
                    manager.undoConversion();
                    break;

                case 3:
                    manager.redoConversion();
                    break;

                case 4:
                    manager.displayAllConversions();
                    break;

                case 5:
                    storage.displayAvailableCurrencies();
                    break;

                case 6:
                    System.out.print("Enter currency name to add: ");
                    String newName= scan.nextLine().toUpperCase();
                    if(storage.isValid(newName)){
                        System.out.println("Currency already exists.");
                    } 
                    else{
                        System.out.print("Enter exchange rate relative to USD: ");
                        if(scan.hasNextDouble()){
                            double newRate= scan.nextDouble();
                            scan.nextLine();
                            storage.addCurrency(newName, newRate);
                            System.out.println("Currency added successfully.");
                        } 
                        else{
                            System.out.println("Invalid rate. Currency not added.");
                            scan.nextLine();
                        }
                    }
                    break;

                case 7:
                    System.out.print("Enter currency name to remove: ");
                    String removeName= scan.nextLine().toUpperCase();
                    storage.removeCurrency(removeName);
                    break;

                case 8:
                    System.out.println("Exiting program...");
                    scan.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
