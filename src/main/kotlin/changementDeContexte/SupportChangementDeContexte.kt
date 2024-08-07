package fr.hamtec.changementDeContexte

import fr.hamtec.coroutineDansCoroutine.firstCordansCor
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun firstChgContexte(): Unit {
    runBlocking {
        val parentJob = GlobalScope.launch {
            //println("Parent coroutine")
            val childJobFirst = launch() {
                println("First child coroutine thread: ${Thread.currentThread().name}")
                delay(1000)
                println("End of First child coroutine")
            }
            val childrJobSecond = launch() {
                println("Second child coroutine thread: ${Thread.currentThread().name}")
                withContext(Dispatchers.IO + Job()){
                    delay(1000)
                    println("End of Second child coroutine")
                }

            }

            delay(200)

            childJobFirst.cancel()
            childrJobSecond.cancel()
        }

        parentJob.join()
    }
    println("Fin program")
}
fun testFirstChgContexte(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        firstChgContexte()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}