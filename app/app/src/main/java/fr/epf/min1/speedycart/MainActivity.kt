package fr.epf.min1.speedycart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import fr.epf.min1.speedycart.ui.viewmodels.ClientUiState
import fr.epf.min1.speedycart.ui.viewmodels.ClientViewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ClientViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.textView)
        val firstnameTextView = findViewById<TextView>(R.id.textView2)

        viewModel = ViewModelProvider(this)[ClientViewModel::class.java]

        // Observer clientUiState
        lifecycleScope.launchWhenStarted {
            viewModel.clientUiState.collect { state ->
                when (state) {
                    is ClientUiState.Success -> {
                        nameTextView.text = state.clients
                    }
                    is ClientUiState.Loading -> {
                        nameTextView.text = "Loading..."
                    }
                    is ClientUiState.Error -> {
                        nameTextView.text = "Error"
                    }
                }
            }
        }

        // Trigger API call
        viewModel.getSpeedyCartClients()
        firstnameTextView.text = "llllll\n llll"
    }
}