import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;

//Stores and manages multiple currencies and their exchange rates
class CurrencyStore{
    private HashMap<String, Currency> currency;

    public CurrencyStore(){
        currency= new HashMap<>();

        addCurrency("USD", 1.00);  //United States
        addCurrency("AUD", 0.6197);  //Australia
        addCurrency("CAD", 1.3890);  //Canada
        addCurrency("EUR", 0.9346);  //Europe
        addCurrency("JPY", 143.40);  //Japan
        addCurrency("GBP", 0.7668);  //United Kingdom
        addCurrency("CHF", 0.9252);  //Switzerland
        addCurrency("INR", 85.61);  //India
        addCurrency("CNY", 7.3191);  //China
        addCurrency("BRL", 5.8731);  //Brazil
        addCurrency("ZAR", 18.50);  //South Africa
        addCurrency("MXN", 18.23);  //Mexico
        addCurrency("SAR", 3.75);  //Saudi Arabia
        addCurrency("AED", 3.67);  //United Arab Emirates
        addCurrency("HKD", 7.85);  //Hong Kong
        addCurrency("KRW", 1335.00);  //South Korea
        addCurrency("SGD", 1.36);  //Singapore
        addCurrency("NZD", 0.5979);  //New Zealand
        addCurrency("TRY", 27.31);  //Turkey
        addCurrency("IDR", 15890.00);  //Indonesia
        addCurrency("THB", 34.68);  //Thailand
        addCurrency("RUB", 96.45);  //Russia
        addCurrency("MYR", 4.70);  //Malaysia
        addCurrency("VND", 23750.00);  //Vietnam
        addCurrency("PKR", 285.00);  //Pakistan
        addCurrency("COP", 4753.55);  //Colombia
        addCurrency("CLP", 796.02);  //Chile
        addCurrency("PHP", 56.02);  //Philippines
        addCurrency("PLN", 4.32);  //Poland
        addCurrency("HUF", 366.02);  //Hungary
        addCurrency("CZK", 22.62);  //Czech Republic
        addCurrency("SEK", 10.87);  //Sweden
        addCurrency("NOK", 10.64);  //Norway
        addCurrency("DKK", 6.94);  //Denmark
        addCurrency("KWD", 0.3077);  //Kuwait
        addCurrency("BHD", 0.377);  //Bahrain
        addCurrency("OMR", 0.3845);  //Oman
        addCurrency("QAR", 3.64);  //Qatar
    }

    //Adds a currency
    public void addCurrency(String name, double rate){
        currency.put(name, new Currency(name, rate));
    }

    //Removes a currency
    public void removeCurrency(String name){
        if(currency.containsKey(name)){
            currency.remove(name);
            System.out.println("Currency " + name + " removed.");
        } 
        else{
            System.out.println("Currency not found.");
        }
    }

    //Validates a currency
    public boolean isValid(String name){
        return currency.containsKey(name);
    }

    //Returns the exchange rate of the given currency
    public double getRate(String name){
        return currency.get(name).rate;
    }

    //Displays all currencies with their rates
    public void displayAvailableCurrencies(){
        System.out.println("Available Currencies:");
        for(String name : currency.keySet()){
            System.out.printf("%s (Rate: %.2f)\n", name, getRate(name));
        }
    }

    //Converts amount from one currency to another
    public BigDecimal convert(String from, String to, double amount){
        double conversion= amount * getRate(to) / getRate(from);
        return BigDecimal.valueOf(conversion).setScale(2, RoundingMode.HALF_UP);
    }
}