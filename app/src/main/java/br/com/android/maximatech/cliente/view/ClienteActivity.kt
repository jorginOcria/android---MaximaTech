package br.com.android.maximatech.cliente.view

import android.app.AlertDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.android.maximatech.cliente.controller.ControllerCliente
import br.com.android.maximatech.cliente.util.NavegacaoEntreFragmentSimpleFactory
import br.com.android.maximatech.core.cross.Constants
import br.com.android.maximatech.core.view.AbstractActivity
import br.com.android.maximatech.dadosdocliente.view.FragmentDadosDoCliente
import br.com.android.maximatech.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_cliente.*

class ClienteActivity : AbstractActivity() {

    private lateinit var controllerCliente: ControllerCliente
    private val fragmentDadosDoCliente = FragmentDadosDoCliente()
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private var fragmentAtual: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)
        carregarToolbar()
        configurarToolbarNaFragmentDadosDoCliente()
        configurarCorDosTextosDoMenuPrincipal()
        mudarDeFragment(fragmentDadosDoCliente)
        configurarCliqueNosBotoesDoMenu()
        configurarCliqueNaImageViewVoltarNaToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_dados_do_cliente_superior__item_legendas -> {
                mostrarDialogLegendas()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_dados_do_cliente_superior, menu)
        verificarEmQualFragmentOUsuarioEsta(menu)
        return true
    }

    override fun initControllers() {
        controllerCliente = ViewModelProvider(this)[ControllerCliente::class.java]
    }

    override fun runObservers() {}

    override fun onBackPressed() {
        navegarParaMainActivity()
    }

    private fun mostrarDialogLegendas() {
        var alertDialogBuilder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_legenda, null)
        alertDialogBuilder.setView(view)
        alertDialogBuilder.setTitle(getString(R.string.dialog_legenda__text_legenda))
        alertDialogBuilder.setPositiveButton(
            getString(R.string.fragment_dados_do_passageiro_alert_dialog__botao_fechar)
        ) { dialog, posicao -> }
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.show()
    }

    private fun verificarEmQualFragmentOUsuarioEsta(menu: Menu?) {
        if (fragmentAtual == Constants.FRAGMENT_ATUAL.HISTORICO_DE_PEDIDO) {
            mostrarItensDoMenuSuperior(menu)
        }
    }

    private fun mostrarItensDoMenuSuperior(menu: Menu?) {
        menu?.findItem(R.id.menu_dados_do_cliente_superior__item_search)!!.isVisible = true
        menu.findItem(R.id.menu_dados_do_cliente_superior__item_legendas)!!.isVisible = true
    }

    fun carregarFragmentAtual(fragmentAtual: String) {
        this.fragmentAtual = fragmentAtual
    }

    private fun carregarToolbar() {
        toolbar = findViewById(R.id.activity_cliente__toolbar)
    }

    fun configurarToolbarNaFragmentDadosDoCliente() {
        toolbar.title = getString(R.string.activity_cliente__nome_na_toolbar_dados_do_cliente)
        setSupportActionBar(toolbar)
    }

    fun configurarToolbarNaFragmentHistoricoDePedido() {
        toolbar.title = getString(R.string.activity_cliente__nome_na_toolbar_historico_de_pedidos)
        setSupportActionBar(toolbar)
    }

    fun configurarToolbarNaFragmentAlvaras() {
        toolbar.title = getString(R.string.activity_cliente__nome_na_toolbar_alvaras)
        setSupportActionBar(toolbar)
    }

    private fun configurarCorDosTextosDoMenuPrincipal() {
        var botaoDeNavegacaoDoMenu: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.botao_de_navegacao_do_menu_principal)
        botaoDeNavegacaoDoMenu.itemIconTintList = getColorStateList()
        botaoDeNavegacaoDoMenu.itemTextColor = getColorStateList()
    }

    private fun getColorStateList(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected),
                intArrayOf(-android.R.attr.state_selected)
            ),
            intArrayOf(
                Color.parseColor(Constants.CORES_DOS_ITENS_DO_MENU_PRINCIPAL.ITEM_ATIVO),
                Color.parseColor(Constants.CORES_DOS_ITENS_DO_MENU_PRINCIPAL.ITEM_INATIVO)
            )
        )
    }

    private fun configurarCliqueNaImageViewVoltarNaToolbar(){
        activity_cliente__view_voltar.setOnClickListener {
            navegarParaMainActivity()
        }
    }

    private fun configurarCliqueNosBotoesDoMenu() {
       botao_de_navegacao_do_menu_principal.setOnItemSelectedListener {
            NavegacaoEntreFragmentSimpleFactory.clickNosBotoesDoMenuPrincipal(
                it,
                this
            ).navegarEntreFragment()
            true
        }
    }

    fun mudarDeFragment(fragmet: Fragment) {
        val transacaoDeFragment = supportFragmentManager.beginTransaction()
        transacaoDeFragment.replace(R.id.fragment_container, fragmet)
        transacaoDeFragment.commit()
    }

     fun navegarParaMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}