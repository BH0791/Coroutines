package fr.hamtec.connaitreEtatDuneCoroutine

import fr.hamtec.constructeurRunBlocking.demoWithRunBlocking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun fistrEtatCoroutine(): Unit {
    val job = GlobalScope.launch {
        println("Coroutine: Before delay")
        delay(100)
        println("Coroutine: After delay")
    }

    while(job.isActive == false){
        println("The coroutine is not active yet")
    }

    println("The coroutine is active: ${job.isActive}")

    while(job.isCompleted == false){
        println("The coroutine is not completed yet")
    }

    println("The coroutine is finisf ${job.isCompleted}")
}
fun testFistrEtatCoroutine(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        fistrEtatCoroutine()
    }
    println("""
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    La coroutine est immédiatement active environ=55ms, qu'il y a un petit délai avant
    l'éxecution de ses premières instruction, et qu'elle ne se termine pas immédiatement 
    après l'exécution de sa dernière instruction.
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++""")
}
fun attendreLaFin(): Unit {
    runBlocking {
        val job = GlobalScope.launch {
            delay(1000)
        }

        job.join()
    }
}
fun testAttendreLaFin(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        attendreLaFin()
    }
    println("""
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++""")
}