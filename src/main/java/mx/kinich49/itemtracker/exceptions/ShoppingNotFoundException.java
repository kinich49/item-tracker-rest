package mx.kinich49.itemtracker.exceptions;

public class ShoppingNotFoundException extends Exception {

    public ShoppingNotFoundException(long id){
        super("Shopping with id " + id + " not found");
    }
}