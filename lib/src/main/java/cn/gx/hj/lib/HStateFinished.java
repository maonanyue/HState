package cn.gx.hj.lib;

public class HStateFinished<Data> implements HStateCallback<Data> {
    private HStateCallback<Data> finished;
    @Override
    public void onDispatchState(HState<Data> state) {
        if(state.status() == HStatus.FINISHED){
            if(finished != null){
                finished.onDispatchState(state);
            }
        }
    }

    public HStateFinished<Data> finished(HStateCallback<Data> finished){
        this.finished = finished;
        return this;
    }
}
