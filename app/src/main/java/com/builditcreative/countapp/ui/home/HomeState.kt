package com.builditcreative.countapp.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

data class HomeState(
    val itemList: ArrayList<ListState> = arrayListOf(),
)

data class ListState(
    val title:String = "",
    val count:Long = 0,
    val isSelected:Boolean = false,
    val type:Int = 0,
) {
    var selectedValue: Boolean by mutableStateOf(isSelected)
    var countValue: Long by mutableStateOf(count)
    var typeValue: Int by mutableStateOf(type)
}