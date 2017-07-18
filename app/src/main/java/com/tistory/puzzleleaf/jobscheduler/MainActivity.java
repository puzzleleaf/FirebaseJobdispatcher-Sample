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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastBroadCastReceiver tbcr = new ToastBroadCastReceiver();
        IntentFilter filter = new IntentFilter("toast");
        //BroadCastReceiver를 통해서 토스트가 나타나게 함
        registerReceiver(tbcr,filter);
        ToastUtility.scheduleChargingReminder(this);

    }

    private class ToastBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "토스트!!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
