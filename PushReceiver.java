package com.getuiexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.igexin.sdk.PushConsts;


/**
 * Created by shiyinghua on 16/6/6.
 */

public class PushReceiver extends BroadcastReceiver {
    public PushReceiver() {
        super();
    }

    public PushReceiver(ReactApplicationContext context) {
        mContext = context;
    }

    private ReactApplicationContext mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        String cid = "";
        Bundle bundle = intent.getExtras();
        WritableMap params = Arguments.createMap();
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_MSG_DATA:
                // 获取透传数据
                byte[] payload = bundle.getByteArray("payload");
                if (payload != null) {
                    String str = new String(payload);
                    params.putString("payload", str);
                    sendEvent("pushreceived", params);
                }
                break;

            case PushConsts.GET_CLIENTID:

                // 获取ClientID(CID)
                // 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
                cid = bundle.getString("clientid");
                params.putString("clientid", cid);
                sendEvent("getclientid", params );
                break;

            case PushConsts.THIRDPART_FEEDBACK:
                break;

            default:
                break;
        }
    }
    private void sendEvent(String type, @Nullable WritableMap params){
        if (mContext == null) {
            return;
        }

        if (! mContext.hasActiveCatalystInstance()) {
            return;
        }
        //发消息给js
        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(type, params);
    }
}
