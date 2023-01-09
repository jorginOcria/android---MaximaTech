package br.com.tisistema.maximatech.main.view

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.cliente.view.ClienteActivity
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.core.view.AbstractActivity
import br.com.tisistema.maximatech.main.controller.ControllerMain
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity() {

    private lateinit var controllerMain: ControllerMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verificarConexaoComAInternet()
        carregarTextVersaoDoAplicativo()
        configurarCliqueNosBotoes()
        criarNotificacao()
        iniciarNotificacao()

    }

    private fun verificarConexaoComAInternet() {
        controllerMain.verificarConexaoComAInternet()
    }

    override fun initControllers() {
        controllerMain = ViewModelProvider(this)[ControllerMain::class.java]
    }

    override fun runObservers() {
        controllerMain.liveMutableExisteConexaoComInternet.observe(this, Observer {
            carregarImageViewExisteConexaoComAInternet()
        })
        controllerMain.liveMutableNaoExisteConexaoComInternet.observe(this, Observer {
            carregarImageViewNaoExisteConexaoComAInternet()
        })
        controllerMain.getMudancaDeStatusDeConexaoComInternet().observe(this, Observer {
            if (it) {
                carregarImageViewExisteConexaoComAInternet()
                return@Observer
            }
            carregarImageViewNaoExisteConexaoComAInternet()
        })
    }

    private fun carregarTextVersaoDoAplicativo() {
        activity_main__versao_do_aplicativo.text = Constants.VERSAO_DO_APLICATIVO.VERSAO
    }

    private fun carregarImageViewExisteConexaoComAInternet() {
        activity_main__conexao_com_a_internet.setImageResource(R.drawable.ic_maxima_nuvem_conectado)
    }

    private fun carregarImageViewNaoExisteConexaoComAInternet() {
        activity_main__conexao_com_a_internet.setImageResource(R.drawable.ic_maxima_nuvem_desconectado)
    }

    private fun configurarCliqueNosBotoes() {
        activity_main__botao_clientes.setOnClickListener { navegarParaClienteActivity() }
    }

    private fun navegarParaClienteActivity() {
        startActivity(Intent(this, ClienteActivity::class.java))
        finish()
    }

    private fun iniciarNotificacao() {
        var intent = Intent(this, ReminderBroadcast::class.java)
        var pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        var alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        var timeAt = System.currentTimeMillis()
        var tenSeconds = 1000 * 10
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            timeAt + tenSeconds, pendingIntent
        )
    }

    private fun criarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var name = "notificacao"
            var descricao = "descricao"
            var importancia = NotificationManager.IMPORTANCE_HIGH
            var channel = NotificationChannel("notificacao", name, importancia)
            channel.description = descricao

            val notificationManager: NotificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}