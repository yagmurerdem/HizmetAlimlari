package tr.com.cinigaz.result;

public class DataResult<T> extends Result {

    private T data;

    public DataResult(boolean success,T data,String message) {
        super(message,success);
        this.data=data;
    }

    public DataResult(T data, boolean success) {
        super(success);
        this.data=data;
    }

    public T getData(){
        return this.data;
    }
}
