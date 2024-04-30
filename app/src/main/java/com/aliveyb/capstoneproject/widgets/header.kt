package com.aliveyb.capstoneproject.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliveyb.capstoneproject.models.Displaydata

@Composable
fun Header(info:List<String>)
{

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        userScrollEnabled = true
    ) {
        items(info) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Topheader(amount: MutableState<String> = remember {
    mutableStateOf("0.0")
}, label: String = "Label" , onclick : () ->Unit = {} ) {
    Surface(modifier = Modifier
        .height(150.dp)
        .fillMaxWidth()
        .padding(10.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
        .clickable {
            onclick()
        },
        color = Color(99, 141, 255, 255)
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = label, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Center)
            Text(text = amount.value, modifier = Modifier.fillMaxWidth(), fontSize = 40.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, textAlign = TextAlign.Center)
        }
    }
}