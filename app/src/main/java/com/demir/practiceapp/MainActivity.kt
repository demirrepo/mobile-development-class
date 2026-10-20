package com.demir.practiceapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demir.practiceapp.mvc.view.MvcActivity
import com.demir.practiceapp.mvi.view.MviActivity
import com.demir.practiceapp.mvp.view.MvpActivity
import com.demir.practiceapp.mvvm.view.MvvmActivity
import com.demir.practiceapp.ui.theme.PracticeAppTheme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Launcher(onOpen = { target -> startActivity(Intent(this, target)) })
            }
        }
    }
}

@Composable
private fun Launcher(onOpen: (Class<*>) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Weather: four patterns", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = { onOpen(MvcActivity::class.java) }) { Text("MVC") }
        Button(onClick = { onOpen(MvpActivity::class.java) }) { Text("MVP") }
        Button(onClick = { onOpen(MvvmActivity::class.java) }) { Text("MVVM") }
        Button(onClick = { onOpen(MviActivity::class.java) }) { Text("MVI") }
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
    PracticeAppTheme {
        Greeting("Android")
    }
}