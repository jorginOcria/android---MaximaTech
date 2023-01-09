package br.com.android.maximatech.core.network.callback

import android.content.Context
import br.com.android.maximatech.core.network.listener.ConnectivityChangeListener

interface CallbackDaConectividade {
    fun registerCallback(context: Context)
    fun addListener(listener: ConnectivityChangeListener)
    fun removeListener(listener: ConnectivityChangeListener)
    fun notifyListeners(isConnected: Boolean)
}