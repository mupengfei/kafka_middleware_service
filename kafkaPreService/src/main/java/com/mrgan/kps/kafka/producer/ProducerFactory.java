package com.mrgan.kps.kafka.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

@Component("producerFactory")
public class ProducerFactory extends
		BasePooledObjectFactory<Producer<String, String>> {

	@Override
	public Producer<String, String> create() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.222.110:9092,192.168.222.110:9093,192.168.222.110:9094");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
//		props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
//				"com.mrgan.kafka.partitioner.RandomPartitioner");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class.getName());

		// ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new KafkaProducer<String, String>(
				props);
		return producer;
	}

	@Override
	public PooledObject<Producer<String, String>> wrap(
			Producer<String, String> obj) {
		// TODO Auto-generated method stub
		return new DefaultPooledObject<Producer<String, String>>(obj);
	}

}
