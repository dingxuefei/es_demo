package com.es_demo.dao;

import java.util.List;

import com.es_demo.model.Message;


/** 
 * @ClassName: MessageDao 
 * @Description: 留言信息
 * @author dingxuefei
 * @date 2015年10月29日 下午7:55:27 
 *  
 */
public interface MessageDao {

	/**
	 * 添加信息
	 * 
	 * @param message
	 * @return
	 */
	public Message insert(Message message);


	/**
	 * 查询记录
	 * 
	 * @return
	 */
	public List<Message> findMessages();

}
