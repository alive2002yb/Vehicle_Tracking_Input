package com.aliveyb.capstoneproject.screens

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aliveyb.capstoneproject.components.preparepredict
import com.aliveyb.capstoneproject.models.Displaydata
import com.aliveyb.capstoneproject.models.MyViewModel
import com.aliveyb.capstoneproject.models.predictionViewModel
import com.aliveyb.capstoneproject.widgets.InputTable
import com.aliveyb.capstoneproject.widgets.Topheader
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen5(navController: NavHostController, viewModel: MyViewModel, main: Context,display: Displaydata)
{
    val prediction = remember { mutableStateOf("Tap Here") }
    val predict = predictionViewModel()
    val newdisplay = Displaydata(labels = display.labels.dropLast(1), values = display.values.dropLast(1), info = display.info.dropLast(1), type= display.type.dropLast(1))
    Card(modifier = Modifier
        .padding(20.dp)
        .wrapContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(.2.dp, color = Color.Black)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(5.dp)
            ,verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(5.dp))
            Topheader(amount = prediction, "Predicted : " +display.labels.drop(display.labels.size-1).first())
            {
                prediction.value = predict.getPrediction().predict.toString().slice(indices = IntRange(0,6))
            }
            val values:List<String> = InputTable(labels = newdisplay.labels, types = newdisplay.type)
            val vehiclemap = preparepredict(labels = newdisplay.labels,values = values, types = newdisplay.type)
            Row (
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Button(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(40.dp),
                    onClick = {
                        predict.parseData(map = vehiclemap)
                    }
                ) {
                    Text(text = "Submit",fontSize = 17.sp)
                }
            }
        }
    }
}