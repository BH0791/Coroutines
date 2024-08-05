package fr.hamtec.coroutineDansCoroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun firstCordansCor() = runBlocking {
    println("Parent coroutine")
    launch {
        println("First child coroutine")
        delay(1000)
        println("End of First child coroutine")
    }
    launch {
        println("Second child coroutine")
        delay(1000)
        println("End of Second child coroutine")
    }
    println("End of parent coroutine")
}

fun testFirstCordansCor(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        firstCordansCor()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}

fun secondCorDansCor(): Unit {
    runBlocking {
        val job = GlobalScope.launch {
            println("Parent coroutine")
            launch {
                println("First child coroutine")
                delay(1000)
                println("End of First child coroutine")
            }
            launch {
                println("Second child coroutine")
                delay(1000)
                println("End of Second child coroutine")
            }
            println("End of parent coroutine")
        }

        job.cancel()

    }
    println("End of program")
}

fun testSecondCorDansCor(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        secondCorDansCor()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}

fun thirdCordansCors() = runBlocking {
    println("Parent coroutine")
    launch {
        println("First child coroutine")
        delay(1000)
        println("End of First child coroutine")
    }
    launch {
        println("Second child coroutine")
        delay(500)
        try {
            50 / 0
        } catch(ex: Exception) {
            println("$ex")
        }
        println("End of Second child coroutine")
    }
    println("End of parent coroutine")
}

fun testThirdCordansCors(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        thirdCordansCors()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}

fun fourthCorDansCor() = runBlocking {
    println("Parent coroutine")
    launch {
        println("First child coroutine")
        delay(1000)
        println("End of First child coroutine")
    }
    launch {
        println("Second child coroutine")
        delay(500)
        val result = runCatching {
            50 / 0
        }
        println("End of second child coroutine with success ? ${result.isSuccess}")
        println("End of Second child coroutine")
    }
    println("End of parent coroutine")
}

fun testFourthCorDansCor(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        fourthCorDansCor()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}

fun fifthCorDansCor(): Unit {
    runBlocking {

        val parentJob = GlobalScope.launch(CoroutineExceptionHandler { _, throwable ->
            println("An error occurred while executing the coroutine : ${throwable.message}")
        }) {
            println("Parent coroutine")
            launch {
                println("First child coroutine")
                delay(1000)
                println("End of First child coroutine")
            }
            launch {
                println("Second child coroutine")
                delay(500)
                50 / 0
                println("End of Second child coroutine")
            }
            println("End of parent coroutine")
        }
        parentJob.join()
    }
    println("End of program")
}

fun testFifthCorDansCor(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        fifthCorDansCor()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}