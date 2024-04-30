package com.aliveyb.capstoneproject.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.aliveyb.capstoneproject.models.MyViewModel
import com.aliveyb.capstoneproject.models.Displaydata
import com.aliveyb.capstoneproject.models.VehicleProperty

@Composable
fun display_fetch(viewModel:MyViewModel) : Displaydata {
    var data by remember {
        mutableStateOf<List<VehicleProperty>?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchData { fetchedData ->
            data = fetchedData
        }

    }

    var display = Displaydata(labels = emptyList(), info = emptyList(), values = emptyList(), type = emptyList())
    data?.let { vehicles ->
        val allLabels = mutableListOf<String>()
        val allValues = mutableListOf<String>()
        val allinfo = mutableListOf<String>()
        val alltype = mutableListOf<String>()
        vehicles.forEach{
            vehicle ->
            if (vehicle.type =="Heading")
            {
                allinfo.add(vehicle.label)
            }
            else{
                allLabels.add(vehicle.label)
                allValues.add(vehicle.value)
                alltype.add(vehicle.type)
            }
        }
        Log.d("data","Data is being assigned")
         display = Displaydata(labels = allLabels, info = allinfo, values = allValues, type = alltype)
    }
    return display
}

@Composable
fun display_fetch(viewModel:MyViewModel,boolean: Boolean) : Displaydata {
    var data by remember {
        mutableStateOf<List<VehicleProperty>?>(null)
    }
    LaunchedEffect(Unit) {
        viewModel.getAccuracy { fetchedData ->
            data = fetchedData
        }
    }
    var display = Displaydata(labels = emptyList(), info = emptyList(), values = emptyList(), type = emptyList())
    data?.let { vehicles ->
        val allLabels = mutableListOf<String>()
        val allValues = mutableListOf<String>()
        val allinfo = mutableListOf<String>()
        val alltype = mutableListOf<String>()
        allLabels.add("Accuracy")
        allValues.add("Loss")
        vehicles.forEach{
                vehicle ->
            if (vehicle.type =="Heading")
            {
                allinfo.add(vehicle.label)
            }
            else{
                allLabels.add(vehicle.label)
                allValues.add(vehicle.value)
                alltype.add(vehicle.type)
            }
        }
        Log.d("data","Data is being assigned")
        display = Displaydata(labels = allLabels, info = allinfo, values = allValues, type = alltype)
    }
    return display
}