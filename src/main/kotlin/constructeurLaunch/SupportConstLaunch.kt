package fr.hamtec.constructeurLaunch

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun firstLaunch(): Unit {
    println("Before coroutine")
    GlobalScope.launch{
        println("Before sleep")
        Thread.sleep(1000)
        println("After sleep")
    }
    println("After coroutine")
}
fun testFirstLaunch(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        firstLaunch()
    }
    println("""
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    Lancer une coroutine est une opération qui n'est pas instantanée. Le lancement de 
    la coroutine s'exécute en parrallèle des instructions qui se trouvent sous cell-ci.
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++""")
}
fun secondLaunch(): Unit {
    println("Before coroutine")
    GlobalScope.launch{
        println("Before sleep")
        Thread.sleep(1000)
        println("After sleep")
    }
    println("After coroutine")
    Thread.sleep(1000)
}
fun testSecondLaunch(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        secondLaunch()
    }
    println("""
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineTimeInMills = $coroutineScopeTimeInMills ms
    Il y a du mieux puisque l'on a ajouter un delay(500). Pour voir la totalité de la
    il faut mettre delay(1000)
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++""")
}