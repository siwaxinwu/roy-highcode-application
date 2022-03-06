# roy-designmode 用于演练单机版高并发
|场景|命令|
|---|---|
|futuretask多个线程之间演练通信|futuretest.MakeTeaExample,futuretest.MakeTeaExample1|
|手写一个线程池|threadpool.MyThreadPool|
|读写锁的演示demo|callback.ThreadTest|
|必死锁的demo演示|deadLockDemo.MustDeadLockDemo|
|写一段代码实现自旋锁|deadLockDemo.SpinLock|
|atomicreference的使用|atomicreference.BankCardARTest2|
|用于两个线程之间互相交换数据|juc.UseExchange|
|利用Semaphare控制流量|juc.UseSemaphore|
|semaphere常见用法|juc.SemaphereExample|
|获取一个线程执行结果有哪些方式|threadpool.GetThreadResult|
|学习callable接口的写法，泛型的使用|callback.TestCallable|
|CycliBarrier的demo演示|TestCycliBarrier|
|使用cycclibarrier来综合N个线程的结果，继续执行业务|cyclibarrier.TestCycliBarrier|
|利用atomicInteger来实现自增|atomicreference.AtomicIntegerTest|
|利用ForkJoinPool实现Fibomacci数列|juc.fibonacci.FiboNacciTest1|
|测试生产消费模型|juc.blockqueue.BlockQueueTest1|
|测试|CompletableFuture|
|测试supplyAsync最直接的用法|completabletest.test.TestCompletableFuture|
|测试thenCompose的用法|completabletest.test.TestCompletableFuture1|
|测试thenCombine用来合并两个任务|completabletest.test.TestCompletableFuture2，completabletest.test.TestCompletableFuture2_1|
|测试applyToEither的用法|completabletest.test.TestCompletableFuture4|
|测试exceptionally的使用|completabletest.test.TestCompletableFuture5|
|测试定时任务线程池|threadpool.TestSchedulePool|







