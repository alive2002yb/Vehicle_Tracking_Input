package com.aliveyb.capstoneproject.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aliveyb.capstoneproject.models.Displaydata
import com.aliveyb.capstoneproject.models.MyViewModel
import com.aliveyb.capstoneproject.widgets.DisplayTable
import com.aliveyb.capstoneproject.widgets.Topheader

@Composable
fun Screen4(navController: NavHostController, viewModel: MyViewModel, main: Context,accuracy :Displaydata)
{

    Screen4MainContent(labels = accuracy.labels, values = accuracy.values )
}

@Composable
fun Screen4MainContent(
                       labels:List<String>,
                       values:List<String>
) {
    Card(modifier = Modifier.padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(.2.dp, color = Color.Black)
    ) {
        val accuracy = remember { mutableStateOf("0.0") }
        accuracy.value = values.last().slice(indices = IntRange(0,6))
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Topheader(amount = accuracy, label = labels.last())
            DisplayTable(labels.dropLast(1),values.dropLast(1))
        }
    }
}

