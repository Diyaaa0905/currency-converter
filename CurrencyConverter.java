//java.text.NumberFormat
//java.util.Locale

//java.net.HttpURLConnection
//java.net.URL
//java.io.InputStreamReader
//java.io.BufferedReader

//javafx--> GUI

import java.math.BigDecimal;
import java.util.*;

class Currency{
    String name; // source currency name
    double rate; // source-currency-rate to final-currency(=USD)

    public Currency(String name, double rate){
        this.name= name;
        this.rate= rate;
    }
}

class CurrencyStore{
    private HashMap<String, Currency> currency;

    public void addCurrency(String name, double rate){
        currency.put(name, new Currency(name, rate));
    }

    public CurrencyStore(){
        currency= new HashMap<>();
        addCurrency("USD", 1.00);
        addCurrency("INR", 85.61);
        addCurrency("EUR", 0.93);
        addCurrency("JPY", 150.60);
    }

    public boolean isValid(String name){
        return currency.containsKey(name);
    }

    public double getRate(String name){
        return currency.get(name).rate;
    }

    public BigDecimal convert(String from, String to, double amount){
        return BigDecimal.valueOf(amount * getRate(to) / getRate(from));
    }
}

public class CurrencyConverter{
    public static void main(String[] args){
        Scanner scan= new Scanner(System.in);
        CurrencyStore storage= new CurrencyStore();

        System.out.println("Enter source currency: ");
        String src= scan.nextLine().toUpperCase();

        System.out.println("Enter final currency: ");
        String fin= scan.nextLine().toUpperCase();

        if(!storage.isValid(src) || !storage.isValid(fin)){
            System.out.println("Invalid currency. Try again.");
        }
        else{
            System.out.print("Enter amount: ");
            double amt= scan.nextDouble();
            BigDecimal result= storage.convert(src, fin, amt);
            System.out.printf("Converted Amount: %.2f %s\n", result, fin);
        }
        scan.close();
    }
}
