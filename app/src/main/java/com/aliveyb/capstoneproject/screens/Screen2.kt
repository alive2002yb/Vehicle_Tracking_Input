package com.aliveyb.capstoneproject.screens

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliveyb.capstoneproject.models.Displaydata
import com.aliveyb.capstoneproject.models.MyViewModel
import com.aliveyb.capstoneproject.widgets.Header
import com.aliveyb.capstoneproject.widgets.InputTable

@Preview(showBackground = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen2MainContent(
    info: List<String> = listOf("Logo Info"),
    labels: List<String> = listOf("Name"),
    types: List<String> = listOf("String"),
    main: Context = LocalContext.current
)
{
    Card(modifier = Modifier
        .padding(20.dp)
        .wrapContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(.2.dp, color = Color.Black)
    ){
        Column(modifier = Modifier
            .fillMaxSize().fillMaxHeight()
            .padding(5.dp)
            ,verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Header(info = info)
            Spacer(modifier = Modifier.padding(5.dp))
            val values:List<String> = InputTable(labels = labels, types = types , main)
            Button(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(40.dp),
                    onClick = {
                        if(values.contains(""))
                        {
                            Toast.makeText(main, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            MyViewModel().parseData(values)
                            Toast.makeText(main, "Data sent successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                ) {
                    Text(text = "Submit",fontSize = 17.sp)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen2(
    viewModel: MyViewModel,
    main: Context,
    display: Displaydata
)
{
//    val display: Displaydata = display_fetch(viewModel)
    Screen2MainContent(info = display.info, labels = display.labels, types = display.type , main)
}