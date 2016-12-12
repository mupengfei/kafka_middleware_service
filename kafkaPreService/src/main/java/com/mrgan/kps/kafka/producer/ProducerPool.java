package com.mrgan.kps.kafka.producer;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("producerPool")
public class ProducerPool extends GenericObjectPool<Producer<String, String>> {

	@Autowired
	public ProducerPool(@Qualifier("producerFactory") ProducerFactory producerFactory, GenericObjectPoolConfig config) {
		super(producerFactory, config);
	}

	@Override
	public Producer<String, String> borrowObject() throws Exception {
		return (Producer<String, String>) super.borrowObject();
	}

}
