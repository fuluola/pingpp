package com;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年10月30日 
 */
class Driver { // ...
	   private static int N = 5;
	   public static void main(String[] args) throws InterruptedException {
	     CountDownLatch startSignal = new CountDownLatch(1);
	     CountDownLatch doneSignal = new CountDownLatch(N);

	     for (int i = 0; i < N; ++i) // create and start threads
	       new Thread(new Worker(startSignal, doneSignal)).start();

	     System.out.println("做一些其他事情1。。。");           // don't let run yet
	     startSignal.countDown();      // let all threads proceed
	     TimeUnit.SECONDS.sleep(1);
	     System.out.println("做一些其他事情2。。。");       
	     doneSignal.await();  // wait for all to finish
	     System.out.println("做一些其他事情3。。。"); 
	   }
	 }
class Worker implements Runnable {
	   private final CountDownLatch startSignal;
	   private final CountDownLatch doneSignal;
	   Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
	     this.startSignal = startSignal;
	     this.doneSignal = doneSignal;
	   }
	   public void run() {
	     try {
	       startSignal.await();
	       doWork();
	       doneSignal.countDown();
	     } catch (InterruptedException ex) {} // return;
	   }

	   void doWork() { 
		   System.out.println("work...");
	   }
	 }