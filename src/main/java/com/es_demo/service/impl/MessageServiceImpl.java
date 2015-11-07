package com.es_demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es_demo.dao.MessageDao;
import com.es_demo.model.Message;
import com.es_demo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public Message insert(Message message) {
		return messageDao.insert(message);
	}

	@Override
	public List<Message> findMessages() {
		return messageDao.findMessages();
	}
	
}
