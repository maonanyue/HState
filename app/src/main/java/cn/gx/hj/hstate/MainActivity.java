package cn.gx.hj.hstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.gx.hj.lib.HState;
import cn.gx.hj.lib.HStateCallback;
import cn.gx.hj.lib.HStatus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run() {
                HState.<Long>get().status(HStatus.STARTED).dispatch(hStateCallback);
                for(int i=0; i < 10; i++){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    HState.<Long>get().status(HStatus.LOADING)
                            .total(10).current(i).dispatch(hStateCallback);
                }

                HState.<Long>get().finish(10).dispatch(hStateCallback);
            }
        }.start();

    }

    HStateCallback<Long> hStateCallback = new HStateCallback<Long>(){
        @Override
        public void onDispatchState(final HState<Long> state) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,
                            "state: status="+state.status()
                                    +", total="+state.total()
                                    +", current="+state.current()
                                    +", data="+state.data(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}
