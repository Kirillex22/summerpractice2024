package com.example.summerpractice2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.summerpractice2024.ui.theme.Summerpractice2024Theme


class Character(name: String, resourcesLink: String, welcomeMessage: String)
{
    private var _name : String = name
    private var _resourcesLink : String = resourcesLink
    private var _welcomeMessage = welcomeMessage

    fun getName() : String {
        return _name
    }

    fun getLink() : String {
        return _resourcesLink
    }

    fun getMessage() : String {
        return _welcomeMessage
    }
}

var MARVER_LOGO_LINK = "https://iili.io/JMnuvbp.png"

var CHARACTERS = listOf(
    Character(
        name = "Deadpool",
        resourcesLink = "https://iili.io/JMnAfIV.png",
        welcomeMessage = "I'm deadpool, baby!"
    ),
    Character(
        name ="Iron-Man",
        resourcesLink = "https://iili.io/JMnuDI2.png",
        welcomeMessage = "I am Iron Man."
    ),
    Character(
        name = "Spider-Man",
        resourcesLink = "https://iili.io/JMnuyB9.png",
        welcomeMessage = "Your friendly neighborhood Spider-Man."
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Summerpractice2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Summerpractice2024Theme {
        Greeting("Android")
    }
}