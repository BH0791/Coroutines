## Constructeur runBlocking
```
expect fun <T> runBlocking(
    context: CoroutineContext = EmptyCoroutineContext, 
    block: suspend CoroutineScope.() -> T
): T
```
Les coroutines runBlocking ne peuvent pas être suspendues ou annulées comme le peuvent les coroutines coroutineScope et que runBlocking est notre seule option lorsque nous devons lancer des coroutines en dehors de la portée d’une coroutine existante.

Exécute une nouvelle coroutine et bloque le thread courant jusqu'à son achèvement. Elle est conçue pour faire le lien entre le code bloquant normal et les bibliothèques écrites dans un style suspensif, à utiliser dans les fonctions principales et dans les tests. L'appel à runBlocking à partir d'une fonction de suspension est redondant.