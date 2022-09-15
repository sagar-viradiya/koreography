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

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring

sealed interface MoveType {
    class Move(
        val initialValue: Float,
        val targetValue: Float,
        val initialVelocity: Float = 0f,
        val animationSpec: AnimationSpec<Float> = spring(),
        val block: (value: Float, velocity: Float) -> Unit
    ) : MoveType

    class SequentialMoves : MoveType {
        private val _moves = mutableListOf<MoveType>()
        internal val moves: List<MoveType>
            get() = _moves.toList()

        fun move(
            initialValue: Float,
            targetValue: Float,
            initialVelocity: Float = 0f,
            animationSpec: AnimationSpec<Float> = spring(),
            block: (value: Float, velocity: Float) -> Unit
        ) {
            _moves += Move(initialValue, targetValue, initialVelocity, animationSpec, block)
        }

        fun parallelMoves(parallelMoves: ParallelMoves.() -> Unit) {
            _moves += ParallelMoves().apply(parallelMoves)
        }

        fun sequentialMoves(sequentialMoves: SequentialMoves.() -> Unit) {
            _moves += this.apply(sequentialMoves)
        }
    }

    class ParallelMoves : MoveType {
        private val _moves = mutableListOf<MoveType>()
        internal val moves: List<MoveType>
            get() = _moves.toList()

        fun move(
            initialValue: Float,
            targetValue: Float,
            initialVelocity: Float = 0f,
            animationSpec: AnimationSpec<Float> = spring(),
            block: (value: Float, velocity: Float) -> Unit
        ) {
            _moves += Move(initialValue, targetValue, initialVelocity, animationSpec, block)
        }

        fun sequentialMoves(sequentialMoves: SequentialMoves.() -> Unit) {
            _moves += SequentialMoves().apply(sequentialMoves)
        }

        fun parallelMoves(parallelMoves: ParallelMoves.() -> Unit) {
            _moves += this.apply(parallelMoves)
        }
    }
}