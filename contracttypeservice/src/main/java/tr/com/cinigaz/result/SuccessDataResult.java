package tr.com.cinigaz.result;
//işlemin başarılı olduğu noktadaki data resultını döndürcez.
public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data, String message) {
        super(true, data, message);
    }

    public SuccessDataResult(T data) {
        super(data,true);
    }

    public SuccessDataResult(String message) {
        super(true,null,message);
    }

    public SuccessDataResult() {
        super(null,true);
    }
}
