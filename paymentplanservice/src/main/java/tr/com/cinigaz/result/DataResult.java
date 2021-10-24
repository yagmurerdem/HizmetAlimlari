package tr.com.cinigaz.result;

public class DataResult<T> extends Result {
    private T data;

    public DataResult(boolean success, T data) {
        super(success);
        this.data = data;
    }

    public DataResult(String message, boolean success, T data) {
        super(message, success);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
