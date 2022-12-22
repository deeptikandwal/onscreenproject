package com.project.onscreen.views.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.onscreen.domain.usecase.GetEmployeesUseCaseImpl
import com.project.onscreen.data.utils.ApiConstants
import com.project.onscreen.domain.model.EmployeeDomainModel
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.OnScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    @Named(ApiConstants.BASE_URL)
    var getEmployeesUseCase: GetEmployeesUseCaseImpl
) : ViewModel() {
    lateinit var result:List<EmployeeDomainModel>
    var intentOnScreen = Channel<OnScreenIntent>(Channel.UNLIMITED)
    private val _state = MutableSharedFlow<OnScreenState>()
    val state: SharedFlow<OnScreenState>
        get() = _state

    init {
        handleOperation()
    }

    @OptIn(FlowPreview::class)
    private fun handleOperation() {
        viewModelScope.launch {
            intentOnScreen.send(OnScreenIntent.FetchEmployees)
            intentOnScreen.consumeAsFlow().collect {
                when (it) {
                    is OnScreenIntent.FetchEmployees -> {
                        _state.emit(OnScreenState.LOADING)
                        try {
                            delay(500)
                             result = convertToList()
                            _state.emit(OnScreenState.SUCCESS(result))
                        } catch (e: Exception) {
                            _state.emit(OnScreenState.ERROR(e.localizedMessage))
                        }
                    }
                }
            }
        }

    }

    @OptIn(FlowPreview::class)
    private suspend fun convertToList() =
        getEmployeesUseCase.getEmployees().flatMapConcat { it.asFlow() }.toList()

}
