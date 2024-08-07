```
val workerPool: ExecutorService = ThreadPoolExecutor(
  corePoolSize,
  maximumPoolSize,
  keepAliveTime,
  TimeUnit.SECONDS,
  workQueue,
  threadFactory,
  handler
)
```
pool de thread fixe
```
val workerPool: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) 
```