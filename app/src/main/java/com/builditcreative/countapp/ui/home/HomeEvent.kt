package com.builditcreative.countapp.ui.home

sealed interface HomeEvent {
    data class UpdateCount(val listState: ListState): HomeEvent
    data class UpdateSelected(val listState: ListState): HomeEvent
    object MoveToOneClicked: HomeEvent
    object MoveToTwoClicked: HomeEvent
}