package cn.gx.hj.lib;

public class HState<Data> {
    @HStatus
    private int status = HStatus.IDLE;
    private int code;
    private long total;
    private long current;

    private String message;
    private String detail;
    private Throwable throwable;
    private Data data;

    public static <Data> HState<Data> get(){
        return new HState<Data>();
    }


    public <T extends HState> T copy(HState src){
        if(src != null) {
            status = src.status;
            code = src.code;
            total = src.total;
            current = src.current;
            message = src.message;
            detail = src.detail;
            throwable = src.throwable;
        }
        return getThis();
    }

    protected <T extends HState> T getThis(){
        return (T) this;
    }


    @HStatus
    public int status(){
        return status;
    }

    public <T extends HState> T status(@HStatus int status){
        this.status = status;
        return getThis();
    }

    public int code(){
        return code;
    }

    public <T extends HState> T code(int code){
        this.code = code;
        return getThis();
    }


    public long total(){
        return total;
    }

    public <T extends HState> T total(long total){
        this.total = total;
        return getThis();
    }

    public long current(){
        return current;
    }

    public <T extends HState> T current(long current){
        this.current = current;
        return getThis();
    }

    public String message(){
        return message;
    }

    public <T extends HState> T message(String message){
        this.message = message;
        return getThis();
    }

    public String detail(){
        return detail;
    }

    public <T extends HState> T detail(String detail){
        this.detail = detail;
        return getThis();
    }

    public Throwable throwable(){
        return throwable;
    }

    public <T extends HState> T throwable(Throwable throwable){
        this.throwable = throwable;
        return getThis();
    }

    public Data data(){
        return data;
    }

    public <T extends HState> T data(Data data){
        this.data = data;
        return getThis();
    }

    public <T extends HState> T finish(int errorCode){
        code(errorCode);
        return status(HStatus.FINISHED);
    }

    public <T extends HState> T finish(int errorCode, Throwable throwable){
        code(errorCode).throwable(throwable);
        return status(HStatus.FINISHED);
    }

    public <T extends HState> T finish(){
        return status(HStatus.FINISHED);
    }

    public <T extends HState> T finish(Data data){
        data(data);
        return status(HStatus.FINISHED);
    }

    public void dispatch(HStateCallback<Data> callback){
        if(callback != null){
            callback.onDispatchState(this);
        }
    }
}
