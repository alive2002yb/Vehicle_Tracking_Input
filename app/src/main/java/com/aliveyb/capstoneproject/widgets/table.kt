package com.aliveyb.capstoneproject.widgets

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aliveyb.capstoneproject.models.DateUtils
import com.aliveyb.capstoneproject.components.isAscii
import com.aliveyb.capstoneproject.models.VehicleItem

@Composable
fun DisplayTable(labels:List<String> ,
                 values:List<String>
)
{
    val vehicleItems = mutableListOf<VehicleItem>()
    for (i in labels.indices) {
        val label = labels[i]
        val value = values[i]
        vehicleItems.add(VehicleItem(label = label, value = value))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        LazyColumn(modifier = Modifier
            .weight(1f)
            .height(400.dp),
            userScrollEnabled = true) {
            items(vehicleItems) { item ->
                Row (modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.label,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = item.value,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun InputTable(
    labels: List<String> = listOf("hello","namaste","Date","mei","kya"),
    types: List<String> = listOf("Numerical","String","Date","Time","Location"),
    main: Context = LocalContext.current
): List<String>
{
    val focusManager = LocalFocusManager.current
    val inputValues = remember { mutableStateListOf<String>() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(5.dp)
    ) {
        val vehicleItems = mutableListOf<VehicleItem>()
        for (i in labels.indices) {
            val label = labels[i]
            val value = types[i]
            vehicleItems.add(VehicleItem(label = label, value = value))
        }
        for (i in 1..labels.size) {
            inputValues.add("")
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .height(400.dp),
            userScrollEnabled = true
        ) {
            itemsIndexed(vehicleItems) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.label,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    when (item.value) {
                        "Numerical" -> {
                            TextField(
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        if (
                                            !inputValues[index].matches("[0-9 fgfd.]+".toRegex())
                                        ) {
                                            // inputValues contains non-numeric characters
                                            Toast.makeText(main, "Input should contain only numerical text, including decimals and commas", Toast.LENGTH_SHORT).show();
                                        }
                                    else{
                                        focusManager.moveFocus(FocusDirection.Next)
                                    }
                                    }
                                ),
                                value = inputValues.getOrNull(index) ?: "",
                                onValueChange = { newValue ->

                                    if (index < inputValues.size) {
                                        inputValues[index] = newValue
                                    } else {
                                        inputValues.add(newValue)
                                    }
                                },
                            )
                        }
//                        "Location"->{
//                            Row(
//                                modifier = Modifier.fillMaxSize().clickable {
//
//                                },
//                                horizontalArrangement = Arrangement.Absolute.Center
//                            )
//                            {
//                                Text(text = "Current Location")
//                                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Current Location", modifier = Modifier.height(15.dp).wrapContentWidth())
//                            }
//                        }
                        "Date" -> {
                            val dateState = rememberDatePickerState()
                            val millisToLocalDate = dateState.selectedDateMillis?.let {
                                DateUtils().convertMillisToLocalDate(it)
                            }
                            val dateToString = millisToLocalDate?.let {
                                DateUtils().dateToString(millisToLocalDate)
                            } ?: "Choose Date"

                            var showDialog by remember { mutableStateOf(false) }
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(onClick = {
                                            showDialog = true
                                        }),
                                    text = dateToString,
                                    textAlign = TextAlign.Center
                                )
                                if (showDialog) {
                                    DatePickerDialog(
                                        onDismissRequest = { showDialog = false },
                                        confirmButton = {
                                            Button(
                                                onClick = {
                                                    showDialog = false
                                                    if (index < inputValues.size) {
                                                        inputValues[index] = dateToString
                                                    } else {
                                                        inputValues.add(dateToString)
                                                    }
                                                }
                                            ) {
                                                Text(text = "OK")
                                            }
                                        },
                                        dismissButton = {
                                            Button(
                                                onClick = {
                                                    showDialog = false
                                                }
                                            ) {
                                                Text(text = "Cancel")
                                            }
                                        }
                                    ) {
                                        DatePicker(
                                            state = dateState,
                                            showModeToggle = true
                                        )
                                    }
                                }
                            }
                        }
                        "String" ->
                        {
                            TextField(
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(onAny = {
                                        if (!isAscii(inputValues[index])) {
                                            Toast.makeText(main, "Input should contain only printable characters", Toast.LENGTH_SHORT).show()
                                        }
                                }),
                                value = inputValues.getOrNull(index) ?: "",
                                onValueChange = { newValue ->
                                    if (index < inputValues.size) {
                                        inputValues[index] = newValue
                                    } else {
                                        inputValues.add(newValue)
                                    }

                                },
                            )
                        }
                        "Time" -> {
                            val timePickerState = rememberTimePickerState(
                                initialHour = 0,
                                initialMinute = 0,
                            )
                            val timetoString = rememberSaveable {
                                mutableStateOf("Choose Time")
                            }
                            var showTimePicker = remember { mutableStateOf(false) }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(onClick = {
                                        showTimePicker.value = true
                                    }),
                                text = timetoString.value,
                                textAlign = TextAlign.Center
                            )
                            if (showTimePicker.value) {
                                TimePickerDialog(
                                    onDismissRequest = {
                                        showTimePicker.value = false
                                        timetoString.value = "${timePickerState.hour}:${timePickerState.minute}"
                                                       },
                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                showTimePicker.value = false
                                                timetoString.value = "${timePickerState.hour}:${timePickerState.minute}"
                                                if (index < inputValues.size) {
                                                    inputValues[index] = timetoString.value
                                                } else {
                                                    inputValues.add(timetoString.value)
                                                }
                                            }
                                        ) { Text("OK") }
                                    },
                                    dismissButton = {
                                        TextButton(
                                            onClick = {
                                                showTimePicker.value = false
                                            }
                                        ) { Text("Cancel") }
                                    },
                                )
                                {
                                    TimePicker(
                                        state = timePickerState,
                                        colors = TimePickerDefaults.colors(
                                            clockDialColor = Color.White,
                                            periodSelectorSelectedContainerColor = Color.White,
                                            periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.background,
                                            containerColor = Color.White,
                                            timeSelectorSelectedContainerColor = Color.White,
                                            timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.background
                                        )
                                    )
                                }
                            }
                        }
                        else ->
                        {
                                TextField(
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    ),
                                    label = { Text(text = "Default Input Type String")},
                                    keyboardActions = KeyboardActions(onAny = {
                                        focusManager.moveFocus(FocusDirection.Next)
                                    }),
                                    value = inputValues.getOrNull(index) ?: "",
                                    onValueChange = { newValue ->
                                        if (index < inputValues.size) {
                                            inputValues[index] = newValue
                                        } else {
                                            inputValues.add(newValue)
                                        }

                                    },
                                )
                        }
                        }
                    }
                }
            }
        }
    return inputValues.slice(indices = IntRange(0,labels.size-1))
}

