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

fun sixteenthCorDansCor(): Unit {
    runBlocking {

        val parentJob = GlobalScope.launch {
            println("Parent coroutine")
            launch() {
                println("First child coroutine thread: ${Thread.currentThread().name}")
                delay(1000)
                println("End of First child coroutine")
            }
            launch(Job() + Dispatchers.IO) {
                println("Second child coroutine thread: ${Thread.currentThread().name}")
                //delay(500)
                println("End of Second child coroutine")
            }
            println("End of parent coroutine")
        }

        parentJob.cancel()
    }
    Thread.sleep(1500)
    println("Fin program")
}

fun testSixteenthCorDansCor(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        sixteenthCorDansCor()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}

fun seventhCorDansCor(): Unit {
    runBlocking {

        val parentJob = GlobalScope.launch {
            println("Parent coroutine")
            launch() {
                println("First child coroutine thread: ${Thread.currentThread().name}")
                delay(1000)
                println("End of First child coroutine")
            }
            launch(SupervisorJob() + Dispatchers.IO + CoroutineExceptionHandler { CoroutineContext, throwable ->
                println("Exception: ${throwable.message}")
            }) {
                println("Second child coroutine thread: ${Thread.currentThread().name}")
                50 / 0
                println("End of Second child coroutine")
            }
            println("End of parent coroutine")
        }

        parentJob.join()
    }
    println("Fin program")
}

fun testSeventhCorDansCor(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        seventhCorDansCor()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}

fun eighthCorDansCor(): Unit {
    runBlocking {

        val parentJob = GlobalScope.launch {
            //println("Parent coroutine")
            val childJobFirst = async(start = CoroutineStart.LAZY) {
                println("First child coroutine thread: ${Thread.currentThread().name}")
                delay(1500)
                println("End of First child coroutine")
            }
            val childrJobSecond = async(start = CoroutineStart.LAZY) {
                println("Second child coroutine thread: ${Thread.currentThread().name}")
                delay(1000)
                println("End of Second child coroutine")
            }
            childJobFirst.start()
            childJobFirst.await()
            childrJobSecond.start()
            childrJobSecond.await()
        }

        parentJob.join()
    }
    println("Fin program")
}

fun testEighthCorDansCor(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        eighthCorDansCor()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}