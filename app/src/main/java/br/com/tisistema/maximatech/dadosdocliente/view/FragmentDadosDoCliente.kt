package br.com.tisistema.maximatech.dadosdocliente.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.contato.model.Contato
import br.com.tisistema.maximatech.contato.view.adapter.AdapterContato
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.core.view.AbstractFragment
import br.com.tisistema.maximatech.dadosdocliente.controller.ControllerDadosDoCliente
import br.com.tisistema.maximatech.dadosdocliente.model.DadosDoCliente
import br.com.tisistema.maximatech.main.view.MainActivity
import kotlinx.android.synthetic.main.fragment_dados_do_cliente.*
import kotlinx.android.synthetic.main.fragment_dados_do_cliente.view.*
import java.text.SimpleDateFormat
import java.util.*


class FragmentDadosDoCliente : AbstractFragment() {

    private lateinit var controllerDadosDoCliente: ControllerDadosDoCliente
    private lateinit var fragmetDadosDoClienteView: View
    private lateinit var adapterContato: AdapterContato
    private var cliente = DadosDoCliente()

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

    private fun verificarConexaoComAInternet() {
        controllerDadosDoCliente.verificarConexaoComAInternet()
    }

    override fun initControllers() {
        controllerDadosDoCliente = ViewModelProvider(this)[ControllerDadosDoCliente::class.java]
    }

    override fun runObservers() {
        controllerDadosDoCliente.liveMutableSucessoAoBuscarDadosDoCliente.observe(
            this,
            Observer { cliente ->
                carregarCliente(cliente)
                carregarCamposDeTextoComOsDadosDoCliente(cliente)
                cliente.contatos?.let { carregarAdapterContatos(it) }
                mostrarDadosDaTela()
                esconderTextLoading()
            })
        controllerDadosDoCliente.liveMutableErroAoBuscarCliente.observe(this,
            Observer {
                mostrarDialogErroAoBuscarCliente()
                esconderTextLoading()
            })
        controllerDadosDoCliente.liveMutableFalhaAoBuscarCliente.observe(this,
            Observer {
                mostrarDialogFalhaAoBuscarCliente()
                esconderTextLoading()
            })
    }

    private fun mostrarDadosDaTela() {
        fragment_dados_do_cliente__view_dados_do_cliente.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_dados_do_cliente.visibility = View.VISIBLE
        fragment_dados_do_cliente__view_divisora.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_razao_social.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_nome_fantasia.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_cpf.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_label_cpf.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_label_cnpj.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_cnpj.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_label_ramo_de_atividade.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_ramo_de_atividade.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_label_enderecos.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_enderecos.visibility = View.VISIBLE
        fragment_dados_do_cliente__view_contatos.visibility = View.VISIBLE
        fragment_dados_do_cliente__text_contatos.visibility = View.VISIBLE
        fragment_dados_do_cliente__linear_layout.visibility = View.VISIBLE
        fragment_dados_do_cliente__lista_de_contatos.visibility = View.VISIBLE
        fragment_dados_do_cliente__view_divisora_contatos.visibility = View.VISIBLE
        fragment_dados_do_cliente__botao_verifica_status_do_cliente.visibility = View.VISIBLE
    }


    private fun esconderTextLoading() {
        fragment_dados_do_cliente_text_loading.visibility = View.GONE
    }

    private fun carregarCliente(dadosDoCliente: DadosDoCliente) {
        this.cliente = dadosDoCliente
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
        val dataEHoraFormatados =
            SimpleDateFormat(Constants.FORMATAR.FORMATO_DATA_E_HORA, Locale.getDefault())
        return dataEHoraFormatados.format(date)
    }

    private fun carregarCamposDeTextoComOsDadosDoCliente(dadosDoCliente: DadosDoCliente) {
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_razao_social.text =
            dadosDoCliente.razaoSocial
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_nome_fantasia.text =
            dadosDoCliente.nomeFantasia
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_cpf.text = dadosDoCliente.cpf
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_cnpj.text = dadosDoCliente.cnpj
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_ramo_de_atividade.text =
            dadosDoCliente.ramoAtividade
        fragmetDadosDoClienteView.fragment_dados_do_cliente__text_enderecos.text =
            dadosDoCliente.endereco
    }

    private fun mostrarDialogErroAoBuscarCliente() {
        var alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(getString(R.string.fragment_dados_do_passageiro_alert_dialog__atencao))
        alertDialogBuilder.setMessage(getString(R.string.fragment_dados_do_passageiro_alert_dialog__erro_ao_buscar_dados_do_cliente))
        alertDialogBuilder.setPositiveButton(
            getString(R.string.fragment_dados_do_passageiro_alert_dialog__botao_fechar)
        ) { dialog, posicao ->
            navegarParaActivityAnterior()
        }
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.show()
    }

    private fun mostrarDialogFalhaAoBuscarCliente() {
        var alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(getString(R.string.fragment_dados_do_passageiro_alert_dialog__atencao))
        alertDialogBuilder.setMessage(getString(R.string.fragment_dados_do_passageiro_alert_dialog__falha_ao_buscar_dados_do_cliente))
        alertDialogBuilder.setPositiveButton(
            getString(R.string.fragment_dados_do_passageiro_alert_dialog__botao_fechar)
        ) { dialog, posicao ->
            navegarParaActivityAnterior()
        }
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.show()
    }

    private fun navegarParaActivityAnterior() {
        (activity as ClienteActivity).popBackStackToLastFragment()
    }

}