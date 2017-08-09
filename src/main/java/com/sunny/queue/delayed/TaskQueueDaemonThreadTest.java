package com.sunny.queue.delayed;

import java.util.concurrent.DelayQueue;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Author:		Sunny
 * Version: 	1.0
 * Create at:	2017年7月19日 下午4:45:50  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
public class TaskQueueDaemonThreadTest {
	
	public static void main(String[] args) throws Exception {
		// 创建延时队列
		DelayQueue<Task> queue = new DelayQueue<Task>();
		
		// 添加延时消息,m1 延时5s
		Task m1 = new Task(1, "b", 3000);
		
		// 添加延时消息,m2 延时3s
		Task m2 = new Task(2, "a", 2000);
		
		queue.offer(m1);
		queue.offer(m2);
		// 启动消费线程
		new Thread(new Consumer(queue)).start();
		
		Thread.sleep(10000l);
		
		Task m3 = new Task(3, "c", 2000);
		queue.offer(m3);
		Task m4 = new Task(4, "d", 2000);
		queue.offer(m4);
		
		// 再次添加
		System.in.read();
	}

}
