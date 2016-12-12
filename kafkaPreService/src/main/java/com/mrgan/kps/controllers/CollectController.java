package com.mrgan.kps.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrgan.kps.Application;
import com.mrgan.kps.kafka.producer.ProducerPool;
import com.mrgan.kps.models.Message;
import com.mrgan.kps.models.NotInsertKafkaMessage;
import com.mrgan.kps.models.dao.MessageDAO;
import com.mrgan.kps.models.dao.NotInsertKafkaMessageDAO;
import com.mrgan.kps.utils.NetUtils;

@RestController
@RequestMapping("/collect")
public class CollectController {
	private Logger logger = LogManager.getLogger(getClass().getName());
	@Autowired
	private NotInsertKafkaMessageDAO notInsertKafkaMessageDAO;

	@Autowired
	private ProducerPool producerPool;

	@RequestMapping(value = { "/kk" }, method = RequestMethod.POST)
	@ResponseBody
	public int collectKankanews(@RequestParam String data, HttpServletRequest request) {
		if (data.trim().equals(""))
			return 0;
		Producer<String, String> producer = null;
		try {
			producer = producerPool.borrowObject();
			Future<RecordMetadata> future = producer
					.send(new ProducerRecord<String, String>("kkCollect", NetUtils.getIpAddr(request), data));
			System.out.println(future.get());
		} catch (Exception e) {
			NotInsertKafkaMessage message = new NotInsertKafkaMessage(null, "kkCollect", data,
					System.currentTimeMillis(), NetUtils.getIpAddr(request), "kankanews");
			notInsertKafkaMessageDAO.save(message);
			logger.error("", e);
		} finally {
			if (producer != null)
				producerPool.returnObject(producer);
		}
		return 1;
	}

}
