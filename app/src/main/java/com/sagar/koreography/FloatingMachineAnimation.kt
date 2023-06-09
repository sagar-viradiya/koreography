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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.sagar_viradiya.rememberKoreography

@Composable
fun FloatingMachineAnimation() {

    val squareScale = remember {
        Animatable(1f)
    }

    val shadowScale = remember {
        Animatable(1f)
    }

    val codePanelScale = remember {
        Animatable(0f)
    }

    val laptopTranslationY = remember {
        Animatable(0f)
    }

    val key1Alpha = remember {
        Animatable(0f)
    }

    val key2Alpha = remember {
        Animatable(0f)
    }

    val key3Alpha = remember {
        Animatable(0f)
    }

    val key4Alpha = remember {
        Animatable(0f)
    }

    val key5Alpha = remember {
        Animatable(0f)
    }

    val key6Alpha = remember {
        Animatable(0f)
    }

    val key7Alpha = remember {
        Animatable(0f)
    }

    val code1Alpha = remember {
        Animatable(0f)
    }

    val code2Alpha = remember {
        Animatable(0f)
    }

    val koreography = rememberKoreography {
        parallelMoves {
            move(
                animatable = squareScale,
                targetValue = 0.9f,
                animationSpec = infiniteRepeatable(
                    animation = tween(700, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = shadowScale,
                targetValue = 1.1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(700, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = laptopTranslationY,
                targetValue = 20.dp.value,
                animationSpec = infiniteRepeatable(
                    animation = tween(700, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            sequentialMoves {
                move(animatable = key7Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key7Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key7Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key7Alpha, targetValue = 0f, animationSpec = tween(150))
                move(
                    animatable = codePanelScale,
                    targetValue = 1f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )
                )
                move(animatable = key1Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key1Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key2Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key2Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key1Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key1Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key1Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key1Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key4Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key4Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key3Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key3Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key1Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key1Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = code2Alpha, targetValue = 1f, animationSpec = tween(250))
                move(animatable = key5Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key5Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key7Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key7Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key5Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key5Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = key6Alpha, targetValue = 1f, animationSpec = tween(150))
                move(animatable = key6Alpha, targetValue = 0f, animationSpec = tween(150))
                move(animatable = code1Alpha, targetValue = 1f, animationSpec = tween(250))
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color(0xFF311094))
            .clickable {
                koreography.danceForever(coroutineScope)
            }
    ) {
        Image(modifier = Modifier.graphicsLayer {
            scaleY = squareScale.value
            scaleX = squareScale.value
            transformOrigin = TransformOrigin(0.125f, 0.65f)
        }, painter = painterResource(id = R.drawable.laptop_square_1), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            scaleY = squareScale.value
            scaleX = squareScale.value
            transformOrigin = TransformOrigin(0.25f, 0.5f)
        }, painter = painterResource(id = R.drawable.laptop_square_2), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            scaleY = squareScale.value
            scaleX = squareScale.value
            transformOrigin = TransformOrigin(0.5f, 0.6f)
        }, painter = painterResource(id = R.drawable.laptop_square_3), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            scaleY = squareScale.value
            scaleX = squareScale.value
            transformOrigin = TransformOrigin(0.75f, 0.65f)

        }, painter = painterResource(id = R.drawable.laptop_square_4), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            scaleY = squareScale.value
            scaleX = squareScale.value
            transformOrigin = TransformOrigin(0.8f, 0.7f)

        }, painter = painterResource(id = R.drawable.laptop_square_5), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            scaleY = shadowScale.value
            scaleX = shadowScale.value
            transformOrigin = TransformOrigin(0.5f, 0.6f)
        }, painter = painterResource(id = R.drawable.laptop_shadow), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
        }, painter = painterResource(id = R.drawable.laptop), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            scaleX = codePanelScale.value
            scaleY = codePanelScale.value
            transformOrigin = TransformOrigin(0.6f, 0.4f)
        }, painter = painterResource(id = R.drawable.laptop_code_panel), contentDescription = null)
        Image(
            modifier = Modifier.graphicsLayer {
                scaleY = shadowScale.value
                scaleX = shadowScale.value
                transformOrigin = TransformOrigin(0.25f, 0.5f)
            },
            painter = painterResource(id = R.drawable.laptop_coffee_mug_shadow),
            contentDescription = null
        )
        Image(
            modifier = Modifier.graphicsLayer {
                translationY = laptopTranslationY.value
            },
            painter = painterResource(id = R.drawable.laptop_coffee_mug),
            contentDescription = null
        )
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            alpha = key1Alpha.value
        }, painter = painterResource(id = R.drawable.laptop_key_1), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            alpha = key2Alpha.value
        }, painter = painterResource(id = R.drawable.laptop_key_2), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            alpha = key3Alpha.value
        }, painter = painterResource(id = R.drawable.laptop_key_3), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            alpha = key4Alpha.value
        }, painter = painterResource(id = R.drawable.laptop_key_4), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            alpha = key5Alpha.value
        }, painter = painterResource(id = R.drawable.laptop_key_5), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            alpha = key6Alpha.value
        }, painter = painterResource(id = R.drawable.laptop_key_6), contentDescription = null)
        Image(modifier = Modifier.graphicsLayer {
            translationY = laptopTranslationY.value
            alpha = key7Alpha.value
        }, painter = painterResource(id = R.drawable.laptop_key_7), contentDescription = null)
        Image(
            modifier = Modifier.graphicsLayer {
                translationY = laptopTranslationY.value
                alpha = code1Alpha.value
            },
            painter = painterResource(id = R.drawable.laptop_code_panel_1),
            contentDescription = null
        )
        Image(
            modifier = Modifier.graphicsLayer {
                alpha = code2Alpha.value
                translationY = laptopTranslationY.value
            },
            painter = painterResource(id = R.drawable.laptop_code_panel_2),
            contentDescription = null
        )
    }
}
