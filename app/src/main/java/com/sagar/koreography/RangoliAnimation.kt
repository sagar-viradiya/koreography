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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.sagar_viradiya.rememberKoreography

@Composable
fun RangoliAnimation() {
    val rotationLayer1 = remember {
        Animatable(0f)
    }
    val rotationLayer2 = remember {
        Animatable(0f)
    }
    val rotationLayer3 = remember {
        Animatable(0f)
    }

    val koreography = rememberKoreography {
        move(rotationLayer3, 90f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer2, 90f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer1, 90f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer3, 180f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer2, 180f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer1, 180f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer3, 90f, animationSpec = tween(durationMillis = 200))
        parallelMoves {
            move(rotationLayer2, 90f, animationSpec = tween(durationMillis = 700))
            move(rotationLayer1, 0f, animationSpec = tween(durationMillis = 1000))
        }
        parallelMoves {
            move(rotationLayer2, 0f, animationSpec = tween(durationMillis = 500))
            move(rotationLayer3, -90f, animationSpec = tween(durationMillis = 500))
            move(rotationLayer1, -90f, animationSpec = tween(durationMillis = 500))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.rangoli_base),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer { rotationZ = rotationLayer1.value },
            painter = painterResource(id = R.drawable.rangoli_layer_1),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer { rotationZ = rotationLayer2.value },
            painter = painterResource(id = R.drawable.rangoli_layer_2),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer { rotationZ = rotationLayer3.value },
            painter = painterResource(id = R.drawable.rangoli_layer_3),
            contentDescription = null
        )
    }

    LaunchedEffect(true) {
        koreography.danceForever(this)
    }
}
