package fr.hamtec.lesChannels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun firstChannel(): Unit {
    runBlocking {
        val channel = Channel<Int>(3)

        launch {
            for(i in 1..10){
                println("Sending the value $i ▲↑▲")
                channel.send(i)
                delay(1000)
            }
        }
        //--
        launch {
            repeat(3){
                val dataReceived = channel.receive()
                println("Received the value $dataReceived ▼↓▼")
            }

        }
    }
    println("End program")
}