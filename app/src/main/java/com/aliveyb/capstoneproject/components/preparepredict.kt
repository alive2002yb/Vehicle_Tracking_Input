package com.aliveyb.capstoneproject.components


fun preparepredict(labels: List<String>,types : List<String>,values: List<String>) : MutableMap<String,Any>
{
    val vehiclemap = mutableMapOf<String, Any>()
                for (i in values.indices) {
                    if (values[i] != "") {
                        if (types[i] == "Numerical") {
                            vehiclemap[labels[i]] = values[i].toDouble()
                        }
                        if (types[i] == "Date") {
                            vehiclemap[labels[i]] = values[i]
                        }
                        if (types[i] == "Time") {
                            vehiclemap[labels[i]] = values[i]
                        }
                        if (types[i] == "Location") {
                            vehiclemap[labels[i]] = values[i].toDouble()
                        } else {
                            vehiclemap[labels[i]] = values[i]
                        }
                    }
                    else
                    {
                        vehiclemap[labels[i]] = 0
                    }
                }
    return vehiclemap
}