package com.tistory.puzzleleaf.jobscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ToastBroadCastReceiver tbcr;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbcr = new ToastBroadCastReceiver();
        filter = new IntentFilter("toast");
        //BroadCastReceiver를 통해서 토스트가 나타나게 함

        ToastUtility.scheduler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(tbcr,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(tbcr);
    }

    private class ToastBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "토스트!!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
