package com.tistory.puzzleleaf.jobscheduler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cmtyx on 2017-07-12.
 */

public class ToastTask {
    public static final String ACTION_TOAST  = "toast_tag";


    public static void executeTask(Context context, String action){
        if(ACTION_TOAST.equals(action)) {
            ToastAction(context);
        }
    }

    private static void ToastAction(Context context){
        //@TODO 데이터를 처리한다.
        context.startService(new Intent(context,ToastService.class));
    }
}
