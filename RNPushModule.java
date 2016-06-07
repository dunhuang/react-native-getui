package com.getuiexample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

/**
 * Created by shiyinghua on 16/6/6.
 */

public class RNPushModule extends ReactContextBaseJavaModule  implements LifecycleEventListener {
    private BroadcastReceiver mReceiver;
    public ReactApplicationContext mContext = null;
    private Activity mActivity;

    public RNPushModule(ReactApplicationContext reactContext, Activity activity) {
        super(reactContext);
        mContext = reactContext;
        mActivity = activity;
        mReceiver = new PushReceiver(reactContext);
        getReactApplicationContext().addLifecycleEventListener(this);
        registerReceiverIfNecessary(mReceiver);
    }
    private void registerReceiverIfNecessary(BroadcastReceiver receiver) {
        //动态生成intent
        String appid = "";
        String packageName = mActivity.getApplicationContext().getPackageName();
        try {
            ApplicationInfo appInfo = mContext.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                appid = appInfo.metaData.getString("PUSH_APPID");
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction("com.igexin.sdk.action."+appid);
        mActivity.registerReceiver(receiver,intentFilter);
    }
    private void unregisterReceiver(BroadcastReceiver receiver) {
        mActivity.unregisterReceiver(receiver);
    }

    @Override
    public void onHostResume() {
        registerReceiverIfNecessary(mReceiver);
    }

    @Override
    public void onHostPause() {
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onHostDestroy() {
        unregisterReceiver(mReceiver);
    }

    @Override
    public String getName() {
        return "RNPushModule";
    }

}