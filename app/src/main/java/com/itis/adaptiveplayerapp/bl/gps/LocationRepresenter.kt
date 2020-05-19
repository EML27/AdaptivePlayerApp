package com.itis.adaptiveplayerapp.bl.gps

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.ContextCompat.getSystemService
import com.itis.adaptiveplayerapp.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepresenter @Inject constructor() : LocationListener {
    fun getCurrentLocation(): Location {
        val context = App.getInstance()
        var lm = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            lm.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                60000,
                10.toFloat(),
                this
            )
        }
        return lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkLocationPermissions(context: Context): Boolean {

        return !(checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED)
    }

    private fun checkAndAskForPermissions(activity: Activity) {
        if (!checkLocationPermissions(activity)
        ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            activity.requestPermissions(permissions, 0)
        }
    }

    override fun onLocationChanged(p0: Location?) {
        TODO("Not yet implemented")
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("Not yet implemented")
    }

    companion object {
        fun checkAndAskForPermissions(activity: Activity) {
            if ((checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(
                            activity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED)
            ) {
                val permissions = arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                activity.requestPermissions(permissions, 0)
            }
        }
    }


}