package com.aliveyb.capstoneproject.components

fun isAscii(s: String): Boolean {
s.forEach {
    if (it.code in 32..126)
    {
        // nothing will happen
    }
    else
    {
        return false
    }
}
    return true
}