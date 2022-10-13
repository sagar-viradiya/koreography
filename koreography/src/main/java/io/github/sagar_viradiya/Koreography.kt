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

package io.github.sagar_viradiya

import androidx.compose.animation.core.animate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class Koreography {

    internal val moves: Move.SequentialMoves = Move.SequentialMoves()

    private var job: Job? = null

    fun dance(scope: CoroutineScope) {
        // In case we want to dance from start then cancel current dance
        job?.cancel()
        job = scope.launch {
            startDance(moves, this)
        }
    }

    internal suspend fun startDance(move: Move, scope: CoroutineScope) {
        when (move) {
            is Move.SingleMove -> with(move) {
                animate(
                    initialValue,
                    targetValue,
                    initialVelocity,
                    animationSpec,
                    block
                )
            }

            is Move.SequentialMoves -> {
                move.moves.forEach { sequentialMoves ->
                    coroutineScope {
                        startDance(sequentialMoves, this)
                    }
                }
            }

            is Move.ParallelMoves -> {
                move.moves.forEach { parallelMoves ->
                    scope.launch {
                        startDance(parallelMoves, this)
                    }
                }
            }
        }
    }
}

fun koreography(koreographyMoves: Move.SequentialMoves.() -> Unit): Koreography {
    return Koreography().apply {
        moves.koreographyMoves()
    }
}

@Composable
fun rememberKoreography(koreographyMoves: Move.SequentialMoves.() -> Unit): Koreography {
    return remember {
        koreography(koreographyMoves)
    }
}

@Composable
fun <T> LaunchKoreography(state: T, koreographyMoves: Move.SequentialMoves.() -> Unit) {
    rememberKoreography(koreographyMoves = koreographyMoves).run {
        LaunchedEffect(state) {
            startDance(moves, this)
        }
    }
}
