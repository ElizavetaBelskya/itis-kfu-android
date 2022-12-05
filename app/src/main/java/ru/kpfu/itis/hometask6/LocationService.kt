package ru.kpfu.itis.hometask6

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.*


class LocationService : Service() {

    private var notificationHandler: NotificationHandler? = null

    private var coordinates: String = "Waiting for coordinates"

    private var locationManager: LocationManager? = null

    override fun onCreate() {
        super.onCreate()
        notificationHandler = NotificationHandler(applicationContext)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    fun isLocationEnabled(): Boolean{
        return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isLocationEnabled()) {
            var location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            coordinates = "You Current Location is : Long: "+ location?.longitude + " , Lat: " + location?.latitude
            showNotification("Location", coordinates)
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                UPDATE_TIMEOUT,
                MIN_DISTANCE
            ) { location ->
                coordinates = "You Current Location is : Long: "+ location.longitude + " , Lat: " + location.latitude
                editNotification("Location", coordinates)
            }
        } else {
            showNotification("Location", coordinates)
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        notificationHandler = null
        locationManager = null
    }

    private fun showNotification(title: String, shortText: String) {
        val notification = notificationHandler?.createNotification(title, shortText)
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun editNotification(title: String, shortText: String) {
        val notification = notificationHandler?.createNotification(title, shortText)
        notificationHandler?.notify(NOTIFICATION_ID, notification)
    }


    companion object {
        const val UPDATE_TIMEOUT = 5000L
        const val NOTIFICATION_ID = 101
        const val MIN_DISTANCE = 0f
    }


}
