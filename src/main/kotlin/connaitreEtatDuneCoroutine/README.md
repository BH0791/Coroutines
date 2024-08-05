## Connaître l'état d'une coroutine

interface Element : CoroutineContext
- abstract val key: Key<*>
- open fun <R> fold(
  initial: R,
  operation: (R, Element) -> R
  ): R
- open operator fun <E : Element> get(key: Key<E>): E?
- open fun minusKey(key: Key<*>): CoroutineContext
 
interface Job : CoroutineContext.Element
- object Key : CoroutineContext.Key<Job>
- abstract val children: Sequence<Job>
- abstract val isActive: Boolean
- abstract val isCancelled: Boolean
- abstract val isCompleted: Boolean
- abstract val onJoin: SelectClause0
- @ExperimentalCoroutinesApi abstract val parent: Job?
- abstract fun cancel(cause: CancellationException? = null)
- abstract fun invokeOnCompletion(handler: CompletionHandler): DisposableHandle
- abstract suspend fun join()
- abstract fun start(): Boolean

interface Deferred<out T> : Job
- abstract val onAwait: SelectClause1<T>
- abstract suspend fun await(): T
- @ExperimentalCoroutinesApi abstract fun getCompleted(): T
- @ExperimentalCoroutinesApi abstract fun getCompletionExceptionOrNull(): Throwable?