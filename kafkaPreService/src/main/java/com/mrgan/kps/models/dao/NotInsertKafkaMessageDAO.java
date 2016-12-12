package com.mrgan.kps.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mrgan.kps.models.NotInsertKafkaMessage;

public interface NotInsertKafkaMessageDAO extends MongoRepository<NotInsertKafkaMessage, String> {
	public List<NotInsertKafkaMessage> findByTopic(String topic);
}