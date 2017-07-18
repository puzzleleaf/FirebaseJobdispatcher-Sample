package com.tistory.puzzleleaf.jobscheduler;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by cmtyx on 2017-07-13.
 */

public class ToastFirebaseJobService extends com.firebase.jobdispatcher.JobService{

    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = ToastFirebaseJobService.this;
                ToastTask.executeTask(context,ToastTask.ACTION_TOAST);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job,false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        if(mBackgroundTask !=null) mBackgroundTask.cancel(true);
        return true;
    }
}
