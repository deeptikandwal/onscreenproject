package com.project.onscreen.views.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.onscreen.data.api.ApiConstants
import com.project.onscreen.domain.usecase.GetAnimesUseCase
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.AnimeState
import com.project.onscreen.views.viewState.OnScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AnimeViewModel @Inject constructor(@Named(ApiConstants.BASE_URL_ANIME) var getAnimesUseCase: GetAnimesUseCase) :
    ViewModel() {
    val intentOnScreen = Channel<OnScreenIntent>(Channel.UNLIMITED)
    private val _state = MutableSharedFlow<AnimeState>()
    val state: SharedFlow<AnimeState>
        get() = _state
    val title = MutableLiveData<String>()

    init {
        handleOperation()
    }

   private fun handleOperation() {
        viewModelScope.launch {
            intentOnScreen.consumeAsFlow().collect {
                when (it) {
                    is OnScreenIntent.FetchAnimes -> {
                        _state.emit(AnimeState.LOADING)
                        try {
                            delay(500)
                            _state.emit(AnimeState.SUCCESS(getAnimesUseCase.getAnimes(title.value.toString())))
                        } catch (e: Exception) {
                            _state.emit(AnimeState.ERROR(e.message))
                        }
                    }
                }
            }
        }
    }

}