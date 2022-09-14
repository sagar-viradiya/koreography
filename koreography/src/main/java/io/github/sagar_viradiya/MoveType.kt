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