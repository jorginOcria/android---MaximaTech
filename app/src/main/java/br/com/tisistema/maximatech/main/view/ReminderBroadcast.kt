package br.com.tisistema.maximatech.main.view

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.tisistema.maximatech.R
import br.com.tisistema.maximatech.core.cross.Constants
import br.com.tisistema.maximatech.splash.view.SplashActivity

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val i = Intent(context, SplashActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        var pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_MUTABLE)

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context!!, "notificacao")
                .setSmallIcon(R.drawable.maxima_logotipo)
                .setContentTitle(Constants.NOTIFICACAO.TITULO)
                .setContentText(Constants.NOTIFICACAO.TEXTO)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(200, builder.build())

    }
}