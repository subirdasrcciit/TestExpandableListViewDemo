package info.androidhive.expandablelistview;

import android.app.Application;
import android.content.Context;

import com.microsoft.azure.mobile.MobileCenter;
import com.microsoft.azure.mobile.analytics.Analytics;
import com.microsoft.azure.mobile.crashes.Crashes;

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

        MobileCenter.start(this, "8ab1c2d9-6f16-4050-8585-95628384b9e2",
                Analytics.class, Crashes.class);
    }

    public static Context getContext() {
        return mContext;
    }



}
