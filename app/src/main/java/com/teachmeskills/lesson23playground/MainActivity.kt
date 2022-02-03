package com.teachmeskills.lesson23playground

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, RecyclerListAdapterFragment())
                .commit()
        }

        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val pendingIntent = PendingIntent.getActivity(
            this,
            1,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_MUTABLE
        )

        val time = SystemClock.elapsedRealtime() + 1 * 60 * 60 * 1000
        val interval = time

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
            time,
            interval,
            pendingIntent
        )
    }
}