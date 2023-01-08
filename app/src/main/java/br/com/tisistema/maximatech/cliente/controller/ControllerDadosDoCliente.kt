package br.com.tisistema.maximatech.cliente.controller

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.tisistema.maximatech.cliente.dao.BuscarClienteRestService
import br.com.tisistema.maximatech.cliente.dao.ClienteRepositoryService
import br.com.tisistema.maximatech.cliente.model.*
import br.com.tisistema.maximatech.contato.dao.ContatoRepositoryService
import br.com.tisistema.maximatech.contato.model.Contato
import br.com.tisistema.maximatech.contato.model.ContatoDto
import br.com.tisistema.maximatech.core.controller.AbstractController
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.core.cross.listener.RestCallListener
import br.com.tisistema.maximatech.core.network.ConectividadeFactory
import br.com.tisistema.tiembarque.core.network.verificarconexao.VerificadorDeConexao
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ControllerDadosDoCliente(application: Application) : AbstractController(application) {

    @SuppressLint("StaticFieldLeak")
    private var context: Context = application.applicationContext

    val cliente: Cliente = Cliente()
    val contatos: ArrayList<Contato> = arrayListOf()

    private val buscarClienteRestService =
        BuscarClienteRestService(this)

    private val verificadorDeConexao: VerificadorDeConexao =
        ConectividadeFactory.CHECKER.getVerificadorDeConectividade()

    private val clienteRepositoryService = ClienteRepositoryService(application)
    private val contatoRepositoryService = ContatoRepositoryService(application)

    @SuppressLint("SimpleDateFormat")
    private val formatoDeDataRecebido: SimpleDateFormat =
        SimpleDateFormat(Constants.DATE_FORMAT.FORMATO_DE_DATA_RECEBIDO)

    @SuppressLint("SimpleDateFormat")
    private val formatoDeDataParaExibicao: SimpleDateFormat =
        SimpleDateFormat(Constants.DATE_FORMAT.FORMATO_DE_DATA_EXIBIDO)

    private val mutableSucessoAoBuscarCliente = MutableLiveData<Cliente>()
    var liveMutableSucessoAoBuscarCliente: LiveData<Cliente> =
        mutableSucessoAoBuscarCliente

    private val mutableFalhaAoBuscarCliente = MutableLiveData<Boolean>()
    var liveMutableFalhaAoBuscarCliente: LiveData<Boolean> =
        mutableFalhaAoBuscarCliente

    private val mutableErroAoBuscarCliente = MutableLiveData<Boolean>()
    var liveMutableErroAoBuscarCliente: LiveData<Boolean> =
        mutableErroAoBuscarCliente

    fun requestBuscarClientes() {
        buscarClienteRestService.getCliente(object : RestCallListener<ResponseClienteDto> {

            override fun onFailure(throwable: Throwable) {
                carregarMutableFalhaAoBuscarCliente()
            }

            override fun onSuccess(data: ResponseClienteDto?, httpStatusCode: Int) {
                if (httpStatusCode == Constants.HTTP.STATUS_CODE.AUTORIZADO) {
                    limparClientesNoBancoDeDados()
                    limparContatosNoBancoDeDados()
                    carregarCliente(data?.cliente!!)
                    carregarContatos(data.cliente!!.contatos!!)
                    mergeClienteNoBancoDeDados()
                    mergeContatosNoBancoDeDados()
                    carregarMutableSucessoAoBuscarCliente()
                    return
                }
                carregarMutableErroAoBuscarCliente()
            }
        }

        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun converterDataParaFormatoDeExibicao(data: String?): String? {
        if (!data.isNullOrEmpty()) {
            val date: Date = formatoDeDataRecebido.parse(data)!!
            return formatoDeDataParaExibicao.format(date)
        }
        return data
    }


    private fun carregarCliente(clienteDto: ClienteDto) {
        cliente.legacyId = verificarSeExisteValorNoParametro(clienteDto.id.toString()).toLong()
        cliente.cnpj = verificarSeExisteValorNoParametro(clienteDto.cnpj)
        cliente.codigo = verificarSeExisteValorNoParametro(clienteDto.codigo)
        cliente.endereco = verificarSeExisteValorNoParametro(clienteDto.endereco)
        cliente.nomeFantasia = verificarSeExisteValorNoParametro(clienteDto.nomeFantasia)
        cliente.ramoAtividade = verificarSeExisteValorNoParametro(clienteDto.ramoAtividade)
        cliente.cpf = verificarSeExisteValorNoParametro(clienteDto.cpf)
        cliente.status = verificarSeExisteValorNoParametro(clienteDto.status)
        cliente.razaoSocial = verificarSeExisteValorNoParametro(clienteDto.razaoSocial)
        cliente.contatos = contatos
    }

    private fun carregarContatos(contatosDto: List<ContatoDto>) {
        val contato = Contato()
        for (contatoDto in contatosDto) {
            contatoDto.dataDeNascimento =
                converterDataParaFormatoDeExibicao(contatoDto.dataDeNascimento)
            contatoDto.dataDeNascimentoDoConjuge =
                converterDataParaFormatoDeExibicao(contatoDto.dataDeNascimentoDoConjuge)
            contatoDto.celular = converterNumeroDeCelularParaFormatoDeExibicao(contatoDto.celular)
            contato.conjuge = verificarSeExisteValorNoParametro(contatoDto.conjuge)
            contato.celular = verificarSeExisteValorNoParametro(contatoDto.celular)
            contato.nome = verificarSeExisteValorNoParametro(contatoDto.nome)
            contato.email = verificarSeExisteValorNoParametro(contatoDto.email)
            contato.time = verificarSeExisteValorNoParametro(contatoDto.time)
            contato.telefone = verificarSeExisteValorNoParametro(contatoDto.telefone)
            contato.dataDeNascimento =
                verificarSeExisteValorNoParametro(contatoDto.dataDeNascimento)
            contato.dataDeNascimentoDoConjuge =
                verificarSeExisteValorNoParametro(contatoDto.dataDeNascimentoDoConjuge)
            contato.tipo = verificarSeExisteValorNoParametro(contatoDto.tipo)
            contato.hobbie = verificarSeExisteValorNoParametro(contatoDto.hobbie)
            contato.idCliente = cliente.legacyId
            contatos.add(contato)
        }
    }

    private fun verificarSeExisteValorNoParametro(parametro: String?): String {
        if (parametro == null || parametro == Constants.SISTEMA.STRING_VAZIO) {
            return Constants.VALIDACOES.PARAMETRO_NULL
        }
        return parametro
    }

    @SuppressLint("DefaultLocale")
    private fun converterNumeroDeCelularParaFormatoDeExibicao(celular: String?): String {
        if (celular != null)
            return String.format(
                "(%02d) %04d-%05d",
                celular.toLong() / 1000000000L % 100,
                celular.toLong() / 10000 % 10000,
                celular.toLong() % 100000
            )
        return celular.toString()
    }

    private fun carregarMutableSucessoAoBuscarCliente() {
        mutableSucessoAoBuscarCliente.value = cliente
    }

    private fun carregarMutableFalhaAoBuscarCliente() {
        mutableFalhaAoBuscarCliente.value = true
    }

    private fun carregarMutableErroAoBuscarCliente() {
        mutableErroAoBuscarCliente.value = true
    }

    fun verificarConexaoComAInternet() {
        if (getConexaoDisponivel(context)) {
            requestBuscarClientes()
        } else {
            verificarSeExisteClienteNoBancoDeDados()
        }
    }

    private fun verificarSeExisteClienteNoBancoDeDados() {
        val cliente = clienteRepositoryService.getAll()
        if (cliente[0].idCliente != null) {
            cliente[0].contatos = getContatosDoCliente(cliente[0].legacyId!!.toInt()) as ArrayList<Contato>?
            carregarMutableSucessoAoBuscarCliente(cliente[0])
            return
        }
        carregarMutableErroAoBuscarCliente()

    }

    private fun getContatosDoCliente(idCliente: Int): List<Contato>? {
        return contatoRepositoryService.getById(idCliente)
    }

    private fun carregarMutableSucessoAoBuscarCliente(cliente: Cliente) {
        mutableSucessoAoBuscarCliente.value = cliente
    }

    private fun getClienteSalvoNoBancoDeDados() {
        clienteRepositoryService.getAll()
    }

    private fun limparClientesNoBancoDeDados() {
        clienteRepositoryService.deleteAll()
    }

    private fun limparContatosNoBancoDeDados() {
        contatoRepositoryService.deleteAll()
    }

    private fun mergeClienteNoBancoDeDados() {
        clienteRepositoryService.insert(cliente)
    }

    private fun mergeContatosNoBancoDeDados() {
        for (contato in contatos) {
            contatoRepositoryService.merge(contato)
        }
    }

    private fun getConexaoDisponivel(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return verificadorDeConexao.existeConexao(connectivityManager)
    }


}
