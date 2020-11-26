package com.github.mohammadsianaki.core.networkconnection

interface NetworkStateListener {
    fun onNetworkStateChanged(networkState: NetworkState)
}

enum class NetworkState {
    CONNECTED, DISCONNECTED
}
