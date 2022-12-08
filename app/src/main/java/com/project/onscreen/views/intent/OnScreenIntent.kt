package com.project.onscreen.views.intent

sealed class OnScreenIntent{
        object FetchEmployees: OnScreenIntent()
        object FetchAnimes: OnScreenIntent()
}
