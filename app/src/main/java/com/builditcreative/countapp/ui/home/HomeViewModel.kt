package com.builditcreative.countapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {


    private val _state = MutableStateFlow(HomeState(
        arrayListOf(
            ListState(title = "item 1", type = 1),
            ListState(title = "item 2", type = 1),
            ListState(title = "item 3", type = 1),
            ListState(title = "item 4", type = 1),
            ListState(title = "item 5", type = 1),
            ListState(title = "item 6", type = 1),
            ListState(title = "item 7", type = 1),
            ListState(title = "item 8", type = 1),
            ListState(title = "item 9", type = 1),
            ListState(title = "item 10", type = 2),
            ListState(title = "item 11", type = 2),
            ListState(title = "item 12", type = 2),
            ListState(title = "item 13", type = 2),
            ListState(title = "item 14", type = 2),
            ListState(title = "item 14", type = 2),
            ListState(title = "item 16", type = 2),
            ListState(title = "item 17", type = 2),
            ListState(title = "item 18", type = 2)
        )
    ))
    val state = _state.asStateFlow()

    fun onEvent(homeEvent: HomeEvent) {
        when (homeEvent) {

            HomeEvent.MoveToOneClicked -> {
                _state.value.itemList.filter { it.typeValue == 1 && it.selectedValue }.forEach { item ->
                    item.typeValue = 2
                    item.selectedValue = false
                }
            }

            HomeEvent.MoveToTwoClicked -> {
                _state.value.itemList.filter { it.typeValue == 2 && it.selectedValue }.forEach { item ->
                    item.typeValue = 1
                    item.selectedValue = false
                }
            }

            is HomeEvent.UpdateCount -> {
                _state.value.itemList.find { it.title==homeEvent.listState.title }?.let {
                    Log.d("TAG", "onEvent: ${homeEvent.listState.count}")
                    it.countValue = homeEvent.listState.count
                }
            }

            is HomeEvent.UpdateSelected -> {
                _state.value.itemList.find { it.title==homeEvent.listState.title }?.let {
                    Log.d("TAG", "onEvent: ${homeEvent.listState.isSelected}")
                    it.selectedValue = homeEvent.listState.isSelected
                }
            }
        }
    }
}