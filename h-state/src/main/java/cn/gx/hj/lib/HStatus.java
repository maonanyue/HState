package cn.gx.hj.lib;


import android.support.annotation.IntDef;

@IntDef({HStatus.IDLE, HStatus.STARTED, HStatus.LOADING, HStatus.FINISHED})
public @interface HStatus {
    int IDLE = 1;
    int STARTED = 2;
    int LOADING = 3;
    int FINISHED = 4;
}
