package com.sagar.koreography

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import io.github.sagar_viradiya.rememberKoreography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {

    var alpha by remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(0f) }

    val scope = rememberCoroutineScope()

    val koreography = rememberKoreography {
        parallelMoves {
            move(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(500)
            ) { value, _ ->
                alpha = value
            }
            move(
                initialValue = 0f,
                targetValue = 2f,
                animationSpec = tween(500)
            ) { value, _ ->
                scale = value
            }
        }
        parallelMoves {
            move(
                initialValue = 1f,
                targetValue = 0f,
                animationSpec = tween(500)
            ) { value, _ ->
                alpha = value
            }
            move(
                initialValue = 2f,
                targetValue = 4f,
                animationSpec = tween(500)
            ) { value, _ ->
                scale = value
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .clickable {
            koreography.dance(scope)
        }) {
        Image(
            painter = painterResource(R.drawable.ic_droid),
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale)
                .alpha(alpha),
            contentDescription = null
        )
    }
}