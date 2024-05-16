package fr.epf.min1.speedycart.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.epf.min1.speedycart.R
import fr.epf.min1.speedycart.data.Client
import fr.epf.min1.speedycart.ui.viewmodels.ClientUiState
import fr.epf.min1.speedycart.ui.viewmodels.ClientViewModel

class ClientAccountActivity : AppCompatActivity() {
    private lateinit var viewModel: ClientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_account)

        val lastnameTextView = findViewById<TextView>(R.id.client_account_last_name)
        val firstnameTextView = findViewById<TextView>(R.id.client_account_first_name)

        viewModel = ViewModelProvider(this)[ClientViewModel::class.java]

        // Observer clientUiState
        lifecycleScope.launchWhenStarted {
            viewModel.clientUiState.collect { state ->
                when (state) {
                    is ClientUiState.Success -> {
                        var json = state.clients

                        val type = object : TypeToken<List<Client>>() {}.type
                        var data = Gson().fromJson<List<Client>>(json, type)
                        lastnameTextView.text = data.get(0).lastname
                        firstnameTextView.text = data.get(0).firstname
                    }

                    is ClientUiState.Loading -> {
                        lastnameTextView.text = getString(R.string.loading)
                        firstnameTextView.text = getString(R.string.loading)
                    }

                    is ClientUiState.Error -> {
                        lastnameTextView.text = getString(R.string.error)
                        firstnameTextView.text = getString(R.string.error)
                    }
                }
            }
        }

        // Trigger API call
        viewModel.getSpeedyCartClients()
    }
}