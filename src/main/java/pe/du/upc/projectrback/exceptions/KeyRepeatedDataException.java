package pe.du.upc.projectrback.exceptions;

public class KeyRepeatedDataException extends RuntimeException{
    public KeyRepeatedDataException() {
        super("Clave repetida detectada.");
    }

    public KeyRepeatedDataException(String message) {
        super(message);
    }

    // Constructor adicional para manejar excepciones con causa
    public KeyRepeatedDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
