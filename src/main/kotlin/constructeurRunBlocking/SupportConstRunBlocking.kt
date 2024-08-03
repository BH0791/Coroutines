package fr.hamtec.constructeurRunBlocking

import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun firstSimpleDemo() {
    println("Exemple semble assez peut pertinent, ce n'est que pour mieux préparer la suite.\n")
    println("Before coroutine")
    runBlocking {
        println("In coroutine before sleep")
        Thread.sleep(1000)
        println("In coroutine after sleep")
    }
    println("After coroutine")
}

fun testCoroutine(arg: Any): Unit {
    val testTime = measureTimeMillis {
        arg
    }
    println("\nSimple runBlocking = $testTime ms")
}