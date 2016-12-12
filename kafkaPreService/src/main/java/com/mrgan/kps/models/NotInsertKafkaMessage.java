package com.mrgan.kps.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotInsertKafkaMessage {
	@Id
	private String id;
	private String topic;
	private String msg;
	private long sendTime;
	private String ip;
	private String service;
}
