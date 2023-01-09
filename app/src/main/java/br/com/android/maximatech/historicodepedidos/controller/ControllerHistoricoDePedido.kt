package br.com.android.maximatech.historicodepedidos.controller

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.android.maximatech.core.controller.AbstractController
import br.com.android.maximatech.core.cross.Constants
import br.com.android.maximatech.core.cross.listener.RestCallListener
import br.com.android.maximatech.core.network.ConectividadeFactory
import br.com.android.maximatech.historicodepedidos.dao.HistoricoDePedidoRepositoryService
import br.com.android.maximatech.historicodepedidos.dao.HistoricoDePedidoRestService
import br.com.android.maximatech.historicodepedidos.model.HistoricoDePedido
import br.com.android.maximatech.historicodepedidos.model.HistoricoDePedidoDto
import br.com.android.maximatech.historicodepedidos.model.ResponseHistoricoDePedidoDto
import br.com.android.maximatech.core.network.verificarconexao.VerificadorDeConexao

class ControllerHistoricoDePedido(application: Application) : AbstractController(application) {


    @SuppressLint("StaticFieldLeak")
    private var context: Context = application.applicationContext

    private val historicoDePedidoRepositoryService = HistoricoDePedidoRepositoryService(application)

    private val historicoDePedidos = arrayListOf<HistoricoDePedido>()

    private val historicoDePedidoRestService =
        HistoricoDePedidoRestService(this)

    private val verificadorDeConexao: VerificadorDeConexao =
        ConectividadeFactory.CHECKER.getVerificadorDeConectividade()

    private val mutableSucessoAoBuscarHistoricoDePedido = MutableLiveData<List<HistoricoDePedido>>()
    var liveMutableSucessoAoBuscarHistoricoDePedido: LiveData<List<HistoricoDePedido>> =
        mutableSucessoAoBuscarHistoricoDePedido

    private val mutableFalhaAoBuscarHistoricoDePedido = MutableLiveData<Boolean>()
    var liveMutableFalhaAoBuscarHistoricoDePedido: LiveData<Boolean> =
        mutableFalhaAoBuscarHistoricoDePedido

    private val mutableErroAoBuscarHistoricoDePedido = MutableLiveData<Boolean>()
    var liveMutableErroAoBuscarHistoricoDePedido: LiveData<Boolean> =
        mutableErroAoBuscarHistoricoDePedido


    fun verificarConexaoComAInternet() {
        if (getConexaoDisponivel(context)) {
            requestHistoricoDePedidos()
        } else {
            verificarSeExisteHistoricoDePedidoNoBancoDeDados()
        }
    }

    private fun verificarSeExisteHistoricoDePedidoNoBancoDeDados() {
        val historicoDePedidos = historicoDePedidoRepositoryService.getAll()
        if (!historicoDePedidos.isNullOrEmpty()) {
            carregarMutableSucessoAoBuscarHistoricoDePedido(historicoDePedidos)
            return
        }
        carregarMutableFalhaAoBuscarHistoricoDePedido()
    }

    private fun getConexaoDisponivel(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return verificadorDeConexao.existeConexao(connectivityManager)
    }

    private fun requestHistoricoDePedidos() {
        historicoDePedidoRestService.getHistoricoDePedido(object :
            RestCallListener<ResponseHistoricoDePedidoDto> {

            override fun onFailure(throwable: Throwable) {
                carregarMutableFalhaAoBuscarHistoricoDePedido()
            }

            override fun onSuccess(data: ResponseHistoricoDePedidoDto?, httpStatusCode: Int) {
                if (httpStatusCode == Constants.HTTP.STATUS_CODE.AUTORIZADO && !data?.pedidos.isNullOrEmpty()) {
                    carregarHistoricoDePedidos(data?.pedidos)
                    limparHistoricoDePedidoNoBancoDeDados()
                    mergeHistoricoDePedidoNoBancoDeDados()
                    carregarMutableSucessoAoBuscarHistoricoDePedido()
                    return
                }
                carregarMutableErroAoBuscarHistoricoDePedido()
            }
        })
    }

    private fun carregarHistoricoDePedidos(historicoDePedidosDto: List<HistoricoDePedidoDto>?) {
        var idHistoricoDePedido = 0L
        for (historicoDePedidoDto in historicoDePedidosDto!!) {
            val historicoDePedido = HistoricoDePedido()
            historicoDePedido.idHistoricoDePedido = idHistoricoDePedido
            historicoDePedidoDto.data =
                configurarDataParaFormatoDeExibicao(historicoDePedidoDto.data)
            historicoDePedido.data = verificarSeExisteValorNoParametro(historicoDePedidoDto.data)
            historicoDePedido.numeroDoPedidoErp =
                verificarSeExisteValorNoParametro(historicoDePedidoDto.numeroDoPedidoErp)
            historicoDePedido.numeroDoPedidoRca =
                verificarSeExisteValorNoParametro(historicoDePedidoDto.numeroDoPedidoRca.toString()).toLong()
            historicoDePedido.critica =
                verificarSeExisteValorNoParametro(historicoDePedidoDto.critica)
            historicoDePedido.legendas = historicoDePedidoDto.legendas
            historicoDePedido.status =
                verificarSeExisteValorNoParametro(historicoDePedidoDto.status)
            historicoDePedido.nomeDoCliente =
                verificarSeExisteValorNoParametro(historicoDePedidoDto.nomeDoCliente)
            historicoDePedido.tipo = verificarSeExisteValorNoParametro(historicoDePedidoDto.tipo)
            historicoDePedido.codigoDoCliente =
                verificarSeExisteValorNoParametro(historicoDePedidoDto.codigoDoCliente)
            idHistoricoDePedido += 1
            historicoDePedidos.add(historicoDePedido)
        }
    }

    private fun configurarDataParaFormatoDeExibicao(data: String?): String {
        return data!!.subSequence(11, 16).toString()
    }

    private fun verificarSeExisteValorNoParametro(parametro: String?): String {
        if (parametro == null || parametro == Constants.SISTEMA.STRING_VAZIO) {
            return Constants.VALIDAR.PARAMETRO_NULL
        }
        return parametro
    }

    private fun carregarMutableSucessoAoBuscarHistoricoDePedido() {
        mutableSucessoAoBuscarHistoricoDePedido.value = historicoDePedidos
    }

    private fun carregarMutableFalhaAoBuscarHistoricoDePedido() {
        mutableFalhaAoBuscarHistoricoDePedido.value = true
    }

    private fun carregarMutableErroAoBuscarHistoricoDePedido() {
        mutableErroAoBuscarHistoricoDePedido.value = true
    }

    private fun carregarMutableSucessoAoBuscarHistoricoDePedido(historicoDePedidos: List<HistoricoDePedido>) {
        mutableSucessoAoBuscarHistoricoDePedido.value = historicoDePedidos
    }

    private fun mergeHistoricoDePedidoNoBancoDeDados() {
        for (historicoDePedidos in historicoDePedidos) {
            historicoDePedidoRepositoryService.insert(historicoDePedidos)
        }
    }

    private fun limparHistoricoDePedidoNoBancoDeDados() {
        historicoDePedidoRepositoryService.deleteAll()
    }
}
