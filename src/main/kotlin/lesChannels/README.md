## Les channels
```
inline suspend fun <E> ReceiveChannel<E>.consumeEach(action: (E) -> Unit)
interface Channel<E> : SendChannel<E> , ReceiveChannel<E> 

fun <E> Channel(
    capacity: Int = RENDEZVOUS, 
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND, 
    onUndeliveredElement: (E) -> Unit? = null
): Channel<E>

```

