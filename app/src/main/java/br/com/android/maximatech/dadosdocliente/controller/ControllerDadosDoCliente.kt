package br.com.android.maximatech.dadosdocliente.controller

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.android.maximatech.dadosdocliente.dao.BuscarDadosDoClienteRestService
import br.com.android.maximatech.dadosdocliente.dao.DadosDoClienteRepositoryService
import br.com.android.maximatech.contato.dao.ContatoRepositoryService
import br.com.android.maximatech.contato.model.Contato
import br.com.android.maximatech.contato.model.ContatoDto
import br.com.android.maximatech.core.controller.AbstractController
import br.com.android.maximatech.core.cross.Constants
import br.com.android.maximatech.core.cross.listener.RestCallListener
import br.com.android.maximatech.core.network.ConectividadeFactory
import br.com.android.maximatech.dadosdocliente.model.DadosDoCliente
import br.com.android.maximatech.dadosdocliente.model.DadosDoCllienteDto
import br.com.android.maximatech.dadosdocliente.model.ResponseDadosDoClienteDto
import br.com.android.maximatech.core.network.verificarconexao.VerificadorDeConexao
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ControllerDadosDoCliente(application: Application) : AbstractController(application) {

    @SuppressLint("StaticFieldLeak")
    private var context: Context = application.applicationContext

    val dadosDoCliente: DadosDoCliente = DadosDoCliente()
    val contatos: ArrayList<Contato> = arrayListOf()

    private val buscarClienteRestService =
        BuscarDadosDoClienteRestService(this)

    private val verificadorDeConexao: VerificadorDeConexao =
        ConectividadeFactory.CHECKER.getVerificadorDeConectividade()

    private val clienteRepositoryService = DadosDoClienteRepositoryService(application)
    private val contatoRepositoryService = ContatoRepositoryService(application)

    @SuppressLint("SimpleDateFormat")
    private val formatoDeDataRecebido: SimpleDateFormat =
        SimpleDateFormat(Constants.FORMATAR.FORMATO_DE_DATA_RECEBIDO)

    @SuppressLint("SimpleDateFormat")
    private val formatoDeDataParaExibicao: SimpleDateFormat =
        SimpleDateFormat(Constants.FORMATAR.FORMATO_DE_DATA_EXIBIDO)

    private val mutableSucessoAoBuscarCliente = MutableLiveData<DadosDoCliente>()
    var liveMutableSucessoAoBuscarDadosDoCliente: LiveData<DadosDoCliente> =
        mutableSucessoAoBuscarCliente

    private val mutableFalhaAoBuscarCliente = MutableLiveData<Boolean>()
    var liveMutableFalhaAoBuscarCliente: LiveData<Boolean> =
        mutableFalhaAoBuscarCliente

    private val mutableErroAoBuscarCliente = MutableLiveData<Boolean>()
    var liveMutableErroAoBuscarCliente: LiveData<Boolean> =
        mutableErroAoBuscarCliente

    private fun requestBuscarClientes() {
        buscarClienteRestService.getCliente(object : RestCallListener<ResponseDadosDoClienteDto> {

            override fun onFailure(throwable: Throwable) {
                carregarMutableFalhaAoBuscarCliente()
            }

            override fun onSuccess(data: ResponseDadosDoClienteDto?, httpStatusCode: Int) {
                if (httpStatusCode == Constants.HTTP.STATUS_CODE.AUTORIZADO) {
                    limparClientesNoBancoDeDados()
                    limparContatosNoBancoDeDados()
                    carregarCliente(data?.dadosDoClliente!!)
                    carregarContatos(data.dadosDoClliente!!.contatos!!)
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

    @SuppressLint("DefaultLocale")
    private fun converterNumeroDeCelularParaFormatoDeExibicao(celular: String?): String? {
        if (celular != null) {
            return String.format(
                Constants.FORMATAR.FORMATO_CELULAR,
                celular.toLong() / 1000000000L % 100,
                celular.toLong() / 10000 % 10000,
                celular.toLong() % 100000
            )
        }
        return celular
    }

    @SuppressLint("SimpleDateFormat")
    private fun converterDataParaFormatoDeExibicao(data: String?): String? {
        if (!data.isNullOrEmpty()) {
            val date: Date = formatoDeDataRecebido.parse(data)!!
            return formatoDeDataParaExibicao.format(date)
        }
        return data
    }

    private fun verificarSeExisteValorNoParametro(parametro: String?): String {
        if (parametro == null || parametro == Constants.SISTEMA.STRING_VAZIO) {
            return Constants.VALIDAR.PARAMETRO_NULL
        }
        return parametro
    }

    private fun verificarSeExisteClienteNoBancoDeDados() {
        val cliente = clienteRepositoryService.getPrimeiroCliente()
        if (cliente != null) {
            cliente.contatos =
                getContatosDoCliente(cliente.legacyId!!.toInt()) as ArrayList<Contato>?
            carregarMutableSucessoAoBuscarCliente(cliente)
            return
        }
        carregarMutableErroAoBuscarCliente()
    }

    private fun carregarCliente(dadosDoCllienteDto: DadosDoCllienteDto) {
        dadosDoCliente.legacyId = verificarSeExisteValorNoParametro(dadosDoCllienteDto.id.toString()).toLong()
        dadosDoCliente.cnpj = verificarSeExisteValorNoParametro(dadosDoCllienteDto.cnpj)
        dadosDoCliente.codigo = verificarSeExisteValorNoParametro(dadosDoCllienteDto.codigo)
        dadosDoCliente.endereco = verificarSeExisteValorNoParametro(dadosDoCllienteDto.endereco)
        dadosDoCliente.nomeFantasia = verificarSeExisteValorNoParametro(dadosDoCllienteDto.nomeFantasia)
        dadosDoCliente.ramoAtividade = verificarSeExisteValorNoParametro(dadosDoCllienteDto.ramoAtividade)
        dadosDoCliente.cpf = verificarSeExisteValorNoParametro(dadosDoCllienteDto.cpf)
        dadosDoCliente.status = verificarSeExisteValorNoParametro(dadosDoCllienteDto.status)
        dadosDoCliente.razaoSocial = verificarSeExisteValorNoParametro(dadosDoCllienteDto.razaoSocial)
        dadosDoCliente.contatos = contatos
    }

    private fun carregarContatos(contatosDto: List<ContatoDto>) {
        var idContato = 0L
        val contato = Contato()
        for (contatoDto in contatosDto) {
            contatoDto.dataDeNascimento = converterDataParaFormatoDeExibicao(contatoDto.dataDeNascimento)
            contatoDto.dataDeNascimentoDoConjuge = converterDataParaFormatoDeExibicao(contatoDto.dataDeNascimentoDoConjuge)
            contatoDto.celular = converterNumeroDeCelularParaFormatoDeExibicao(contatoDto.celular)
            contato.conjuge = verificarSeExisteValorNoParametro(contatoDto.conjuge)
            contato.celular = verificarSeExisteValorNoParametro(contatoDto.celular)
            contato.nome = verificarSeExisteValorNoParametro(contatoDto.nome)
            contato.email = verificarSeExisteValorNoParametro(contatoDto.email)
            contato.time = verificarSeExisteValorNoParametro(contatoDto.time)
            contato.telefone = verificarSeExisteValorNoParametro(contatoDto.telefone)
            contato.dataDeNascimento = verificarSeExisteValorNoParametro(contatoDto.dataDeNascimento)
            contato.dataDeNascimentoDoConjuge = verificarSeExisteValorNoParametro(contatoDto.dataDeNascimentoDoConjuge)
            contato.tipo = verificarSeExisteValorNoParametro(contatoDto.tipo)
            contato.hobbie = verificarSeExisteValorNoParametro(contatoDto.hobbie)
            contato.idCliente = dadosDoCliente.legacyId
            contato.idContato = idContato
            idContato += 1
            contatos.add(contato)
        }
    }

    private fun carregarMutableSucessoAoBuscarCliente() {
        mutableSucessoAoBuscarCliente.value = dadosDoCliente
    }

    private fun carregarMutableFalhaAoBuscarCliente() {
        mutableFalhaAoBuscarCliente.value = true
    }

    private fun carregarMutableErroAoBuscarCliente() {
        mutableErroAoBuscarCliente.value = true
    }

    private fun getContatosDoCliente(idCliente: Int): List<Contato>? {
        return contatoRepositoryService.getById(idCliente)
    }

    private fun carregarMutableSucessoAoBuscarCliente(dadosDoCliente: DadosDoCliente) {
        mutableSucessoAoBuscarCliente.value = dadosDoCliente
    }

    private fun limparClientesNoBancoDeDados() {
        clienteRepositoryService.deleteAll()
    }

    private fun limparContatosNoBancoDeDados() {
        contatoRepositoryService.deleteAll()
    }

    private fun mergeClienteNoBancoDeDados() {
        clienteRepositoryService.merge(dadosDoCliente)
    }

    private fun mergeContatosNoBancoDeDados() {
        for (contato in contatos) {
            contatoRepositoryService.merge(contato)
        }
    }

    fun verificarConexaoComAInternet() {
        if (getConexaoDisponivel(context)) {
            requestBuscarClientes()
        } else {
            verificarSeExisteClienteNoBancoDeDados()
        }
    }

    private fun getConexaoDisponivel(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return verificadorDeConexao.existeConexao(connectivityManager)
    }


}
