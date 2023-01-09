package br.com.tisistema.maximatech.historicodepedidos.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.contato.view.adapter.AdapterContato
import br.com.tisistema.maximatech.core.view.AbstractFragment
import br.com.tisistema.maximatech.historicodepedidos.controller.ControllerHistoricoDePedido
import br.com.tisistema.maximatech.historicodepedidos.model.HistoricoDePedido
import br.com.tisistema.maximatech.historicodepedidos.view.adapter.AdapterHistoricoDePedido
import kotlinx.android.synthetic.main.fragment_dados_do_cliente.*
import kotlinx.android.synthetic.main.fragment_historico_de_pedidos.*

class FragmentHistoricoDePedidos : AbstractFragment() {

    private lateinit var controllerHistoricoDePedido: ControllerHistoricoDePedido
    private lateinit var fragmetHistoricoDePedidos: View
    private lateinit var adapterHistoricoDePedido: AdapterHistoricoDePedido
    private var historicoDePedidos: List<HistoricoDePedido> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmetHistoricoDePedidos =
            inflater.inflate(R.layout.fragment_historico_de_pedidos, container, false)
        verificarConexaoComAInternet()
        return fragmetHistoricoDePedidos
    }

    override fun initControllers() {
        controllerHistoricoDePedido =
            ViewModelProvider(this)[ControllerHistoricoDePedido::class.java]
    }

    override fun runObservers() {
        controllerHistoricoDePedido.liveMutableSucessoAoBuscarHistoricoDePedido.observe(this,
            Observer {
                carregarHistoricoDePedido(it)
                carregarAdapterHistoricoDePedido()
                esconderTextLoading()
            })
        controllerHistoricoDePedido.liveMutableFalhaAoBuscarHistoricoDePedido.observe(this,
            Observer {
                mostrarDialogFalhaAoBuscarHistoricoDePedido()
            })
        controllerHistoricoDePedido.liveMutableErroAoBuscarHistoricoDePedido.observe(this,
            Observer {
                mostrarDialogErroAoBuscarHistoricoDePedido()
            })
    }

    private fun esconderTextLoading(){
        fragment_historico_de_pedidos_text_loading.visibility = View.GONE
    }

    private fun mostrarDialogFalhaAoBuscarHistoricoDePedido() {
        var alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(getString(R.string.fragment_historico_de_pedido_alert_dialog__atencao))
        alertDialogBuilder.setMessage(getString(R.string.fragment_historico_de_pedido_alert_dialog__falha_ao_buscar_historico_de_pedido))
        alertDialogBuilder.setPositiveButton(
            getString(R.string.fragment_historico_de_pedido_alert_dialog__botao_fechar)
        ) { dialog, posicao ->
            navegarParaActivityAnterior()
        }
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.show()
    }

    private fun mostrarDialogErroAoBuscarHistoricoDePedido() {
        var alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(getString(R.string.fragment_historico_de_pedido_alert_dialog__atencao))
        alertDialogBuilder.setMessage(getString(R.string.fragment_historico_de_pedido_alert_dialog__erro_ao_buscar_historico_de_pedido))
        alertDialogBuilder.setPositiveButton(
            getString(R.string.fragment_historico_de_pedido_alert_dialog__botao_fechar)
        ) { dialog, posicao ->
            navegarParaActivityAnterior()
        }
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.show()
    }

    private fun carregarHistoricoDePedido(historicoDePedidos: List<HistoricoDePedido>) {
        this.historicoDePedidos = historicoDePedidos
    }

    private fun carregarAdapterHistoricoDePedido() {
        adapterHistoricoDePedido =
            activity?.let { it1 -> AdapterHistoricoDePedido(it1, historicoDePedidos) }!!
        fragment_historico_de_pedidos__list_historico_de_pedidos.adapter = adapterHistoricoDePedido
    }

    private fun verificarConexaoComAInternet() {
        controllerHistoricoDePedido.verificarConexaoComAInternet()
    }

    private fun navegarParaActivityAnterior() {
        (activity as ClienteActivity).popBackStackToLastFragment()
    }

}