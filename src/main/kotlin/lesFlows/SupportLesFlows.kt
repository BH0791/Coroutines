package fr.hamtec.lesFlows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun firstFlow(): Unit {
    runBlocking {
        //-- Envoie
        val flow: Flow<Int> = flow {
            for(i in 1..3) {
                println("Sending the value $i ▲↑▲")
                emit(i)
                delay(1000)
            }
        }
        //-- Reception
        launch {
            flow.collect {
                println("#1 Received the value $it ▼↓▼")
            }
        }
    }
    println("○ End program ○")
}

suspend fun MethFlowCombine() {
    println("La méthode combine()")
    val combined2Flow = sampleFlow1().combine(sampleFlow2()) { first, second ->
        "($first, $second)"
    }
    combined2Flow.collect { println("$it") }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun MethFlowFlatMapConcat() {
    println("La méthode flatMapConcat() ")
    val combined3Flow = sampleFlow1().flatMapConcat { value1 ->
        sampleFlow2().map { value2 ->
            "($value1, $value2)"
        }
    }
    combined3Flow.collect { println("$it") }
}

suspend fun MethFlowFlapMapMerge() {
    println("La méthode merge()")
    val combined4Flow = sampleFlow1().flatMapMerge { value1 ->
        sampleFlow2().map { value2 ->
            "($value1, $value2)"
        }
    }
    combined4Flow.collect { println("$it") }
}

suspend fun MethFlowZip(): Unit {
    println("La méthode zip()")
    val combinedFlow = sampleFlow1().zip(sampleFlow2()) { first, second -> "($first, $second)" }
    combinedFlow.collect { println("$it") }
}

suspend fun testFlow(): Unit {
    val tmps = measureTimeMillis {
        MethFlowZip()
    }
    println(
            """
 ╔════════════════════════════════╗
  coroutineTimeInMills = $tmps ms 
╚════════════════════════════════╝"""
    )
}

fun testEmitData(): Unit {
    runBlocking {
        println("Cor ==")
        val job = GlobalScope.launch() {
            emitData().collect { println("valeur : $it") }
        }
        job.join()
    }
}