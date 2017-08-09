package com.sunny.queue.delayed;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Description	延时消息实体 
 * Copyright:	Copyright (c)2017
 * Author:		Sunny
 * Version: 	1.0
 * Create at:	2017年7月19日 下午4:43:21  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
public class Task implements Delayed {
	private int id;		// id
	private String body; // 消息内容
	private long excuteTime;// 执行时间

	/**
	 * @param id
	 * @param body
	 * @param delayTime
	 */
	public Task(int id, String body, long delayTime) {
		this.id = id;
		this.body = body;
		this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Delayed delayed) {
		Task msg = (Task) delayed;
		return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1 : (Integer.valueOf(this.id) < Integer
				.valueOf(msg.id) ? -1 : 0);
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	/**
	 * @return the {@link #id}
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the {@link #id} to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the {@link #body}
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the {@link #body} to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the {@link #excuteTime}
	 */
	public long getExcuteTime() {
		return excuteTime;
	}

	/**
	 * @param excuteTime the {@link #excuteTime} to set
	 */
	public void setExcuteTime(long excuteTime) {
		this.excuteTime = excuteTime;
	}
}
