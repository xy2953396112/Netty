package com.xzh.netty.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
     
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
	    Runnable task1 = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				System.out.println("I am task1..");
			}};
		Callable<Integer> task2 = new Callable<Integer>() {

			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return new Integer(10);
			}
		};
		//每传递一个Runnable对象到ExecutorService.submit()方法得到一个回调的Future
		Future<?> f1 = executorService.submit(task1);
		Future<?> f2 = executorService.submit(task2);
		
	    System.out.println("task1 is completed?:"+f1.isDone());
	    System.out.println("task2 is completed?:"+f2.isDone());
	    
	    while(f1.isDone()){
	    	System.out.println("task1 completed");
	        break;
	    }
	    while(f2.isDone()){
	    	System.out.println("task2 completed");
	        break;
	    }
	}
}
