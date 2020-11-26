package com.github.mohammadsianaki.core.networkconnection.manager

import android.app.Activity
import android.net.NetworkRequest
import android.os.Build
import com.github.mohammadsianaki.core.extensions.getConnectivityManager
import com.github.mohammadsianaki.core.networkconnection.NetworkStateListener
import com.github.mohammadsianaki.core.networkconnection.receiver.NetworkCallback
import com.github.mohammadsianaki.core.networkconnection.receiver.NetworkReceiver

object ReceiverManager : NetworkStateFacade {
    private val connectivityReceiver = NetworkReceiver()

    override fun registerNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkStateListener
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val connectivityManager = activity.applicationContext.getConnectivityManager()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(NetworkCallback)
            } else {
                connectivityManager.registerNetworkCallback(
                    NetworkRequest.Builder().build(), NetworkCallback
                )
            }
            NetworkCallback.addNetworkListener(networkListener)
        }
    }

    override fun unregisterNetworkChangeReceiver(
        activity: Activity,
        networkListener: NetworkStateListener
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val connectivityManager = activity.applicationContext.getConnectivityManager()
            connectivityManager.unregisterNetworkCallback(NetworkCallback)
            NetworkCallback.removeNetworkListener(networkListener)
        } else {
            activity.unregisterReceiver(connectivityReceiver)
            connectivityReceiver.removeNetworkListener(networkListener)
        }
    }
}
