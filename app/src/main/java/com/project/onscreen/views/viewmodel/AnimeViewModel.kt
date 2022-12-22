package com.project.onscreen.views.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.onscreen.data.usecase.GetAnimesUseCaseImpl
import com.project.onscreen.data.utils.ApiConstants
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.AnimeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class AnimeViewModel @Inject constructor(@Named(ApiConstants.BASE_URL_ANIME) var getAnimesUseCase: GetAnimesUseCaseImpl) :
    ViewModel() {
    var intentOnScreen = Channel<OnScreenIntent>(Channel.UNLIMITED)
    var _state = MutableSharedFlow<AnimeState>()
    val state: SharedFlow<AnimeState>
        get() = _state
    val title = MutableLiveData<String?>()

    init {
        handleOperation()
    }

    @OptIn(FlowPreview::class)
    private fun handleOperation() {
        viewModelScope.launch {
            intentOnScreen.consumeAsFlow().collect {
                when (it) {
                    is OnScreenIntent.FetchAnimes -> {
                        _state.emit(AnimeState.LOADING)
                        try {
                            delay(500)
                            val result = convertToList()
                            _state.emit(AnimeState.SUCCESS(result))
                        } catch (e: Exception) {
                            _state.emit(AnimeState.ERROR(e.message))
                        }
                    }
                    else -> {
                        //no ops
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private suspend fun convertToList() =
        getAnimesUseCase.getAnimes(title.value.toString()).flatMapConcat {
            it.asFlow()
        }.toList()

    val onQueryTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                title.value = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }

}