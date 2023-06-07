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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Double.POSITIVE_INFINITY

class Koreography<T, V : AnimationVector> {

    internal val moves: Move.SequentialMoves<T, V> = Move.SequentialMoves()

    private var job: Job? = null

    fun dance(scope: CoroutineScope, onDanceFinished: () -> Unit = {}) {
        // In case we want to dance from start then cancel current dance
        _dance(count = 1, scope = scope, onDanceFinished)
    }

    fun repeatDance(count: Int, scope: CoroutineScope, onDanceFinished: () -> Unit = {}) {
        // In case we want to dance from start then cancel current dance
        _dance(count = count, scope = scope, onDanceFinished)
    }

    fun danceForever(scope: CoroutineScope) {
        _dance(scope = scope)
    }

    fun snapTo(scope: CoroutineScope, animatableTargetMap: Map<Animatable<T, V>, T>) {
        /* Cancel the on going animation and set the final state of all animations to value passed
           in [animatableTargetMap] */
        job?.cancel()
        scope.launch {
            snapTo(animatableTargetMap = animatableTargetMap)
        }
    }

    suspend fun snapTo(animatableTargetMap: Map<Animatable<T, V>, T>) {
        animatableTargetMap.forEach { (animatable, target) ->
            animatable.snapTo(target)
        }
    }

    internal suspend fun resetAndStartDance(scope: CoroutineScope) {
        snapAnimationToInitialValue()
        startDance(scope)
    }

    private fun _dance(
        count: Number = POSITIVE_INFINITY,
        scope: CoroutineScope,
        onDanceFinished: () -> Unit = {}
    ) {
        job?.cancel()
        job = scope.launch {
            if (count == POSITIVE_INFINITY) {
                while (true) {
                    startDance(this)
                }
            } else {
                repeat(count as Int) {
                    startDance(this)
                }
                onDanceFinished()
            }
        }
    }

    private suspend fun startDance(scope: CoroutineScope, move: Move<T, V> = moves) {
        when (move) {
            is Move.SequentialMoves -> {
                move.moves.forEach { sequentialMoves ->
                    coroutineScope {
                        startDance(this, sequentialMoves)
                    }
                }
            }

            is Move.ParallelMoves -> {
                move.moves.forEach { parallelMoves ->
                    scope.launch {
                        startDance(this, parallelMoves)
                    }
                }
            }

            is Move.SingleAnimatableMove -> with(move) {
                animatable.animateTo(
                    targetValue = targetValue,
                    initialVelocity = initialVelocity,
                    animationSpec = animationSpec
                )
            }
        }
    }

    private suspend fun snapAnimationToInitialValue(move: Move<T, V> = moves) {
        when (move) {
            is Move.ParallelMoves -> {
                move.moves.forEach {
                    snapAnimationToInitialValue(it)
                }
            }

            is Move.SequentialMoves -> {
                move.moves.forEach {
                    snapAnimationToInitialValue(it)
                }
            }

            is Move.SingleAnimatableMove -> {
                move.animatable.snapTo(move.initialValue)
            }
        }
    }
}

fun <T, V : AnimationVector> koreography(
    koreographyMoves: Move.SequentialMoves<T, V>.() -> Unit
): Koreography<T, V> {
    return Koreography<T, V>().apply {
        moves.koreographyMoves()
    }
}

@Composable
fun <T, V : AnimationVector> rememberKoreography(
    koreographyMoves: Move.SequentialMoves<T, V>.() -> Unit
): Koreography<T, V> {
    return remember {
        koreography(koreographyMoves)
    }
}

@Composable
fun <S, T, V : AnimationVector> LaunchKoreography(
    state: S,
    onDanceFinished: () -> Unit = {},
    koreographyMoves: Move.SequentialMoves<T, V>.() -> Unit
) {
    rememberKoreography(koreographyMoves = koreographyMoves).run {
        LaunchedEffect(state) {
            resetAndStartDance(this)
            onDanceFinished()
        }
    }
}
