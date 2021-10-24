package tr.com.cinigaz.result;
//işlem sonucu başarılı
public class SuccessResult extends Result{

    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(message,true);
    }
}
