package com.kshitij.services;

import java.util.List;

import com.kshitij.models.Message;

public interface MessageService 
{

	void saveMessage(Message message);

	List<Message> getList();

	Message getMessage(int mid);

	List<Message> getMessageBySenderName(String name);

	List<Message> getMessageBySenderDate(String date);

	void deleteById(int mid);

	void deleteMessageBySenderName(String name);

}
