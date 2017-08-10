package info.androidhive.expandablelistview;

import android.app.Application;
import android.content.Context;

import com.microsoft.azure.mobile.MobileCenter;
import com.microsoft.azure.mobile.analytics.Analytics;
import com.microsoft.azure.mobile.crashes.Crashes;

import info.androidhive.expandablelistview.util.Constants;

/**
 * Created by mobilecoe-imac6 on 06/04/17.
 */
public class ExpandableApp extends Application {

    private static Context mContext;
    private static ExpandableApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;

        MobileCenter.start(this, Constants.MOBILE_CENTER_KEY,
                Analytics.class, Crashes.class);
    }

    public static Context getContext() {
        return mContext;
    }



}
