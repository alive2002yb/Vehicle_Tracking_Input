package com.aliveyb.capstoneproject.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliveyb.capstoneproject.models.MyViewModel
import com.aliveyb.capstoneproject.navigation.ApplicationScreens

@Composable
fun Screen3(navController: NavController, viewModel: MyViewModel, main: Context)
{
    Column (modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)
        .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)){

        Surface(color = Color.White)
        {
            Text("Model Training")
        }

        Button(onClick = {
            navController.navigate(route = ApplicationScreens.screen4.name)
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Get Accuracy")
        }
        Button(onClick = {
            navController.navigate(route = ApplicationScreens.screen5.name)
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Predict Values")
        }
    }
}