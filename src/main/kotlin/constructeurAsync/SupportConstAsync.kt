package fr.hamtec.constructeurAsync

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun firstAsync(): Unit {
    runBlocking {
        val deferred = GlobalScope.async {
            println("Before sleep")
            delay(1000)
            println("After sleep")
            val result = 12
            result
        }

        val result = deferred.await()

        println("Result = ${result}")
    }
}

fun testFirstAsync(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        firstAsync()
    }
    println(
            """
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineScopeTimeInMills = $coroutineScopeTimeInMills
    Ce constructeur présent plusieurs caractéristiques. Tout d'abord, il est possible 
    de renvoyer un résultat. Il est également possible d'attendre la fin de l'exécution
    des instructions contenus dans la coroutine.
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"""
    )
}