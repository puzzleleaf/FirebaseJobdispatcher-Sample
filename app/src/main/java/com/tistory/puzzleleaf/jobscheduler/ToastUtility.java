package com.tistory.puzzleleaf.jobscheduler;


import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by cmtyx on 2017-07-13.
 */

public class ToastUtility {
    private static final String REMINDER_JOB_TAG = "toast_tag";
    private static boolean sInitialized = false;

    synchronized public static void scheduler(final Context context){
        if(sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(ToastFirebaseJobService.class)
                .setTag(REMINDER_JOB_TAG)
                //제약조건을 명시한다.
                //네트워크 제약조건, 배터리 조건 등등
                // only run on an unmetered network
                //Constraint.ON_UNMETERED_NETWORK,
              //  .setConstraints(Constraint.ON_ANY_NETWORK) 제약조건 없음
                //작업이 지속되는 기간을 설정한다. 영원히 or 다음 부팅시까지 등
                .setLifetime(Lifetime.FOREVER)
                //알림이 계속해서 발생하게 지정
                .setRecurring(true)
                //지금으로부터 첫번째 인자 ~ 두번째 인자 사이에 수행
                 // executionWindow 메서드의 첫 번째 인수는 작업을 수행해야하는 시간 프레임의 시작입니다.
                 // 두 번째 인수는 데이터가 동기화되어야하는 최신 시점입니다.
                 // 종료 시간은 보장되는 것은 아니지만 FirebaseJobDispatcher가 벗어날 수있는 지침이 될 수 있습니다.
                .setTrigger(Trigger.executionWindow(0, 60))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintReminderJob);
        sInitialized =true;

    }

}
