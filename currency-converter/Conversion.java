import java.math.BigDecimal;

//Stores a single currency conversion record
class Conversion{
    private String fromCurrency, toCurrency;
    private double amount;
    private BigDecimal result;
    private double exchangeRate;

    //Constructs a new Conversion object
    public Conversion(String fromCurrency, String toCurrency, double amount, BigDecimal result, double exchangeRate){
        this.fromCurrency= fromCurrency;
        this.toCurrency= toCurrency;
        this.amount= amount;
        this.result= result;
        this.exchangeRate= exchangeRate;
    }

    //Returns conversion
    public String displayConversion(){
        return String.format("%.2f %s = %.2f %s (Exchange Rate: %.2f)",
                amount, fromCurrency, result.doubleValue(), toCurrency, exchangeRate);
    }

    @Override
    public String toString(){
        return displayConversion();
    }
}