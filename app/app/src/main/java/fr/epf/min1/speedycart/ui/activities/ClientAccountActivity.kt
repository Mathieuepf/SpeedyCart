package fr.epf.min1.speedycart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Client
import fr.epf.min1.speedycart.data.User
import fr.epf.min1.speedycart.network.Retrofit
import fr.epf.min1.speedycart.network.SpeedyCartApiService
import kotlinx.coroutines.runBlocking

private const val TAG = "ClientAccountActivity"

class ClientAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_account)

        val lastnameTextView = findViewById<TextView>(R.id.client_account_last_name)
        val firstnameTextView = findViewById<TextView>(R.id.client_account_first_name)

        val user = tryfetchClient(1L)
        if (user != null) {
            lastnameTextView.text = user.client!!.lastname
            firstnameTextView.text = user.client!!.firstname
        } else {
            lastnameTextView.text = "error"
            firstnameTextView.text = "error"
        }
    }

    private fun tryfetchClient(id: Long): User? {
        val clientService = Retrofit
            .getInstance()
            .create(SpeedyCartApiService::class.java)

        return runBlocking {
            try {
                val response = clientService.getUserById(id)
                if (response.isSuccessful
                    && response.body() != null
                    && response.body()!!.client != null
                ) {
                    response.body()!!
                } else {
                    null
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                null
            }
        }
    }
}