package br.com.android.maximatech.main.view

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
import br.com.android.maximatech.cliente.view.ClienteActivity
import br.com.android.maximatech.core.cross.Constants
import br.com.android.maximatech.core.view.AbstractActivity
import br.com.android.maximatech.main.controller.ControllerMain
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
        val intent = Intent(this, ReminderBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val currentTimeMillis = System.currentTimeMillis()
        val tempoDaNotificacao = Constants.NOTIFICACAO.TEMPO_PARA_MANDAR_NOTIFICACAO_MILISEGUNDOS
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            currentTimeMillis + tempoDaNotificacao, pendingIntent
        )
    }

    private fun criarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nome = Constants.NOTIFICACAO.ID
            val descricaoDaNotificacao = Constants.NOTIFICACAO.DESCRICAO
            val importanciaDaNotificacao = NotificationManager.IMPORTANCE_HIGH
            val channel =
                NotificationChannel(Constants.NOTIFICACAO.ID, nome, importanciaDaNotificacao)
            channel.description = descricaoDaNotificacao
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}