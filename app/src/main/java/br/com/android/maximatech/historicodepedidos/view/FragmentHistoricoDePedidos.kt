package br.com.android.maximatech.historicodepedidos.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.android.maximatech.cliente.view.ClienteActivity
import br.com.android.maximatech.core.view.AbstractFragment
import br.com.android.maximatech.historicodepedidos.controller.ControllerHistoricoDePedido
import br.com.android.maximatech.historicodepedidos.model.HistoricoDePedido
import br.com.android.maximatech.historicodepedidos.view.adapter.AdapterHistoricoDePedido
import kotlinx.android.synthetic.main.fragment_historico_de_pedidos.*


class FragmentHistoricoDePedidos : AbstractFragment() {

    private lateinit var controllerHistoricoDePedido: ControllerHistoricoDePedido
    private lateinit var fragmetHistoricoDePedidos: View
    private lateinit var adapterHistoricoDePedido: AdapterHistoricoDePedido
    private var historicoDePedidos: List<HistoricoDePedido> = arrayListOf()
    private lateinit var menuItem: MenuItem
    private lateinit var searchView: SearchView

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

    private fun esconderTextLoading() {
        fragment_historico_de_pedidos_text_loading.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_dados_do_cliente_superior, menu)
        menuItem = menu.findItem(R.id.menu_dados_do_cliente_superior__item_search)
        searchView = MenuItemCompat.getActionView(menuItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
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
        (activity as ClienteActivity).navegarParaMainActivity()
    }

}