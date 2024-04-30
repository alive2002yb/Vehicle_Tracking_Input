package com.aliveyb.capstoneproject.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliveyb.capstoneproject.components.display_fetch
import com.aliveyb.capstoneproject.models.MyViewModel
import com.aliveyb.capstoneproject.models.Displaydata
import com.aliveyb.capstoneproject.widgets.Header
import com.aliveyb.capstoneproject.widgets.DisplayTable

@Composable
fun Screen1MainContent(info:List<String>,
                labels:List<String>,
                values:List<String>
) {
        Card(modifier = Modifier.padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            border = BorderStroke(.2.dp, color = Color.Black)
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Header(info)
                DisplayTable(labels,values)
            }
    }
}

@Composable
fun Screen1(viewModel:MyViewModel, navController: NavController , display: Displaydata)
{
//    val display: Displaydata = display_fetch(viewModel)
    Screen1MainContent(
        info = display.info,
        labels = display.labels,
        values = display.values
    )
}