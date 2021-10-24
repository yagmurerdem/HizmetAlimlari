package tr.com.cinigaz.result;

public class Result {
    private boolean success;//işlem başarılı mı
    private String message; //işlem başarılı,başarısız karşılığındaki mesaj(işlem sonucunda çıkan mesaj bilgisi)

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public boolean isSuccess(){
        return this.success;
    }


    public String getMessage(){
        return this.message;
    }
}