package com.example.cashenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cashenger.ui.theme.CashengerTheme
import com.example.cashenger.views.features.chat.ChatScreen
import com.example.cashenger.views.features.navigation.CashengerNavigation

class MainActivity : ComponentActivity() {
    
    private lateinit var navController: NavHostController
    
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashengerTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    CashengerNavigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CashengerTheme {
        Greeting("Android")
    }
}