package com.macy.networkstate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isNetWorkEnabled();
        isNetworkConnected();
    }

    static String TAG = "macy";

    /**
     * 判断移动网络是否开启
     *
     * @param context
     * @return
     */
    public boolean isNetEnabled(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    Log.i(TAG, "移动网络已经开启-------networkInfo[i].getType()" + networkInfo[i].getType());
                    if (networkInfo[i].getType() == ConnectivityManager.TYPE_MOBILE)
                        if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                            Log.i(TAG, "移动网络已经开启");
                            return true;
                        }
                }
            }
        }
        Log.i(TAG, "移动网络还未开启");
        return false;
    }

    /**
     * 判断WIFI网络是否开启
     *
     * @param context
     * @return
     */
    public boolean isWifiEnabled(Context context) {
        WifiManager wm = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        if (wm != null && wm.isWifiEnabled()) {
            Log.i(TAG, "Wifi网络已经开启");
            return true;
        }
        Log.i(TAG, "Wifi网络还未开启");
        return false;
    }

    /**
     * 判断移动网络是否连接成功
     *
     * @param context
     * @return
     */
    public boolean isNetContected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (cm != null && info != null && info.isConnected()) {
            Log.i(TAG, "移动网络连接成功");
            return true;
        }
        Log.i(TAG, "移动网络连接失败");
        return false;
    }

    /**
     * 判断WIFI是否连接成功
     *
     * @param context
     * @return
     */
    public boolean isWifiContected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (info != null && info.isConnected()) {
            Log.i(TAG, "Wifi网络连接成功");
            return true;
        }
        Log.i(TAG, "Wifi网络连接失败");
        return false;
    }

    /**
     * 判断移动网络和WIFI是否开启
     */
    public void isNetWorkEnabled() {
        if (isNetEnabled(this) || isWifiEnabled(this)) {
            Log.i(TAG, "网络已经开启" + isNetEnabled(this) + "    ,    " + isWifiEnabled(this));
        } else {
            Log.i(TAG, "网络还未开启" + isNetEnabled(this) + "    ,    " + isWifiEnabled(this));
        }
    }

    /**
     * 判断移动网络和WIFI是否连接成功
     */
    public void isNetworkConnected() {
        if (isWifiContected(this) || isNetContected(this)) {
            Log.i(TAG, "网络连接成功" + isWifiContected(this) + "    ,    " + isNetContected(this));
        } else {
            Log.i(TAG, "网络连接失败" + isWifiContected(this) + "    ,    " + isNetContected(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
