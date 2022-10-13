/*
 * Copyright 2022 Koreography Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sagar.koreography

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.sagar_viradiya.LaunchKoreography
import io.github.sagar_viradiya.rememberKoreography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                var state by remember {
                    mutableStateOf(true)
                }
                MainScreen(state = state) {
                    state = !state
                }
            }
        }
    }
}

@Composable
private fun MainScreen(state: Boolean, onClick: () -> Unit) {

    var alpha by remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(0f) }
    var rotate by remember { mutableStateOf(0f) }

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

    LaunchKoreography(state = state) {
        parallelMoves {
            move(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(2000)
            ) { value, _ ->
                alpha = value
            }
            move(
                initialValue = 0f,
                targetValue = 4f,
                animationSpec = tween(2000)
            ) { value, _ ->
                scale = value
            }
            move(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = tween(2000)
            ) { value, _ ->
                rotate = value
            }
        }
        parallelMoves {
            move(
                initialValue = 1f,
                targetValue = 0f,
                animationSpec = tween(2000)
            ) { value, _ ->
                alpha = value
            }
            move(
                initialValue = 4f,
                targetValue = 0f,
                animationSpec = tween(2000)
            ) { value, _ ->
                scale = value
            }
            move(
                initialValue = 360f,
                targetValue = 0f,
                animationSpec = tween(2000)
            ) { value, _ ->
                rotate = value
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()

            .padding(16.dp)
    ) {

        var imageResource by remember {
            mutableStateOf(R.drawable.ic_toy)
        }

        Button(onClick = {
            imageResource = R.drawable.ic_droid
            koreography.dance(scope)
        }) {
            Text("Fade + Scale")
        }
        Button(onClick = {
            imageResource = R.drawable.ic_toy
            onClick()
        }) {
            Text("Fade + Scale + Rotate")
        }
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(imageResource),
                modifier = Modifier
                    .align(Alignment.Center)
                    .scale(scale)
                    .alpha(alpha)
                    .rotate(rotate),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}