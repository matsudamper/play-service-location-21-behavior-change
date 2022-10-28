package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.Assert.fail

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
                            Text(text = "Location Request")
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = {
                            presentedTest()
                        }) {
                            Text(text = "Presented test")
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = {
                            passedTest()
                        }) {
                            Text(text = "passed test in my environment")
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

    /**
     * https://issuetracker.google.com/issues/256046114#comment3
     * fails in my environment
     */
    private fun presentedTest() {
        val settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
        settingsClient
            .checkLocationSettings(
                LocationSettingsRequest.Builder()
                    .addLocationRequest(
                        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 0).build()
                    )
                    .build()
            )
            .addOnCompleteListener { task ->
                try {
                    task.getResult(ApiException::class.java)
                    fail()
                } catch (e: ApiException) {
                    assertThat(e).isInstanceOf(ResolvableApiException::class.java) // fail
                    assertThat(task.exception).isEqualTo(e)
                    val apiException = e as ResolvableApiException
                    assertThat(apiException.status.statusCode).isEqualTo(ConnectionResult.RESOLUTION_REQUIRED)
                    assertThat(apiException.resolution).isNotNull
                }
            }
    }

    /**
     * It will pass this test in my environment.
     */
    private fun passedTest() {
        val settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
        settingsClient
            .checkLocationSettings(
                LocationSettingsRequest.Builder()
                    .addLocationRequest(
                        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 0).build()
                    )
                    .build()
            )
            .addOnCompleteListener { task ->
                try {
                    task.getResult(ApiException::class.java)
                    fail()
                } catch (e: ApiException) {
                    assertThat(e).isNotInstanceOf(ResolvableApiException::class.java)
                    assertThat(task.exception).isEqualTo(e)
                    val apiException = e/* as ResolvableApiException */
                    assertThat(apiException.status.statusCode).isEqualTo(ConnectionResult.RESOLUTION_REQUIRED)
                    assertThat(apiException.status.resolution).isNotNull
                }
            }
    }
}
