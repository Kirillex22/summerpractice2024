package com.example.summerpractice2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}

@Composable
fun paintCharacterPreview(character: Character) {
    Box(modifier = Modifier
        .size(300.dp, 550.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.getLink())
                .crossfade(true)
                .build(),
            contentDescription = "Superhero",
            contentScale = ContentScale.Crop,
            clipToBounds = true,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
        )

        Text(text = character.getName(), color = Color.White, fontSize = 30.sp, modifier = Modifier
            .padding(start = 15.dp, top = 500.dp)
        )
    }
}

@Composable
fun paintCharacterFull(character: Character, navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.getLink())
                .crossfade(true)
                .build(),
            contentDescription = "Superhero",
            contentScale = ContentScale.Crop,
            clipToBounds = true,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
        )

        Text(text = character.getName(), color = Color.White, fontSize = 30.sp, modifier = Modifier
            .padding(start = 15.dp, top = 750.dp)
        )
        Text(text = character.getMessage(), color = Color.White, modifier = Modifier
            .padding(start = 15.dp, top = 800.dp)
        )
        Box(
            //onClick = {navController.navigate("characters_preview")},
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
                .size(32.dp, 28.dp)
                .clickable(onClick = {navController.navigate("characters_preview")}),
        ){
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.left_arrow),
                contentDescription = "Left arrow",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun paintPreview(characters : List<Character>, navController: NavHostController, listState: LazyListState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Black,
                        Color.DarkGray,
                        Color.Red
                    ), start = Offset(666.0f, 666.0f)
                ),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 60.dp)
        ) {
            AsyncImage(
                model = MARVER_LOGO_LINK,
                contentDescription = "Marvel Studios",
                modifier = Modifier
                    .height(27.dp)
                    .width(128.dp)
            )
        }

        Row(modifier = Modifier
            .padding(top = 90.dp)
            .align(Alignment.CenterHorizontally)
        ){
            Text(text = "Choose your hero", color = Color.White)
        }

        LazyRow(
            state = listState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
            modifier = Modifier
                .padding(top = 80.dp, start = 50.dp, end = 50.dp)
        ) {
            items(characters) { character ->
                //var isSelected by remember { mutableStateOf(value = false) }

                Column(modifier = Modifier
                    .clickable(
                        onClick = {
                            navController.navigate(
                                "${
                                    character
                                        .getName()
                                        .lowercase()
                                }_info"
                            )
                        }
                    )
                ){paintCharacterPreview(character)}
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController)
{
    val listState = rememberLazyListState()

    NavHost(
        navController = navController,
        startDestination = "characters_preview"
    ) {

        composable("characters_preview") {
            paintPreview(CHARACTERS, navController, listState)
        }

        composable("deadpool_info"){
            paintCharacterFull(character = CHARACTERS[0], navController)
        }

        composable("iron-man_info"){
            paintCharacterFull(character = CHARACTERS[1], navController)
        }

        composable("spider-man_info"){
            paintCharacterFull(character = CHARACTERS[2], navController)
        }
    }
}