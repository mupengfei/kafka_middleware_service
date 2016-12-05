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
	public ProducerPool(
			@Qualifier("producerFactory") ProducerFactory producerFactory,
			GenericObjectPoolConfig config) {
		super(producerFactory, config);
		// TODO Auto-generated constructor stub
	}

	// @Autowired
	// public ProducerPool(
	// @Qualifier("producerFactory") ProducerFactory producerFactory,
	// GenericObjectPoolConfig config, AbandonedConfig abandonedConfig) {
	// super(producerFactory, config, abandonedConfig);
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public Producer<String, String> borrowObject() throws Exception {
		// TODO Auto-generated method stub
		return (Producer<String, String>) super.borrowObject();
	}

//	public Producer<String, String> borroProducer(long borrowMaxWaitMillis)
//			throws Exception {
//		// TODO Auto-generated method stub
//		Producer<String, String> producer = super
//				.borrowObject(borrowMaxWaitMillis);
//		return producer;
//	}

}
