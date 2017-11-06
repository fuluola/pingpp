package com;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年10月30日 
 */
class Driver2 { // ...
	static int N = 5; 
	public static void main(String[] args) throws InterruptedException {
	     CountDownLatch doneSignal = new CountDownLatch(N);
	     Executor e = Executors.newFixedThreadPool(1);
	     
	     for (int i = 0; i < N; ++i) // create and start threads
	       e.execute(new WorkerRunnable(doneSignal, i));

	               // wait for all to finish
//	     TimeUnit.SECONDS.sleep(1);
	     doneSignal.await();
	     System.out.println("main thread end...");
	     
	   }
	 }

	 class WorkerRunnable implements Runnable {
	   private final CountDownLatch doneSignal;
	   private final int i;
	   WorkerRunnable(CountDownLatch doneSignal, int i) {
	     this.doneSignal = doneSignal;
	     this.i = i;
	   }
	   public void run() {
		   
	       doWork(i);
	       doneSignal.countDown();
	     
	   }

	   void doWork(int i) { 
		   System.out.println(i +" thread working..");
	   }
	 }
	 
