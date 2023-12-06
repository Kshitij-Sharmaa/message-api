package com.kshitij.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kshitij.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>
{
    public List<Message> getListBySender(String name);

	public List<Message> getListByDate(String date);

	//@Transactional
	//public void deleteBySender(String name);

	@Transactional
	@Modifying
	@Query("delete from Message where sender=:arg")
	public void deleteBySender(@Param("arg") String name);
	
}
