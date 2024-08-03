package fr.hamtec.constructeurRunBlocking

import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun firstSimpleDemo() {
    val nameThread = Thread.currentThread().name
    println("Exemple semble assez peut pertinent, ce n'est que pour mieux préparer la suite.\n")
    println("Before coroutine ${nameThread}")
    runBlocking {
        println("In coroutine ${nameThread} before sleep 1000 ms")
        Thread.sleep(1000)
        println("In coroutine ${nameThread} after sleep 1000 ms")
    }
    println("After coroutine ${nameThread}")
}
fun testCoroutine(): Unit {
    val testTime = measureTimeMillis {
        firstSimpleDemo()
    }
    println(
            """
    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++            
    Simple runBlocking = $testTime ms
    les coroutines runBlocking ne peuvent pas être suspendues ou annulées
    comme le peuvent les coroutines coroutineScope et que runBlocking est 
    notre seule option lorsque nous devons lancer des coroutines en dehors 
    de la portée d’une coroutine existante.
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}