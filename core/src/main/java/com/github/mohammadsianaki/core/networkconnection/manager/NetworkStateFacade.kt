package com.github.mohammadsianaki.core.networkconnection.manager

import android.app.Activity
import com.github.mohammadsianaki.core.networkconnection.NetworkStateListener

interface NetworkStateFacade {
    fun registerNetworkChangeReceiver(activity: Activity, networkListener: NetworkStateListener)
    fun unregisterNetworkChangeReceiver(activity: Activity, networkListener: NetworkStateListener)
}
