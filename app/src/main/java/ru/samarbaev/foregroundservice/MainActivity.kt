package ru.samarbaev.foregroundservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * // Введение
// API для создания нотификаций начиная с версии API 26 поменялось
// Поэтому этап создания нотификаций для api ниже 26 и выше 26 различаются
//
// Создание Notification для версии api ниже 26
// Существует 2 основных класса для конфигурации
// 1. NotificationCompat
// 2. NotificationManagerCompat

// Создание Notification для версии api выше 26
// Существует 3 основных класса для конфигурации
// 1. NotificationCompat
// 2. NotificationChannel
// 3. NotificationManager/NotificationManagerCompat

// NotificationCompat - это класс, который отвечает за конфигурацию внешнего вида notification.
// У него разнообразное количество параметров для конфигурации: иконка, заголовок, подзаголовок и тд.
// Подробнее об этом смотри документацию.
// Для создания экземпляра необходимо вызвать Builder(). Builder() имеет два конструктора
// Builder(Context context) - для версии API ниже 26
// Builder(Context context, String channelId) - для версии API выше 26

// NotificationChannel - этот класс появился в версии api выше 26. Позволяет группировать уведолмения в разные категории.
// Это позволяет реализовать уведомления нескольких типов. Например, рекламные уведомления, уведомления о днях рождений и тд
// вот тут более подробно.
// Важно отметить, что NotificationChannel реализует интерфейс Parcelable.
// https://stackoverflow.com/questions/65247258/what-is-notificationchannel
//
// NotificationManagerCompat - это класс, который занимается управлением работы Notification.
// Например, мы можем вызвать метод notify(int id, @NonNull Notification notification) для
// размещения уведомления, которое будет отображаться в строке состояния, ленте и т. д.
// Или мы можем отменить все ранее показаное уведомление с помощью метода cancel(int id)
// Разница между NotificationManagerCompat vs NotificationManager
// Если кратко, то NotificationManagerCompat под капотом использует NotificationManager
// NotificationManagerCompat - это класс для поддержки более ранних версий. Она поддерживает минимальный уровень API 19.
// https://stackoverflow.com/questions/52575494/what-differences-between-notificationmanager-and-notificationmanagercompat/59803199#59803199
 * */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val configBuilder = NotificationCompat.Builder(this, "CHANNEL_TEST_ID")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("textTitle")
            .setContentText("textContent")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel("CHANNEL_TEST_ID", "testName", NotificationManager.IMPORTANCE_DEFAULT)

            getNotificationManager().apply {
                createNotificationChannel(notificationChannel)
                notify(1, configBuilder.build())
            }
        } else {
            getNotificationManager().notify(1, configBuilder.build())
        }
    }

    private fun getNotificationManager(): NotificationManagerCompat {
        return NotificationManagerCompat.from(this)
    }
}