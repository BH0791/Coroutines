## Les coroutines
Aide-mémoire

>Une coroutine est un modèle de conception de simultanéité que vous pouvez utiliser sur Android pour simplifier le code qui s'exécute de manière asynchrone. Les coroutines ont été ajoutées à Kotlin dans la version 1.3 et sont basées sur des concepts établis dans d'autres langages.<br>
>Les coroutines sont des sous-routines ou des programmes qui permettent le multitâche coopératif. Par conséquent, les coroutines peuvent être suspendues ou reprises, ou elles peuvent céder le passage à une autre coroutine. En Kotlin, le mot-clé suspend avant la fonction signifie qu’elle suspend l’appelant et ne peut être appelée qu’au sein d’une coroutine.
### runBlocking
```
expect fun <T> runBlocking(
    context: CoroutineContext = EmptyCoroutineContext, 
    block: suspend CoroutineScope.() -> T
): T
```
### launch
```	
fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext, 
    start: CoroutineStart = CoroutineStart.DEFAULT, 
    block: suspend CoroutineScope.() -> Unit
): Job
```
### async
```
fun <T> CoroutineScope.async(
    context: CoroutineContext = EmptyCoroutineContext, 
    start: CoroutineStart = CoroutineStart.DEFAULT, 
    block: suspend CoroutineScope.() -> T
): Deferred<T>
```
*<br>
Remerciements<br>Ludovic ROLAND<br>Jim Steinberger<br>Martin Devillers ..*