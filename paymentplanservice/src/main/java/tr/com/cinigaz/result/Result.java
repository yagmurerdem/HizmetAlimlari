package tr.com.cinigaz.result;

public class Result {

    private String message;
    private boolean success;


    public Result(boolean success) {
        this.success = success;
    }


    public Result(String message, boolean success) {
        this(success);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
