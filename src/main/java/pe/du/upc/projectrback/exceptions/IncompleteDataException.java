package pe.du.upc.projectrback.exceptions;

public class IncompleteDataException extends RuntimeException{

    //public IncompleteDataException(String message) { super(message); }
    public IncompleteDataException(String message) {
        super(message);
    }

    // Constructor adicional para mayor flexibilidad
    public IncompleteDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
