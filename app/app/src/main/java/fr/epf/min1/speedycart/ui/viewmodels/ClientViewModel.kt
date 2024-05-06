package fr.epf.min1.speedycart.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.epf.min1.speedycart.network.SpeedyCartApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ClientUiState {
    data class Success(val clients: String) : ClientUiState
    object Loading : ClientUiState
    object Error : ClientUiState
}

class ClientViewModel : ViewModel() {
    private val _clientUiState = MutableStateFlow<ClientUiState>(ClientUiState.Loading)
    val clientUiState: StateFlow<ClientUiState> = _clientUiState

    init {
        getSpeedyCartClients()
    }

    fun getSpeedyCartClients(){
        viewModelScope.launch {
            _clientUiState.value =
                try {
                    val listResult = SpeedyCartApi.retrofitService.getClients()
                    ClientUiState.Success(listResult)
                } catch (e: IOException) {
                    ClientUiState.Error
                }
        }
    }
}
