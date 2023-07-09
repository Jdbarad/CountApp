package com.builditcreative.countapp.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.builditcreative.countapp.R

@Composable
fun HomeScreen(state: HomeState, onEvent: (HomeEvent) -> Unit) {
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(state.itemList.filter { it.typeValue == 1 }) { listState ->
                        ListItem(listState, onEvent)
                    }
                }
                Column(
                    Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { onEvent(HomeEvent.MoveToOneClicked) }) {
                        Text(text = "Move to Right")
                    }
                    Button(onClick = { onEvent(HomeEvent.MoveToTwoClicked) }) {
                        Text(text = "Move to Left")
                    }
                }
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(state.itemList.filter { it.typeValue == 2 }) { listState ->
                        ListItem(listState,onEvent)
                    }
                }
            }
        }
        else -> {
            Column {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    items(state.itemList.filter { it.typeValue == 1 }) { listState ->
                        ListItem(listState, onEvent)
                    }
                }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { onEvent(HomeEvent.MoveToOneClicked) }) {
                        Text(text = "Move to Down")
                    }
                    Button(onClick = { onEvent(HomeEvent.MoveToTwoClicked) }) {
                        Text(text = "Move to Up")
                    }
                }
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    items(state.itemList.filter { it.typeValue == 2 }) { listState ->
                        ListItem(listState,onEvent)
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(state: ListState, onChange: (HomeEvent) -> Unit) {
    Row(
        Modifier
            .then(
                if (state.selectedValue)
                    Modifier
                        .padding(2.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50)
                        )
                else
                    Modifier
                        .padding(2.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(50)
                        )
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .padding(2.dp)
            .clickable {
                onChange(HomeEvent.UpdateSelected(state.copy(isSelected = !state.selectedValue)))
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = state.title, fontSize = TextUnit(16f, TextUnitType.Sp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            IconButton(onClick = { onChange(HomeEvent.UpdateCount(state.copy(count = state.countValue-1))) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = "Minus Button"
                )
            }
            Text(
                text = state.countValue.toString(),
                fontSize = TextUnit(20f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(50.dp)
            )
            IconButton(onClick = { onChange(HomeEvent.UpdateCount(state.copy(count = state.countValue+1))) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Minus Button"
                )
            }
        }
    }
}

@Preview(
    device = "spec:parent=pixel_6a,orientation=landscape", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)

@Composable
fun PreviewHomeScreen() {
    HomeScreen(state = HomeState(), onEvent = {})
}