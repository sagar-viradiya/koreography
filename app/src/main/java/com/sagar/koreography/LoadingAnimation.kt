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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.sagar_viradiya.rememberKoreography

@Composable
fun LoadingAnimation() {
    val scaleXRect1 = remember { Animatable(1f) }

    val scaleXRect2 = remember { Animatable(1f) }

    val scaleYRect1 = remember { Animatable(1f) }

    val scaleYRect2 = remember { Animatable(1f) }

    val rect1HorizontalAlignment = remember { Animatable(-1f) }

    val rect1VerticalAlignment = remember { Animatable(-1f) }

    val rect2HorizontalAlignment = remember { Animatable(1f) }

    val rect2VerticalAlignment = remember { Animatable(1f) }

    val koreography = rememberKoreography {
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect2HorizontalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect1HorizontalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect2VerticalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect1VerticalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect1HorizontalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect2HorizontalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect1VerticalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect2VerticalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).clickable {
                koreography.danceForever(coroutineScope)
            },
    ) {
        Box(
            modifier = Modifier
                .width(208.dp)
                .height(224.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.vector_1),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        BiasAlignment(
                            horizontalBias = rect1HorizontalAlignment.value,
                            verticalBias = rect1VerticalAlignment.value
                        )
                    )
                    .graphicsLayer {
                        scaleX = scaleXRect1.value
                        scaleY = scaleYRect1.value
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.vector_2),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        BiasAlignment(
                            horizontalBias = rect2HorizontalAlignment.value,
                            verticalBias = rect2VerticalAlignment.value
                        )
                    )
                    .graphicsLayer {
                        scaleX = scaleXRect2.value
                        scaleY = scaleYRect2.value
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
            )
        }
    }


}



