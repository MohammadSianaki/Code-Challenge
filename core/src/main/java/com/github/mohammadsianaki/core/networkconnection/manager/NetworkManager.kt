package com.github.mohammadsianaki.core.networkconnection.manager

import android.content.Context
import com.github.mohammadsianaki.core.extensions.getConnectivityManager

class NetworkManager : NetworkStateFacade by ReceiverManager {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            return context.getConnectivityManager().activeNetworkInfo?.isConnected ?: false
        }
    }
}
