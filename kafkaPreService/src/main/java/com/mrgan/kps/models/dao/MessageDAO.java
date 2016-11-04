package com.mrgan.kps.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mrgan.kps.models.Message;

public interface MessageDAO extends MongoRepository<Message, String> {
	public List<Message> findByTopic(String topic);
}