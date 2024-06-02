package fr.epf.min1.speedycart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import kotlinx.coroutines.runBlocking

const val TAG = "ClientAccountActivity"

class ClientAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_account)

        val lastnameTextView = findViewById<TextView>(R.id.client_account_last_name)
        val firstnameTextView = findViewById<TextView>(R.id.client_account_first_name)
        lastnameTextView.text = "loading"
        firstnameTextView.text = "loading"

        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        runBlocking {
            try {
                val response = clientService.getUsers()
                if (response.isSuccessful && response.body() != null) {
                    val users = response.body()!!
                    Log.d(TAG, "$users")
                    for (user in users) {
                        if (user.client != null) {
                            lastnameTextView.text = user.client.lastname
                            firstnameTextView.text = user.client.firstname
                            break
                        }
                    }

                } else {
                    lastnameTextView.text = "error"
                    firstnameTextView.text = "error"
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                lastnameTextView.text = "error"
                firstnameTextView.text = "error"
            }
        }
    }
}