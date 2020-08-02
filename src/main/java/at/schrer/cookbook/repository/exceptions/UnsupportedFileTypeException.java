package at.schrer.cookbook.repository.exceptions;

public class UnsupportedFileTypeException extends Exception {

    public UnsupportedFileTypeException(String message) {
        super(message);
    }

    public UnsupportedFileTypeException(String message, Throwable e){
        super(message,e);
    }
}
