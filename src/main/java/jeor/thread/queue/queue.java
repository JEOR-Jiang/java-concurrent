package jeor.thread.queue;

import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author jeor
 * @Date 2020/4/11 10:59
 * @Version 1.0
 * 队列的四种API实现操作：指定获取，poll一个，一直等待确保成功，等待一段时间
 *  Queue没有等待功能
 *  BlockingQueue拥有等待功能
 */
public class queue {
    public static void main(String[] args) throws InterruptedException {
        //  1.add,remove,element

        Queue queue = new ArrayBlockingQueue(3);
        queue.add(1);
        System.out.println(queue.element());
        queue.add(2);
        queue.remove(2);
        System.out.println(queue.element());
        queue.remove(3);
        System.out.println(String.format("第一种结果：%s", queue.size()));

        //  2.offer,poll,peek
        queue.offer(12);
        queue.offer(122);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(String.format("第二种结果：%s", queue.size()));
        //  3.put,take
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.put(12);
        blockingQueue.put(13);
        blockingQueue.put(14);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(String.format("第三种结果：%s", blockingQueue.size()));
        //  4.offer,poll
        blockingQueue.offer(12, 2, TimeUnit.SECONDS);
        System.out.println(blockingQueue.size());
        blockingQueue.offer(12, 2, TimeUnit.SECONDS);
        System.out.println(blockingQueue.size());
        blockingQueue.offer(12, 2, TimeUnit.SECONDS);
        System.out.println(blockingQueue.size());
        System.out.println(String.format("第四种结果：%s", blockingQueue.size()));
    }
}
