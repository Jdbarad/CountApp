package com.builditcreative.countapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.builditcreative.countapp.ui.home.HomeScreen
import com.builditcreative.countapp.ui.home.HomeViewModel
import com.builditcreative.countapp.ui.theme.CountAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val homeViewModel by viewModels<HomeViewModel>()
                    HomeScreen(
                        state = homeViewModel.state.collectAsState().value,
                        onEvent = {
                            homeViewModel.onEvent(it)
                        }
                    )
                }
            }
        }
    }
}