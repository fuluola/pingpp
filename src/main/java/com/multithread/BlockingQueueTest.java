package com.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年10月31日 
 */
public class BlockingQueueTest {
	 
    public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(20);
 
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);
 
        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);
 
        Thread.sleep(10 * 1000);
//        producer1.stop();
//        producer2.stop();
//        producer3.stop();
 
//        Thread.sleep(1000);
        // 退出Executor
//        service.shutdown();
    }
}
