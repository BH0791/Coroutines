package fr.hamtec.lesFlows

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

suspend fun appelMethFlow(): Unit {
    val combinedFlow = sampleFlow1().zip(sampleFlow2()) { first, second -> "($first, $second)" }
    sampleFlow1().collect { println("$it") }
    val combined2Flow = sampleFlow1().combine(sampleFlow2()) { first, second ->
        "($first, $second)"
    }
    val combined3Flow = sampleFlow1().flatMapConcat { value1 ->
        sampleFlow2().map { value2 ->
            "($value1, $value2)"
        }
    }

    println("La méthode zip()")
    combinedFlow.collect { println("$it") }
    println("La méthode combine()")
    combined2Flow.collect { println("$it") }
    println("La méthode flatMapConcat() ")
    combined3Flow.collect { println("$it") }


        val combined4Flow = sampleFlow1().flatMapMerge { value1 ->
            sampleFlow2().map { value2 ->
                "($value1, $value2)"
            }
        }
        println("La méthode merge()")
        combined4Flow.collect { println("$it") }
    }



suspend fun testFlow(): Unit {
    val tmps = measureTimeMillis {
        appelMethFlow()
    }
    println(
            """
 ╔════════════════════════════════╗
  coroutineTimeInMills = $tmps ms 
╚════════════════════════════════╝"""
    )
}