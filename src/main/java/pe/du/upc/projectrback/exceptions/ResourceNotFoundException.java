package pe.du.upc.projectrback.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("Recurso no encontrado."); // Mensaje predeterminado
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructor adicional para manejar excepciones con causa
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }



}
