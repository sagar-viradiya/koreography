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
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.spring

sealed interface Move<T, V : AnimationVector> {
    class SingleAnimatableMove<T, V : AnimationVector>(
        val animatable: Animatable<T, V>,
        val targetValue: T,
        val initialVelocity: T = animatable.velocity,
        val animationSpec: AnimationSpec<T> = spring(),
        internal val initialValue: T = animatable.value
    ) : Move<T, V>

    class SequentialMoves<T, V : AnimationVector> : Move<T, V> {
        private val _moves = mutableListOf<Move<T, V>>()
        internal val moves: List<Move<T, V>>
            get() = _moves.toList()

        fun move(
            animatable: Animatable<T, V>,
            targetValue: T,
            initialVelocity: T = animatable.velocity,
            animationSpec: AnimationSpec<T> = spring()
        ) {
            _moves += SingleAnimatableMove(animatable, targetValue, initialVelocity, animationSpec)
        }

        fun parallelMoves(parallelMoves: ParallelMoves<T, V>.() -> Unit) {
            _moves += ParallelMoves<T, V>().apply(parallelMoves)
        }

        fun sequentialMoves(sequentialMoves: SequentialMoves<T, V>.() -> Unit) {
            _moves += this.apply(sequentialMoves)
        }
    }

    class ParallelMoves<T, V : AnimationVector> : Move<T, V> {
        private val _moves = mutableListOf<Move<T, V>>()
        internal val moves: List<Move<T, V>>
            get() = _moves.toList()

        fun move(
            animatable: Animatable<T, V>,
            targetValue: T,
            initialVelocity: T = animatable.velocity,
            animationSpec: AnimationSpec<T> = spring(),
        ) {
            _moves += SingleAnimatableMove(animatable, targetValue, initialVelocity, animationSpec)
        }

        fun sequentialMoves(sequentialMoves: SequentialMoves<T, V>.() -> Unit) {
            _moves += SequentialMoves<T, V>().apply(sequentialMoves)
        }

        fun parallelMoves(parallelMoves: ParallelMoves<T, V>.() -> Unit) {
            _moves += this.apply(parallelMoves)
        }
    }
}