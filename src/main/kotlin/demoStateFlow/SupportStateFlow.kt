package fr.hamtec.demoStateFlow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun firstStateFlow(): Unit {
runBlocking {

    val sharedFlow = MutableSharedFlow<Int>(
            replay = 2,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val jojo = launch {
        sharedFlow.emit(0) // initial value
        //val stateFlow =
        sharedFlow.distinctUntilChanged()

        sharedFlow.emit(1)
        sharedFlow.emit(2)
        sharedFlow.emit(2)
        sharedFlow.emit(1)
        sharedFlow.emit(3)
    }


    val job1 = GlobalScope.launch {
        sharedFlow.collect {
            println(it)
        }
    }
//    val job2 = GlobalScope.launch {
//        sharedFlow.collect {
//            println("$it")
//        }
//    }
    jojo.join()
    job1.join()
//    job2.join()
}


}