package br.com.tisistema.maximatech.core.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.tisistema.maximatech.core.cross.listener.CommonsHTTPListener
import br.com.tisistema.maximatech.core.util.ConnectivityUtil
import br.com.tisistema.tiembarque.core.network.listener.ConnectivityChangeListener

abstract class AbstractController(application: Application) : AndroidViewModel(application),
    CommonsHTTPListener, ConnectivityChangeListener {

    private val connectivityCallback = ConnectivityUtil.getInstance().connectivityCallback

    init {
        connectivityCallback.addListener(this)
    }

    private var mutableCommonHTTPStatus = MutableLiveData<Int>()
    fun getCommonHTTPStatus(): LiveData<Int> {
        return mutableCommonHTTPStatus
    }

    private var mutableHTTPCarregando = MutableLiveData<Boolean>()
    fun getHTTPCarregando(): LiveData<Boolean> {
        return mutableHTTPCarregando
    }

    private var mutableHTTPNaoCarregando = MutableLiveData<Boolean>()
    fun getHTTPNaoCarregando(): LiveData<Boolean> {
        return mutableHTTPNaoCarregando
    }


    private var mutableMudancaDeStatusDeConexaoComInternet = MutableLiveData<Boolean>()
    fun getMudancaDeStatusDeConexaoComInternet(): LiveData<Boolean> {
        return mutableMudancaDeStatusDeConexaoComInternet
    }

    override fun onReceiveCommonHTTPStatus(statusCode: Int) {
        mutableCommonHTTPStatus.postValue(statusCode)
    }

    override fun onStartHTTPRequest() {
        mutableHTTPCarregando.postValue(true)
    }

    override fun onReceiveHTTPResponse() {
        mutableHTTPNaoCarregando.postValue(true)
    }

    override fun onNetworkConnectionChange(isConnected: Boolean) {
        mutableMudancaDeStatusDeConexaoComInternet.postValue(isConnected)
    }

    fun carregarMutableMudancaDeStatusDeConexaoComInternet(){
        mutableMudancaDeStatusDeConexaoComInternet.postValue(false)
    }

}