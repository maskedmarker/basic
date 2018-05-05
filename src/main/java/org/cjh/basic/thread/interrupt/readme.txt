void java.lang.Thread.interrupt()

Interrupts this thread. 

If this thread is blocked in an invocation of the wait(), wait(long), or wait(long, int) methods of the Object class, or of the join(), join(long), join(long, int), sleep(long), or sleep(long, int), methods of this class, then its interrupt status will be cleared and it will receive an InterruptedException. 

If this thread is blocked in an I/O operation upon an interruptible channel then the channel will be closed, the thread's interrupt status will be set, and the thread will receive a java.nio.channels.ClosedByInterruptException. 

If this thread is blocked in a java.nio.channels.Selector then the thread's interrupt status will be set and it will return immediately from the selection operation, possibly with a non-zero value, just as if the selector's wakeup method were invoked. 

If none of the previous conditions hold then this thread's interrupt status will be set. 

Interrupting a thread that is not alive need not have any effect.


java.lang.InterruptedException


Thrown 
when a thread is waiting, sleeping, or otherwise occupied, 
and the thread is interrupted, either before or during the activity. 

Occasionally a method may wish to test whether the current thread has been interrupted, 
and if so, to immediately throw this exception. The following code can be used to achieve this effect: 
  if (Thread.interrupted())  // Clears interrupted status!
      throw new InterruptedException();



调用线程的interrupt() 方法不会中断一个正在运行的线程，这个机制只是设置了一个线程中断标志位，如果在程序中你不检测线程中断标志位，
那么即使设置了中断标志位为true，线程也一样照常运行。
但是在由正常状态转为阻塞/挂起状态时,当前线程会检查中断状态,若为true则抛异常.
当前线程处于阻塞/挂起状态时,中断状态被其他线程设置为true,则当前线程抛异常,同时处于激活正常态.

线程只有在状态切换时才会涉及自己的中断状态:
当前线程由正常态转为阻塞/挂起状态时,才会检查自己的中断状态,若为true,则当前线程清除中断状态(即设置为初始值false)并抛异常.
当前线程由阻塞/挂起状态时,被其他线程设置为中断,则当前线程清除中断状态(即设置为初始值false)并抛异常,恢复正常态.


对于wait()/join()/sleep(),线程被唤醒时,检查中断状态,若true则清除标识并抛异常.标识只能用一次.
对于IO阻塞(select也算是IO操作),抛异常时会保留中断标识.

中断非阻塞线程通常有两种方式：
(1)采用线程间共享变量
这种方式比较简单可行，需要注意的一点是共享变量必须设置为volatile，这样才能保证修改后其他线程立即可见。
(2) 采用中断机制 
即if (Thread.isInterrupted()),底层还是变更共享变量,只是通过Thread.interrupt()来完成的.
(三)：不可中断线程
有一种情况是线程不能被中断的，就是调用synchronized关键字和reentrantLock.lock()获取锁的过程。


首先，一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止。
所以，Thread.stop, Thread.suspend, Thread.resume 都已经被废弃了。
而 Thread.interrupt 的作用其实也不是中断线程，而是「通知线程应该中断了」，具体到底中断还是继续运行，应该由被通知的线程自己处理。

链接：https://www.zhihu.com/question/41048032/answer/89431513

作者：风格鱼
链接：https://www.zhihu.com/question/41048032/answer/89478427

其实这是一个如何停止线程的问题，要真正理解interrupt()方法，要先了解stop()方法。
在以前通过thread.stop()可以停止一个线程，注意stop()方法是可以由一个线程去停止另外一个线程，
这种方法太过暴力而且是不安全的，怎么说呢，线程A调用线程B的stop方法去停止线程B，调用这个方法的时候线程A其实并不知道线程B执行的具体情况，
这种突然间地停止会导致线程B的一些清理工作无法完成，还有一个情况是执行stop方法后线程B会马上释放锁，这有可能会引发数据不同步问题。
基于以上这些问题，stop()方法被抛弃了。
在这样的情况下，interrupt()方法出现了，它与stop不同，它不会真正停止一个线程，它仅仅是给这个线程发了一个信号告诉它它应该结束了（设置一个停止标志）。
真正符合安全的做法，就是让线程自己去结束自己，而不是让一个线程去结束另外一个线程。
通过interrupt()和.interrupted()方法两者的配合可以实现正常去停止一个线程，线程A通过调用线程B的interrupt方法通知线程B让它结束线程，在线程B的run方法内部，通过循环检查.
interrupted()方法是否为真来接收线程A的信号，如果为真就可以抛出一个异常，在catch中完成一些清理工作，然后结束线程。
Thread.interrupted()会清除标志位，并不是代表线程又恢复了，可以理解为仅仅是代表它已经响应完了这个中断信号然后又重新置为可以再次接收信号的状态。
从始至终，理解一个关键点，interrupt()方法仅仅是改变一个标志位的值而已，和线程的状态并没有必然的联系。理解了这点，其他的问题就很清晰了。

