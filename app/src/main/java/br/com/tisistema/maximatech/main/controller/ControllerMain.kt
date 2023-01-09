package br.com.tisistema.maximatech.main.controller

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.tisistema.maximatech.core.controller.AbstractController
import br.com.tisistema.maximatech.core.network.ConectividadeFactory
import br.com.tisistema.tiembarque.core.network.verificarconexao.VerificadorDeConexao

class ControllerMain(application: Application) : AbstractController(application) {

    @SuppressLint("StaticFieldLeak")
    private var context: Context = application.applicationContext

    private val verificadorDeConexao: VerificadorDeConexao =
        ConectividadeFactory.CHECKER.getVerificadorDeConectividade()

    private val mutableExisteConexaoComInternet = MutableLiveData<Boolean>()
    var liveMutableExisteConexaoComInternet: LiveData<Boolean> =
        mutableExisteConexaoComInternet

    private val mutableNaoExisteConexaoComInternet = MutableLiveData<Boolean>()
    var liveMutableNaoExisteConexaoComInternet: LiveData<Boolean> =
        mutableNaoExisteConexaoComInternet


    fun verificarConexaoComAInternet() {
        if (getConexaoDisponivel(context)) {
            carregarMutableExisteConexaoComInternet()
            return
        }
        carregarMutableNaoExisteConexaoComInternet()
    }

    private fun carregarMutableExisteConexaoComInternet() {
        mutableExisteConexaoComInternet.value = true
    }

    private fun carregarMutableNaoExisteConexaoComInternet() {
        mutableNaoExisteConexaoComInternet.value = true
    }

    private fun getConexaoDisponivel(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return verificadorDeConexao.existeConexao(connectivityManager)
    }
}
