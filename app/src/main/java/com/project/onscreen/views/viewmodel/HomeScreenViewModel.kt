package com.project.onscreen.views.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.onscreen.data.api.ApiConstants
import com.project.onscreen.domain.usecase.GetEmployeesUseCase
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.OnScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
   @Named(ApiConstants.BASE_URL)
    val getEmployeesUseCase: GetEmployeesUseCase
) : ViewModel() {
    val intentOnScreen = Channel<OnScreenIntent>(Channel.UNLIMITED)
    private val _state = MutableSharedFlow<OnScreenState>()
    val state: SharedFlow<OnScreenState>
        get() = _state

    init {
        handleOperation()
    }

   private  fun handleOperation() {
        viewModelScope.launch {
            intentOnScreen.consumeAsFlow().collect {
                when (it) {
                    is OnScreenIntent.FetchEmployees ->{
                    _state.emit(OnScreenState.LOADING)
                        try {
                            _state.emit(OnScreenState.EMPLOYEES_SUCCESS(getEmployeesUseCase.getEmployees()))
                        }catch (e:Exception){
                            _state.emit(OnScreenState.ERROR(e.localizedMessage))
                        }
                    }
                }
            }
        }

    }



}
