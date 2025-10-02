import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

//Manages conversion history and provides undo/redo functionality
class ConversionManager{
    Stack<Conversion> conversionStack= new Stack<>(); //Stack for undo operations
    Stack<Conversion> redoStack= new Stack<>(); //Stack for redo operations
    ArrayList<Conversion> conversionList= new ArrayList<>(); //Stores the conversion history

    //Adds a conversion to the history. Clears the redo stack upon new entry
    void addConversion(Conversion conversion){
        conversionStack.push(conversion);
        conversionList.add(conversion);
        redoStack.clear();
        System.out.println("Conversion added: " + conversion);
    }

    //Undoes the last conversion operation
    void undoConversion(){
        if(conversionStack.isEmpty()){
            System.out.println("No conversions to undo.");
        }
        else{
            Conversion undoneConversion= conversionStack.pop();
            redoStack.push(undoneConversion);
            conversionList.remove(undoneConversion);
            System.out.println("Undoing conversion: " + undoneConversion);
        }
    }

    //Redoes the last conversion operation
    void redoConversion(){
        if(redoStack.isEmpty()){
            System.out.println("No conversions to redo.");
        }
        else{
            Conversion redoneConversion= redoStack.pop();
            conversionStack.push(redoneConversion);
            conversionList.add(redoneConversion);
            System.out.println("Redoing conversion: " + redoneConversion);
        }
    }

    //Displays all conversions
    void displayAllConversions(){
        if(conversionList.isEmpty()){
            System.out.println("No conversions have been made yet.");
        }
        else{
            System.out.println("All Conversions:");
            for(Conversion conversion : conversionList){
                System.out.println(conversion);
            }
        }
    }
}