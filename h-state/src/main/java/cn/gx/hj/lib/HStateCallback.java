package cn.gx.hj.lib;

public interface HStateCallback<Data> {
    void onDispatchState(HState<Data> state);
}
