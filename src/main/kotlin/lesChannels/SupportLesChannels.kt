package fr.hamtec.lesChannels

import kotlinx.coroutines.channels.Channel
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

fun channelRendezVous(): Unit {
    runBlocking {
        //++ val channel = Channel<Int>() pour le même résultat
        val channel = Channel<Int>(Channel.RENDEZVOUS)

        launch {
            for(i in 1..3) {
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

fun channelLinkedListChannel(): Unit {
    runBlocking {
        val channel = Channel<Int>(Channel.UNLIMITED)

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

fun channelConflated(): Unit {
    runBlocking {
        val channel = Channel<Int>(Channel.CONFLATED)

        launch {
            for(i in 1..6) {
                println("Sending the value $i ▲↑▲")
                channel.send(i)
                delay(550)
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

fun channelDoubleReception(): Unit {
    runBlocking {
        val channel = Channel<Int>(Channel.UNLIMITED)
        //-- Envoie
        launch {
            for(i in 1..5) {
                println("Sending the value $i ▲↑▲")
                channel.send(i)
            }
            channel.close()
        }
        //-- Reception-1
        launch {
            channel.consumeEach {
                println("#1 Received the value $it ▼↓▼")
                delay(100)
            }
        }
        //-- Reception-2
        launch {
            channel.consumeEach {
                println("#2 Received the value $it ▼↓▼")
                delay(100)
            }
        }
    }
    println("○ End program ○")
}

fun testChannel(): Unit {
    val tmps = measureTimeMillis {
        channelDoubleReception()
    }
    println(
            """
 ╔════════════════════════════════╗
  coroutineTimeInMills = $tmps ms 
╚════════════════════════════════╝"""
    )
}