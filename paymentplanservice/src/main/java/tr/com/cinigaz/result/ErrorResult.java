package tr.com.cinigaz.result;

public class ErrorResult extends Result{
    public ErrorResult() {
        super(false);
    }

    public ErrorResult(String message) {
        super(message,false);
    }
}
