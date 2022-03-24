package id.ac.ubaya.advweek4_160419107.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.advweek4_160419107.R
import id.ac.ubaya.advweek4_160419107.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {
    init{
        instance = this
    }
    companion object{
        private var instance:MainActivity ?=null
        fun showNotification(title:String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder= NotificationCompat.Builder(instance!!.applicationContext, channelId)
                .apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT

                    setAutoCancel(true)

            }

            val notif= NotificationManagerCompat.from(instance!!.applicationContext)
            notif.notify(1001,builder.build())

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT,
            true,
            getString(R.string.app_name),
            "App Channel"
        )

//        Observable.just("hellow","world","!!")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { Log.d("Messages","data captured: $it")},
//                { Log.d("Messages","error: ${it.message.toString()}")},
//                { Log.d("Messages","complete")},
//            )


    }
}