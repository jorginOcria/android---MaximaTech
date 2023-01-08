package br.com.tisistema.tiembarque.core.network.listener

interface ConnectivityChangeListener {
    fun onNetworkConnectionChange(isConnected: Boolean)
}