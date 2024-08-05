## Constructeur launch
```	
fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext, 
    start: CoroutineStart = CoroutineStart.DEFAULT, 
    block: suspend CoroutineScope.() -> Unit
): Job
```
Lance une nouvelle coroutine sans bloquer le thread courant et renvoie une référence à la coroutine sous la forme d'un job. La coroutine est annulée lorsque le travail résultant est annulé.
