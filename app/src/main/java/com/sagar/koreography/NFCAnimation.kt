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
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import io.github.sagar_viradiya.rememberKoreography

@Composable
fun NFCAnimation() {
    val scaleMobile = remember {
        Animatable(0f)
    }

    val scaleReferenceFrame = remember {
        Animatable(0f)
    }

    val scaleYEye = remember {
        Animatable(0f)
    }

    val scaleSmile = remember {
        Animatable(0f)
    }

    val scaleLine = remember {
        Animatable(0f)
    }

    val translateLine = remember {
        Animatable(0f)
    }

    val alphaReferenceFrame = remember {
        Animatable(1f)
    }

    val alphaCard = remember {
        Animatable(0f)
    }

    val translationXCard = remember {
        Animatable(0f)
    }

    val translationYCard = remember {
        Animatable(0f)
    }

    val rotationCard = remember {
        Animatable(0f)
    }

    val alphaNFC = remember {
        Animatable(0f)
    }

    val scaleNFC = remember {
        Animatable(0f)
    }

    val translationXNFCWave1 = remember {
        Animatable(0f)
    }

    val translationXNFCWave2 = remember {
        Animatable(0f)
    }

    val translationZCard = remember {
        Animatable(0f)
    }

    val scaleGreenCircle = remember {
        Animatable(0f)
    }

    val rotationGreenCircle = remember {
        Animatable(0f)
    }

    val koreography = rememberKoreography {
        parallelMoves {
            move(
                animatable = scaleMobile,
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            move(
                animatable = scaleReferenceFrame,
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
        parallelMoves {
            move(
                animatable = scaleYEye,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 200)
            )
            move(
                animatable = scaleSmile,
                targetValue = 1f,
                animationSpec = tween(durationMillis = 200)
            )
        }
        move(
            animatable = scaleYEye,
            targetValue = 0f,
            animationSpec = tween(durationMillis = 200)
        )
        move(
            animatable = scaleYEye,
            targetValue = 1f,
            animationSpec = tween(durationMillis = 200)
        )

        parallelMoves {
            move(animatable = scaleLine, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = translateLine,
                targetValue = -112.dp.value,
                animationSpec = tween(300)
            )
        }

        move(animatable = translateLine, targetValue = 112.dp.value, animationSpec = tween(300))
        move(animatable = translateLine, targetValue = 0.dp.value, animationSpec = tween(300))
        move(animatable = scaleLine, targetValue = 0f, animationSpec = tween(200))

        parallelMoves {
            move(animatable = scaleReferenceFrame, targetValue = 0f, animationSpec = tween(200))
            move(animatable = alphaReferenceFrame, targetValue = 0f, animationSpec = tween(200))
            move(
                animatable = scaleYEye,
                targetValue = 0f,
                animationSpec = tween(durationMillis = 200)
            )
            move(
                animatable = alphaCard, targetValue = 1f, animationSpec = tween(
                    400
                )
            )
            move(
                animatable = translationYCard,
                targetValue = 228.dp.value,
                animationSpec = tween(700)
            )
            move(
                animatable = translationXCard,
                targetValue = 32.dp.value,
                animationSpec = tween(700)
            )
            move(animatable = rotationCard, targetValue = 32f, animationSpec = tween(700))
            move(animatable = alphaNFC, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = scaleNFC,
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }

        parallelMoves {
            move(
                animatable = translationYCard,
                targetValue = 72.dp.value,
                animationSpec = tween(500)
            )
            move(
                animatable = translationXCard,
                targetValue = 88.dp.value,
                animationSpec = tween(500)
            )
            move(animatable = scaleNFC, targetValue = 0.8f, animationSpec = tween(500))
            sequentialMoves {
                parallelMoves {
                    move(
                        animatable = translationXNFCWave1,
                        targetValue = -32.dp.value,
                        animationSpec = tween(250)
                    )
                    move(
                        animatable = translationXNFCWave2,
                        targetValue = -64.dp.value,
                        animationSpec = tween(500)
                    )
                }
                parallelMoves {
                    move(
                        animatable = translationXNFCWave1,
                        targetValue = 0.dp.value,
                        animationSpec = tween(250)
                    )
                    move(
                        animatable = translationXNFCWave2,
                        targetValue = 0.dp.value,
                        animationSpec = tween(500)
                    )
                }
            }
        }

        parallelMoves {
            move(
                animatable = translationYCard,
                targetValue = 172.dp.value,
                animationSpec = tween(500)
            )
            move(
                animatable = translationXCard,
                targetValue = 112.dp.value,
                animationSpec = tween(500)
            )
            move(animatable = scaleNFC, targetValue = 1f, animationSpec = tween(500))
            sequentialMoves {
                parallelMoves {
                    move(
                        animatable = translationXNFCWave1,
                        targetValue = -32.dp.value,
                        animationSpec = tween(250)
                    )
                    move(
                        animatable = translationXNFCWave2,
                        targetValue = -64.dp.value,
                        animationSpec = tween(500)
                    )
                }
                parallelMoves {
                    move(
                        animatable = translationXNFCWave1,
                        targetValue = 0.dp.value,
                        animationSpec = tween(250)
                    )
                    move(
                        animatable = translationXNFCWave2,
                        targetValue = 0.dp.value,
                        animationSpec = tween(500)
                    )
                }
            }
        }

        parallelMoves {
            move(
                animatable = translationYCard,
                targetValue = 72.dp.value,
                animationSpec = tween(500)
            )
            move(
                animatable = translationXCard,
                targetValue = -8.dp.value,
                animationSpec = tween(500)
            )
            move(animatable = scaleNFC, targetValue = 0.8f, animationSpec = tween(500))
            sequentialMoves {
                parallelMoves {
                    move(
                        animatable = translationXNFCWave1,
                        targetValue = -32.dp.value,
                        animationSpec = tween(250)
                    )
                    move(
                        animatable = translationXNFCWave2,
                        targetValue = -64.dp.value,
                        animationSpec = tween(500)
                    )
                }
                parallelMoves {
                    move(
                        animatable = translationXNFCWave1,
                        targetValue = 0.dp.value,
                        animationSpec = tween(250)
                    )
                    move(
                        animatable = translationXNFCWave2,
                        targetValue = 0.dp.value,
                        animationSpec = tween(500)
                    )
                }
            }
        }

        parallelMoves {
            move(animatable = alphaNFC, targetValue = 0f, animationSpec = tween(50))
            move(
                animatable = rotationCard,
                targetValue = -70f,
                animationSpec = tween(400, easing = LinearOutSlowInEasing)
            )
        }

        parallelMoves {
            move(animatable = translationZCard, targetValue = 1f, animationSpec = tween(50))
            move(
                animatable = rotationCard,
                targetValue = 20f,
                animationSpec = tween(400)
            )
            move(
                animatable = scaleGreenCircle,
                targetValue = 1f,
                animationSpec = tween(500)
            )
            move(animatable = rotationGreenCircle, targetValue = 360f, animationSpec = tween(1000))
        }

    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { koreography.dance(coroutineScope) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.card),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer {
                        alpha = alphaCard.value
                        translationX = translationXCard.value
                        translationY = translationYCard.value
                        rotationZ = rotationCard.value
                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                    }
                    .zIndex(translationZCard.value)
            )
            Image(
                painter = painterResource(R.drawable.mobile),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        scaleX = scaleMobile.value
                        scaleY = scaleMobile.value
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.eye_1),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        scaleY = scaleYEye.value
                        alpha = alphaReferenceFrame.value
                        transformOrigin = TransformOrigin(0.2f, 0.2f)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.eye_2),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        scaleY = scaleYEye.value
                        alpha = alphaReferenceFrame.value
                        transformOrigin = TransformOrigin(0.8f, 0.2f)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.smile),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        scaleX = scaleSmile.value
                        scaleY = scaleSmile.value
                        alpha = alphaReferenceFrame.value
                        transformOrigin = TransformOrigin(0.5f, 0.8f)
                    }
            )
            Image(
                painter = painterResource(R.drawable.reference_frame),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        scaleX = scaleReferenceFrame.value
                        scaleY = scaleReferenceFrame.value
                        alpha = alphaReferenceFrame.value
                    }
            )
            Image(
                painter = painterResource(R.drawable.scan_line),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        scaleX = scaleLine.value
                        translationY = translateLine.value
                    }
            )
            Image(
                painter = painterResource(R.drawable.nfc),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        scaleX = scaleNFC.value
                        scaleY = scaleNFC.value
                        alpha = alphaNFC.value
                        transformOrigin = TransformOrigin(0.25f, 0.5f)
                    }
            )
            Image(
                painter = painterResource(R.drawable.nfc_wave_1),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        translationX = translationXNFCWave1.value
                        alpha = alphaNFC.value
                        scaleX = scaleNFC.value
                        scaleY = scaleNFC.value
                        transformOrigin = TransformOrigin(0.6f, 0.5f)
                    }
            )
            Image(
                painter = painterResource(R.drawable.nfc_wave_2),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .graphicsLayer {
                        translationX = translationXNFCWave2.value
                        alpha = alphaNFC.value
                        scaleX = scaleNFC.value
                        scaleY = scaleNFC.value
                        transformOrigin = TransformOrigin(0.8f, 0.5f)

                    }
            )
            Image(
                painter = painterResource(R.drawable.green_circle),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.BottomEnd
                    )
                    .graphicsLayer {
                        rotationZ = rotationGreenCircle.value
                        scaleX = scaleGreenCircle.value
                        scaleY = scaleGreenCircle.value
                    }
            )

        }
    }
}

@Preview
@Composable
fun PreviewNFCAnimation() {
    NFCAnimation()
}