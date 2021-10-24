package tr.com.cinigaz.result;

public class Result {
    private boolean success;
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(String message, boolean success) {
        this(success);//bu classın success parametreli constructorını çağırır.
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }
}
