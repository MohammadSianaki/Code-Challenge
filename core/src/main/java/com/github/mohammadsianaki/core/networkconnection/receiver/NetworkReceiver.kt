package com.github.mohammadsianaki.core.networkconnection.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import com.github.mohammadsianaki.core.networkconnection.NetworkState
import com.github.mohammadsianaki.core.networkconnection.NetworkStateListener
import com.github.mohammadsianaki.core.networkconnection.manager.NetworkManager

class NetworkReceiver : BroadcastReceiver() {

    private val listeners = hashSetOf<NetworkStateListener>()
    private var lastNetworkState: NetworkState? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        require(context != null && intent != null)
        val networkInfo = intent.extras?.get("networkInfo") as? NetworkInfo
        val networkConnected = when (networkInfo?.detailedState) {
            NetworkInfo.DetailedState.CONNECTED -> NetworkState.CONNECTED
            NetworkInfo.DetailedState.DISCONNECTED -> if (NetworkManager.isNetworkAvailable(context)) NetworkState.CONNECTED else NetworkState.DISCONNECTED
            else -> lastNetworkState
        }
        if (networkConnected == lastNetworkState) {
            return
        }
        lastNetworkState = networkConnected
        notifyObservers(networkConnected!!)
    }

    private fun notifyObservers(networkState: NetworkState) {
        listeners.forEach { listener ->
            listener.onNetworkStateChanged(networkState)
        }
    }

    fun addNetworkListener(listener: NetworkStateListener) = listeners.add(listener)
    fun removeNetworkListener(listener: NetworkStateListener) = listeners.remove(listener)
}
