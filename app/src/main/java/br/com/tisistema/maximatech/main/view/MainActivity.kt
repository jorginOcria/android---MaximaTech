package br.com.tisistema.maximatech.main.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.core.cross.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarCliqueNosBotoes()
    }

    private fun configurarCliqueNosBotoes() {
        var botaoClientes: Button =
            findViewById(R.id.activity_main__botao_clientes)
        botaoClientes.setOnClickListener {
            navegarParaClienteActivity()
        }
    }

    private fun navegarParaClienteActivity() {
        startActivity(Intent(this, ClienteActivity::class.java))
        finish()
    }

}