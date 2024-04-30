package com.aliveyb.capstoneproject.navigation

enum class ApplicationScreens {
    HomeScreen,
    screen1,
    screen2,
    screen3,
    screen4,
    screen5;
    companion object{
        fun fromRoute(route:String?):ApplicationScreens
        = when(route?.substringBefore("/")){
            HomeScreen.name->HomeScreen
            screen1.name -> screen1
            screen2.name -> screen2
            screen3.name -> screen3
            screen4.name -> screen4
            screen5.name -> screen5
            null -> HomeScreen
            else -> throw  IllegalArgumentException("Route $route is not recognised")
        }
    }
}