package ca.wchang.openMoives.exception;

public class MovieException extends RuntimeException {

    private String message;

    public MovieException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}