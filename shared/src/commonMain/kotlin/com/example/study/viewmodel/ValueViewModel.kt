package com.example.study.viewmodel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class Status {
    object Loading : Status()
    data class Error(val message: String) : Status()
    data class Success(val result: SomeData) : Status()
}

open class ValueViewModel : ViewModel(){
   private var _state = MutableStateFlow(ViewState(true))
    var state:StateFlow<ViewState> = _state.asStateFlow()

     fun onButtonClicked() {

        _state.update { it.copy(buttonIsLoading = true) }
        CoroutineScope(Dispatchers.Default).launch(Dispatchers.IO) {
             callToServer()
        _state.update { it.copy(buttonIsLoading = false) }
        }
    }

    private suspend fun callToServer() {
        delay(3000) // We simulate a 3s server call
    }
}