package io.github.sagar_viradiya

import androidx.compose.animation.core.animate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class Koreography {

    internal val moves: MoveType.SequentialMoves = MoveType.SequentialMoves()

    private var job: Job? = null

    fun dance(scope: CoroutineScope) {
        // In case we want to dance from start then cancel current dance
        job?.cancel()
        job = scope.launch {
            startDance(moves, this)
        }
    }

    private suspend fun startDance(moves: MoveType, scope: CoroutineScope) {
        when (moves) {
            is MoveType.Move -> with(moves) {
                animate(
                    initialValue,
                    targetValue,
                    initialVelocity,
                    animationSpec,
                    block
                )
            }

            is MoveType.SequentialMoves -> {
                moves.moves.forEach { sequentialMoves ->
                    coroutineScope {
                        startDance(sequentialMoves, this)
                    }
                }
            }

            is MoveType.ParallelMoves -> {
                moves.moves.forEach { parallelMoves ->
                    scope.launch {
                        startDance(parallelMoves, this)
                    }
                }
            }
        }
    }
}

fun koreography(koreographyMoves: MoveType.SequentialMoves.() -> Unit): Koreography {
    return Koreography().apply {
        moves.koreographyMoves()
    }
}

@Composable
fun rememberKoreography(koreographyMoves: MoveType.SequentialMoves.() -> Unit): Koreography {
    return remember {
        koreography(koreographyMoves)
    }
}
