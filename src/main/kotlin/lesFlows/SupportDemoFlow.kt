package fr.hamtec.lesFlows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

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
fun emitData() : Flow<Int> {
    return flow<Int> {
        repeat(5) {
            emit(java.util.Random().nextInt(100))
            delay(10)
        }
    }
}