package fr.hamtec.lesChannels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun firstChannel(): Unit {
    runBlocking {
        val channel = Channel<Int>(3)

        launch {
            for(i in 1..3) {
                println("Sending the value $i ▲↑▲")
                channel.send(i)
                delay(1000)
            }
        }
        //--
        launch {
            repeat(3) {
                val dataReceived = channel.receive()
                println("Received the value $dataReceived ▼↓▼")
            }

        }
    }
    println("End program")
}

fun secondChannel(): Unit {
    runBlocking {
        val channel = Channel<Int>(3)

        launch {
            for(i in 1..10) {
                channel.send(i)
                println("Sending the value $i ▲↑▲")
            }
        }
        //--
        launch {
            repeat(5) {
                val dataReceived = channel.receive()
                delay(1000)
                println("Received the value $dataReceived ▼↓▼")
            }

        }
    }
    println("End program")
}

fun thirdChannel(): Unit {
    runBlocking {
        val channel = Channel<Int>(3)

        launch {
            for(i in 1..5) {
                channel.send(i)
                println("Sending the value $i ▲↑▲")
            }
            channel.close()
        }
        //--
        launch {
            channel.consumeEach {
                delay(1000)
                println("Received the value $it ▼↓▼")
            }
        }
    }
    println("○ End program ○")
}

fun testChannel(): Unit {
    val tmps = measureTimeMillis {
        thirdChannel()
    }
    println(
            """
 ╔════════════════════════════════╗
  coroutineTimeInMills = $tmps ms 
╚════════════════════════════════╝"""
    )
}