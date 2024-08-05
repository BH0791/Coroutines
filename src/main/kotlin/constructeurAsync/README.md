## Constructeur async
```
fun <T> CoroutineScope.async(
    context: CoroutineContext = EmptyCoroutineContext, 
    start: CoroutineStart = CoroutineStart.DEFAULT, 
    block: suspend CoroutineScope.() -> T
): Deferred<T>
```
Crée une coroutine et renvoie son résultat futur sous la forme d'une implémentation de Deferred. La coroutine en cours d'exécution est annulée lorsque le différé résultant est annulé.