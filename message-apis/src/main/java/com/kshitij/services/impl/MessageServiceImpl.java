package com.kshitij.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.models.Message;
import com.kshitij.repositories.MessageRepository;
import com.kshitij.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService
{
	@Autowired
	private MessageRepository messageRepository;


	public void saveMessage(Message message) 
	{
		message.setDate(LocalDate.now().toString());
		message.setTime(LocalTime.now().toString());
		messageRepository.save(message);		
	}


	
	public List<Message> getList() 
	{
		return messageRepository.findAll();
	
	}




	public Message getMessage(int mid)
	{
	
		return messageRepository.findById(mid).orElse(null);
	}



	
	public List<Message> getMessageBySenderName(String name) 
	{
		
		return messageRepository.getListBySender(name);
	}



	
	public List<Message> getMessageBySenderDate(String date)
	{
		
		return messageRepository.getListByDate(date);
	}



	
	public void deleteById(int mid)
	{
		messageRepository.deleteById(mid);
	}



	public void deleteMessageBySenderName(String name) 
	{
		 messageRepository.deleteBySender(name);
	}

}
