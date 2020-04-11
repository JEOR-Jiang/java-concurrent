package jeor.thread.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author jeor
 * @Date 2020/4/11 10:09
 * @Version 1.0
 * 四种多线程的实现：Thread，Runable,Callable,Executers
 */
public class hello {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.Thread
        HelloThread thread = new HelloThread("___小明");
        HelloThread thread2 = new HelloThread("小红");
//        new Thread(thread).start();
//        new Thread(thread2).start();

        // 2.Runable
        HelloRunable runable = new HelloRunable();
//        new Thread(runable, "明哥").start();
//        new Thread(runable, "红姐").start();

        // 3.Callable
//        HelloCallable callable = new HelloCallable();
//        FutureTask future = new FutureTask(callable);
//        new Thread(future, "father").start();
//        HelloCallable callable2 = new HelloCallable();
//        FutureTask future2 = new FutureTask(callable);
//        new Thread(future2, "mother").start();
//        String res1 = (String) future.get();
//        String res2 = (String) future2.get();
//        System.out.println(res1);
//        System.out.println(res2);

        // 4.Executors

        BlockingQueue queue = new ArrayBlockingQueue(20);
        ExecutorService pool =  Executors.newFixedThreadPool(3);    // Executors最大池值太大有性能问题通常不用。
        pool = new ThreadPoolExecutor(3, 6, 20, TimeUnit.MINUTES, queue);
        List<Future> futures = new ArrayList<Future>();
        for (int i = 0; i < 10; i++) {
            Future future = pool.submit(new HelloCallable());
            futures.add(future);
        }
        for (Future future : futures){
            while (!future.isDone()){}
            System.out.println(future.get());
        }
    }

}

class HelloThread extends Thread{
    private String name;
    public HelloThread(String name){
        this.name = name;
    }
    @Override
    public void run() {
        while (true){
            System.out.println(this.name+"向前进一步");
        }
    }
}
class HelloRunable implements Runnable{
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"向前进一步");
        }
    }
}
class HelloCallable implements Callable<String>{
    private int ticket = 0;
    public String call() throws Exception {
        while (ticket<100){
            ticket++;
            System.out.println(Thread.currentThread().getName() + String.format("走到第%s步", ticket));
        }
        return "fish";
    }
}
