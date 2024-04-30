package com.aliveyb.capstoneproject.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliveyb.capstoneproject.components.display_fetch
import com.aliveyb.capstoneproject.models.Displaydata
import com.aliveyb.capstoneproject.models.MyViewModel
import com.aliveyb.capstoneproject.screens.HomeScreen
import com.aliveyb.capstoneproject.screens.Screen1
import com.aliveyb.capstoneproject.screens.Screen2
import com.aliveyb.capstoneproject.screens.Screen3
import com.aliveyb.capstoneproject.screens.Screen4
import com.aliveyb.capstoneproject.screens.Screen5

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ApplicationNavigation(
    main: Context,
)
{
    val navController = rememberNavController()
    val viewModel = MyViewModel()
    val display: Displaydata = display_fetch(viewModel)
    val accuracy: Displaydata = display_fetch(viewModel,boolean = true)
    NavHost(navController = navController, startDestination = ApplicationScreens.HomeScreen.name)
                {
                    composable(ApplicationScreens.HomeScreen.name)
                    {
                        //here we pass where this should lead us to
                        HomeScreen(navController=navController)
                    }
                    composable(ApplicationScreens.screen1.name)
                    {
                        Screen1(navController = navController , viewModel = viewModel, display = display)
                    }
                    composable(ApplicationScreens.screen2.name)
                    {
                        Screen2(viewModel = viewModel, main = main, display = display)
                    }
                    composable(ApplicationScreens.screen3.name)
                    {
                        Screen3(navController=navController,viewModel = viewModel, main = main)
                    }
                    composable(ApplicationScreens.screen4.name)
                    {
                        Screen4(navController=navController,viewModel = viewModel, main = main,accuracy = accuracy)
                    }
                    composable(ApplicationScreens.screen5.name)
                    {
                        Screen5(navController=navController,viewModel = viewModel, main = main, display = display)
                    }
                }
}