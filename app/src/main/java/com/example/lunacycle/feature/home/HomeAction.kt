package com.example.lunacycle.feature.home

sealed interface HomeAction {
    data object RegisterSymptoms : HomeAction
    data object RegisterWellbeing : HomeAction
    data object OpenCalendar : HomeAction
}
