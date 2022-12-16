package com.project.onscreen.views.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.onscreen.data.api.ApiConstants
import com.project.onscreen.data.domain.usecase.GetAnimesUseCase
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.OnScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AnimeViewModel @Inject constructor(@Named(ApiConstants.BASE_URL_ANIME) var getAnimesUseCase: GetAnimesUseCase) :
    ViewModel() {
    val intentOnScreen = Channel<OnScreenIntent>(Channel.UNLIMITED)
    private val _state = MutableSharedFlow<OnScreenState>()
    val state: SharedFlow<OnScreenState>
        get() = _state
    val title = MutableLiveData<String>()

    init {
        handleOperation()
    }

    fun handleOperation() {
        viewModelScope.launch {
            intentOnScreen.consumeAsFlow().collect {
                when (it) {
                    is OnScreenIntent.FetchAnimes -> {
                        _state.emit(OnScreenState.LOADING)
                        try {
                            _state.emit(OnScreenState.SUCCESS(getAnimesUseCase.getAnimes(title.value.toString())))
                        } catch (e: Exception) {
                            _state.emit(OnScreenState.ERROR(e.message))
                        }
                    }
                }
            }
        }
    }

    @VisibleForTesting
    fun setUseCase(getAnimesUseCase: GetAnimesUseCase) {
        this.getAnimesUseCase = getAnimesUseCase
    }

}