package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Button(onClick = {
                            locationRequest()
                        }) {
                            Text(text = "crash")
                        }
                    }
                }
            }
        }
    }

    private fun locationRequest() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(LocationRequest.create())

        val result = LocationServices.getSettingsClient(this)
            .checkLocationSettings(builder.build())

        result
            .addOnCompleteListener { task ->
                runCatching {
                    task.getResult(ApiException::class.java)
                }.onSuccess {

                }.onFailure {
                    val exception = it as? ApiException ?: return@onFailure
                    when (exception.statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            when (exception) {
                                is ResolvableApiException -> {
                                    exception.startResolutionForResult(this, 1000)
                                }
                                else -> throw exception
                            }
                        }

                        else -> Unit
                    }
                }
            }
    }
}
