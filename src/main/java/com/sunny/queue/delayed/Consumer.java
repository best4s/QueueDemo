package com.sunny.queue.delayed;

import java.util.concurrent.DelayQueue;

/**
 * <pre>
 * Description	消费者
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Version: 	1.0
 * Create at:	2017年7月19日 下午4:47:11  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class Consumer implements Runnable {

	// 延时队列
	private DelayQueue<Task> queue;

	public Consumer(DelayQueue<Task> queue) {
		this.queue = queue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Task task = queue.take();
				if (null != task) 
				System.out.println("消费消息：" + task.getId() + ":" + task.getBody());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
	}
}
