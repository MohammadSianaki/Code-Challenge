package com.github.mohammadsianaki.core.networkconnection.receiver

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.mohammadsianaki.core.networkconnection.NetworkState
import com.github.mohammadsianaki.core.networkconnection.NetworkStateListener

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
object NetworkCallback : ConnectivityManager.NetworkCallback() {
    private val listeners = hashSetOf<NetworkStateListener>()

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        notifyObservers(NetworkState.CONNECTED)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        notifyObservers(NetworkState.DISCONNECTED)
    }

    private fun notifyObservers(networkState: NetworkState) {
        listeners.forEach { listener ->
            listener.onNetworkStateChanged(networkState)
        }
    }

    fun addNetworkListener(listener: NetworkStateListener) = listeners.add(listener)
    fun removeNetworkListener(listener: NetworkStateListener) = listeners.remove(listener)
}
