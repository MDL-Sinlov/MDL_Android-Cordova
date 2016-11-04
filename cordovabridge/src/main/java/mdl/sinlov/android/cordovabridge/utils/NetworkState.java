/**
 * Copyright (c) 2015, sinlov Corporation, All Rights Reserved
 */
package mdl.sinlov.android.cordovabridge.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;

/**
 * Get NetworkState
 * <p>Created by "sinlov" on 2015/11/29.
 */
public class NetworkState {

    public static final int NETWORK_TYPE_MOBILE = ConnectivityManager.TYPE_MOBILE;
    public static final int NETWORK_TYPE_WIFI = ConnectivityManager.TYPE_WIFI;

    /**
     * Decide is NetWork available
     * 是否连接网络
     *
     * @param context context
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo net = connectivityManager.getActiveNetworkInfo();
        return net != null && net.isAvailable() && net.isConnected();
    }

    /**
     * 通过 host address 判断网络服务是否可用
     * @param context context
     * @param hostAddress boolean
     */
    public static boolean isServiceReachable(Context context, int hostAddress) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.requestRouteToHost(connectivityManager
                .getActiveNetworkInfo().getType(), hostAddress);
    }

    /**
     * get Network type.
     * 获取网络类型
     * <br>{@link NetworkState#NETWORK_TYPE_MOBILE}
     * <br>{@link NetworkState#NETWORK_TYPE_WIFI}
     * @param context
     * @return type of {@link NetworkState#NETWORK_TYPE_MOBILE} or {@link NetworkState#NETWORK_TYPE_WIFI}
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return NETWORK_TYPE_MOBILE;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isAvailable()) {
            if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return NETWORK_TYPE_WIFI;
            } else {
                return NETWORK_TYPE_MOBILE;
            }
        }
        return NETWORK_TYPE_MOBILE;
    }

    /**
     * 是否为Wap 网络
     * @return boolean
     */
    public static boolean isWapNetwork() {
        return !TextUtils.isEmpty(getProxyHost());
    }

    /**
     * 获取代理Host
     * @return host string
     */
    @SuppressWarnings("deprecation")
    public static String getProxyHost() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return System.getProperty("http.proxyHost");
        } else {
            return android.net.Proxy.getDefaultHost();
        }
    }

    /**
     * 获取代理端口
     * @return int of port
     */
    @SuppressWarnings("deprecation")
    public static int getProxyPort() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Integer.valueOf(System.getProperty("http.proxyPort"));
        } else {
            return Integer.valueOf(android.net.Proxy.getDefaultHost());
        }
    }

    private NetworkState() {
    }
}
