package br.com.tisistema.maximatech.cliente.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.controller.ControllerDadosDoCliente
import br.com.tisistema.maximatech.cliente.model.Cliente
import br.com.tisistema.maximatech.contato.model.Contato
import br.com.tisistema.maximatech.cliente.view.adapter.AdapterContato
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.core.view.AbstractFragment
import kotlinx.android.synthetic.main.fragment_dados_do_cliente.*
import kotlinx.android.synthetic.main.fragment_dados_do_cliente.view.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentDadosDoCliente : AbstractFragment() {

    private lateinit var controllerDadosDoCliente: ControllerDadosDoCliente
    private lateinit var fragmetDadosDoClienteView: View
    private lateinit var adapterContato: AdapterContato
    private var cliente = Cliente()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmetDadosDoClienteView =
            inflater.inflate(R.layout.fragment_dados_do_cliente, container, false)
        carregarFragmentView(fragmetDadosDoClienteView)
        verificarConexaoComAInternet()
        configurarCliqueNoBotao()
        return fragmetDadosDoClienteView
    }

    private fun verificarConexaoComAInternet(){
        controllerDadosDoCliente.verificarConexaoComAInternet()
    }

    override fun initControllers() {
        controllerDadosDoCliente = ViewModelProvider(this)[ControllerDadosDoCliente::class.java]
    }

    override fun runObservers() {
        controllerDadosDoCliente.liveMutableSucessoAoBuscarCliente.observe(
            this,
            Observer { cliente ->
                carregarCliente(cliente)
                carregarCamposDeTextoComOsDadosDoCliente(cliente)
                cliente.contatos?.let { carregarAdapterContatos(it) }
            })
    }

    private fun carregarCliente(cliente: Cliente) {
        this.cliente = cliente
    }

    private fun carregarAdapterContatos(contatos: List<Contato>) {
        adapterContato =
            activity?.let { it1 -> AdapterContato(it1, contatos) }!!
        fragment_dados_do_cliente__lista_de_contatos.adapter = adapterContato
    }

    private fun configurarCliqueNoBotao() {
        fragmetDadosDoClienteView.fragment_dados_do_cliente__botao_verifica_status_do_cliente.setOnClickListener {
            exibirToast()
        }
    }

    private fun exibirToast() {
        showToast(
            Constants.TOAST.AVISO,
            getDataEHoraAtual() + "${getString(R.string.toast__status_do_cliente)}${cliente.status}"
        )
    }

    private fun getDataEHoraAtual(): String {
        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateTimeFormat.format(date)
    }

    private fun carregarCamposDeTextoComOsDadosDoCliente(cliente: Cliente) {
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_razao_social.text =
            cliente.razaoSocial
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_nome_fantasia.text =
            cliente.nomeFantasia
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_cpf.text = cliente.cpf
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_cnpj.text = cliente.cnpj
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_ramo_de_atividade.text =
            cliente.ramoAtividade
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_enderecos.text = cliente.endereco
    }

    private fun requestBuscarClientes() {
        controllerDadosDoCliente.requestBuscarClientes()
    }

}