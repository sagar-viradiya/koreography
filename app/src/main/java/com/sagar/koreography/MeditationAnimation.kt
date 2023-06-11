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
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.sagar_viradiya.rememberKoreography

@Composable
fun MeditationAnimation() {
    val offsetYMeditation = remember { Animatable(0f) }
    val offsetYMeditationAura = remember { Animatable(0f) }
    val scaleAura = remember { Animatable(1f) }
    val scaleShadow = remember { Animatable(1f) }
    val koreography = rememberKoreography {
        parallelMoves {
            move(
                animatable = offsetYMeditation,
                targetValue = -160f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = offsetYMeditationAura,
                targetValue = -80f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = scaleAura,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = scaleShadow,
                targetValue = 0.5f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 16.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(64.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.meditation_aura),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            translationY = offsetYMeditationAura.value
                            scaleX = scaleAura.value
                            scaleY = scaleAura.value
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(R.drawable.meditation_shadow),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            scaleX = scaleShadow.value
                            transformOrigin = TransformOrigin(0.5f, 0.8f)
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(R.drawable.meditation),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            translationY = offsetYMeditation.value
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    LaunchedEffect(true) {
        koreography.danceForever(this)
    }
}
