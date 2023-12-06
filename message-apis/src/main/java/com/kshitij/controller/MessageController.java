package com.kshitij.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kshitij.models.Message;
import com.kshitij.services.MessageService;


@RestController
@RequestMapping("message-service")
public class MessageController 
{
	@Autowired
	private MessageService messageService;
	@PostMapping("create")
	public ResponseEntity<Message> createMessageResource(@RequestBody Message message)
	{
		messageService.saveMessage(message);
		//ResponseEntity<Message> responseEntity=new ResponseEntity<>(message,HttpStatus.CREATED);
	    //return responseEntity;
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
	@GetMapping("list")
	public ResponseEntity<List<Message>> getMessageList()
	{
		List<Message> messageList=messageService.getList();
		return ResponseEntity.ok(messageList);
	}
	//http://localhost:8080/message-service/message/112
	@GetMapping("message/{mid}")
	public ResponseEntity<Message> getMessage1(@PathVariable int mid)
	{
		Message message=messageService.getMessage(mid);
		if(message==null)
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(message);
	}
	//http://localhost:8080/message-service/message?mid=112
	@GetMapping("message")
	public ResponseEntity<Message> getMessage2(@RequestParam int mid)
	{
		Message message=messageService.getMessage(mid);
		if(message==null)
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("list/listbysender")
	public ResponseEntity<List<Message>> getMessageBySender(@RequestParam String name)
	{
		List<Message> message=messageService.getMessageBySenderName(name);
		if(message.isEmpty())
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("list/listbydate")
	public ResponseEntity<List<Message>> getMessageByDate(@RequestParam String date)
	{
		List<Message> message=messageService.getMessageBySenderDate(date);
		if(message.isEmpty())
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(message);
	}
	@DeleteMapping("delete")
	public ResponseEntity<Message> deleteById(@RequestParam int mid)
	{
		Message message=messageService.getMessage(mid);
		if(message==null)
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		messageService.deleteById(mid);
		return ResponseEntity.ok(message);
	}
	@DeleteMapping("delete/bysender")
	public ResponseEntity<List<Message>> deleteMessageBySender(@RequestParam String name)
	{
		List<Message> message=messageService.getMessageBySenderName(name);
		if(message.isEmpty())
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		messageService.deleteMessageBySenderName(name);
		return ResponseEntity.ok(message);
	}
	
	@PatchMapping("partialupdate/{id}")
	public ResponseEntity<Message> updateMessagePartially(@PathVariable("id") int mid , @RequestBody Message mess)
	{
		Message message=messageService.getMessage(mid);
		if(message==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		if(mess.getSender()!=null) 
		    message.setSender(mess.getSender());
		if(mess.getText()!=null)
			message.setText(mess.getText());
		messageService.saveMessage(message);
		return ResponseEntity.ok(message);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Message> updateMessage(@PathVariable("id") int mid , @RequestBody Message mess)
	{
		Message message=messageService.getMessage(mid);
		if(message==null)
		{
			messageService.saveMessage(mess);
			ResponseEntity.status(HttpStatus.CREATED).body(mess);
		}
		mess.setMid(mid);
		messageService.saveMessage(mess);
		return ResponseEntity.ok(mess);
	}	
	
}
