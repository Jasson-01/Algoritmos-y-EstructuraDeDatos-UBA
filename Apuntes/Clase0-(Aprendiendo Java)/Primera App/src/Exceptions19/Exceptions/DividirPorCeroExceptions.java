// package Exceptions;

public class DividirPorCeroExceptions extends Exception {
    
    @Override
    public String getMessage(){
        return "No se puede dividir por cero";
    }
        
}
