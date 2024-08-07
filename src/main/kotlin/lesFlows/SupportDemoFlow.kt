package fr.hamtec.lesFlows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun sampleFlow1(): Flow<Int> = flow {
    repeat(3) {
        delay(500)
        emit(it)
    }
}
suspend fun sampleFlow2(): Flow<Int> = flow {
    repeat(3) {
        delay(500)
        emit(it * it)
    }
}