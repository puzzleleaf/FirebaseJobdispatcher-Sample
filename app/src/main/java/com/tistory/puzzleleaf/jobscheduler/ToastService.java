package com.tistory.puzzleleaf.jobscheduler;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cmtyx on 2017-07-18.
 */

public class ToastService extends IntentService {
    public ToastService() {
        super("ToastService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("qwe","토스트!");
        sendBroadcast(new Intent("toast"));
    }
}
