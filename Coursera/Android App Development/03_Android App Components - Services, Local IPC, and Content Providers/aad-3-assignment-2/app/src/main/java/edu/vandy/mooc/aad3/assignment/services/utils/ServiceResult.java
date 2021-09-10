package edu.vandy.mooc.aad3.assignment.services.utils;


import android.os.Bundle;

/**
 * Interface that's implemented by an Activity that wants to receive
 * the results of a Service.
 */
@SuppressWarnings("UnusedParameters")
public interface ServiceResult {
    /**
     * Called when a launched Service sends back results from
     * computations it runs, giving the requestCode it was started
     * with, the resultCode it returned, and any additional data from
     * it.  The resultCode will be RESULT_CANCELED if the Service
     * explicitly returned that.
     */
    void onServiceResult(int requestCode,
                         int resultCode,
                         Bundle data);
}