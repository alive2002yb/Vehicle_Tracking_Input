package com.aliveyb.capstoneproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliveyb.capstoneproject.navigation.ApplicationScreens

//@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)){

            Surface(color = Color.White)
            {
                Text("This is the Homescreen")
            }
            Button(onClick = {
                navController.navigate(route = ApplicationScreens.screen1.name)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                contentColor = Color.Black),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Display")
            }
            Button(onClick = {
                navController.navigate(route = ApplicationScreens.screen2.name)
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                    contentColor = Color.Black),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Input Values")
            }
            Button(onClick = {
                navController.navigate(route = ApplicationScreens.screen3.name)
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                    contentColor = Color.Black),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Get Accuracy and Predict Values")
            }
        }
}