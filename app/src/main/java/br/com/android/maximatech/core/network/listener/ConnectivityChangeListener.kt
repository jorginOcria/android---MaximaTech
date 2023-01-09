package br.com.android.maximatech.core.network.listener

interface ConnectivityChangeListener {
    fun onNetworkConnectionChange(isConnected: Boolean)
}