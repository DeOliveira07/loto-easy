package com.example.lotoeasy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class SorteioNotificationWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        exibirNotificacao()
        return Result.success()
    }

    private fun exibirNotificacao() {
        val channelId = "lotoeasy_sorteios_channel"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Lembretes de Sorteio LotoEasy",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notificações de lembretes e resultados de sorteios da Lotomania"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Ícone padrão do Android
            .setContentTitle("LotoEasy - Dia de Sorteio! 🎲")
            .setContentText("Hoje é dia de sorteio da Lotomania às 20h. Não esqueça de cadastrar e conferir seus talões!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        notificationManager.notify(1001, builder.build())
    }
}