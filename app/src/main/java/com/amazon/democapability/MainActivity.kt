package com.amazon.democapability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amazon.democapability.ui.theme.DemoCapabilityTheme

// safeDrawingPadding() ->
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoCapabilityTheme {
//                Box(modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Yellow)
//                    .safeDrawingPadding()) {
//                    Greeting(name = "Nam")
//                }
//                InsetsDemoScreen()
//                DemoLazyScreen()
//                FormWithLazyColumn()
//                InsetPaddingDemo()
                MyScreen()
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
    DemoCapabilityTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsetsDemoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        TopAppBar(
            title = { Text("Demo Insets") },
            modifier = Modifier.statusBarsPadding()
        )

        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Nhập nội dung...") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .imePadding()           // chừa chỗ cho bàn phím
                .navigationBarsPadding() // chừa chỗ cho navigation bar
                .padding(16.dp)
        )
    }
}

@Composable
fun DemoLazyScreen() {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        // Other content
        item {
            Spacer(
                Modifier
                    .windowInsetsBottomHeight(WindowInsets.systemBars)
                    .fillMaxHeight()
                    .background(Color.Blue)
            )
        }
    }
}

@Composable
fun FormWithLazyColumn() {
    val items = remember { mutableStateListOf("", "", "", "", "","","","","","","") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .safeDrawingPadding()
    ) {
        itemsIndexed(items) { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue -> items[index] = newValue },
                label = { Text("Field #$index") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Spacer chỉ ở cuối danh sách
        item {
            Spacer(
                Modifier
                    .windowInsetsBottomHeight(WindowInsets.systemBars)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun InsetPaddingDemo() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.LightGray)
    ) {
        Spacer(
            Modifier
                .windowInsetsTopHeight(WindowInsets.systemBars)
                .fillMaxWidth()
                .background(Color.Red)
        )

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .consumeWindowInsets(WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
                .padding(16.dp)
        ) {
            repeat(10) { index ->
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Field #$index") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }

            Spacer(
                Modifier
                    .windowInsetsBottomHeight(WindowInsets.ime)
                    .fillMaxWidth()
                    .background(Color.Green)
            )
        }

        Spacer(
            Modifier
                .windowInsetsBottomHeight(WindowInsets.systemBars)
                .fillMaxWidth()
                .background(Color.Blue)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyScreen() {
    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .imeNestedScroll(),
            content = { }
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp) // normal 16dp of padding for FABs
                .navigationBarsPadding() // padding for navigation bar
                .imePadding(), // padding for when IME appears
            onClick = { }
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }
}
