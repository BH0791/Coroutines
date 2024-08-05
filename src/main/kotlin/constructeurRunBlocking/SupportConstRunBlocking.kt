package fr.hamtec.constructeurRunBlocking

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

val context = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
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
fun demoWithCoroutineScope() = runBlocking {
    (1..10).forEach {
        launch(context) {
            coroutineScope {
                println("Start No.$it in coroutineScope on ${Thread.currentThread().name}")
                delay(500)
                println("End No.$it in coroutineScope on ${Thread.currentThread().name}")
            }
        }
    }
}
fun testDemoWithCoroutineScope(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        demoWithCoroutineScope()
    }
    println("""
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineScopeTimeInMills = $coroutineScopeTimeInMills
    Ci-dessus, nous commençons par runBlocking pour créer un pont à partir de notre 
    code de blocage. À partir de l’intérieur du bloc runBlocking, nous utilisons 
    launch pour distribuer les coroutines suspendables à n’importe quel thread inactif 
    dans le pool de threads du contexte. Enfin, nous utilisons coroutineScope pour 
    lancer une coroutine qui invoquera delay() avec 500 millisecondes. La méthode 
    delay() est une fonction de suspension qui crée un point de suspension pour la 
    coroutine lancée par coroutineScope.
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++""")
}
fun demoWithRunBlocking() = runBlocking {
    (1..10).forEach {
        launch(context) {
            runBlocking {
                println("Start No.$it in runBlocking on ${Thread.currentThread().name}")
                delay(500)
                println("End No.$it in runBlocking on ${Thread.currentThread().name}")
            }
        }
    }
}
fun testDemoWithRunBlocking(): Unit {
    val coroutineScopeTimeInMills = measureTimeMillis {
        demoWithRunBlocking()
    }
    println("""
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    coroutineScopeTimeInMills = $coroutineScopeTimeInMills
    Chaque fil a été complété sur le même fil qu’au départ, et notre temps d’exécution
    total a pris plus de 2500 ms. La coroutine lancée par runBlocking ignorait le 
    point de suspension créé par l’appel delay(). Les coroutines runBlocking ne sont 
    pas suspendables.
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++""")
}
