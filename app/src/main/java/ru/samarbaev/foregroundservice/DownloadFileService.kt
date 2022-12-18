package ru.samarbaev.foregroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class DownloadFileService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(p0: Intent?): IBinder? = null


}