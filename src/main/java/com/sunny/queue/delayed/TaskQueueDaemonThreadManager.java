package com.sunny.queue.delayed;

import java.util.concurrent.DelayQueue;

import javax.annotation.PostConstruct;


/**  
 * <pre>
 * Description	消息队列线程处理中心
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
public class TaskQueueDaemonThreadManager {
	
	/**
	 * 创建一个最初为空的新 DelayQueue
	 */
	private final DelayQueue<Task> queue = new DelayQueue<Task>();
	
	/**
	 * 守护线程
	 */
	private Thread daemonThread;

	/**
	 * 初始化守护线程
	 */
	@PostConstruct
	private void init() {
		daemonThread = new Thread(new Runnable() {
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				execute();
			}
		});
		daemonThread.setDaemon(true);
		daemonThread.setName("Task Queue Daemon Thread");
		daemonThread.start();
	}

	/**
	 * 轮询消费队列
	 */
	private void execute() {
		System.out.println("start:" + System.currentTimeMillis());
		while (true) {
			try {
				// 从延迟队列中取值,如果没有对象过期则队列一直等待，
				Task task = queue.take();
				if (task != null) {
					System.out.println("消费消息：" + task.getId() + ":" + task.getBody());
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	/**
	 * 添加任务， time 延迟时间 task 任务 用户为问题设置延迟时间
	 */
	public void put(int id, String body, long time) {
		Task task = new Task(id, body, time);
		queue.offer(task);
	}
}
