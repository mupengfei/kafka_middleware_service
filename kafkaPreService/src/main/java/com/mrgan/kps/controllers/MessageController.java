package com.mrgan.kps.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrgan.kps.kafka.producer.ProducerPool;
import com.mrgan.kps.models.Message;
import com.mrgan.kps.models.dao.MessageDAO;
import com.mrgan.kps.utils.NetUtils;

@RestController
@RequestMapping("/msg")
public class MessageController {
	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private ProducerPool producerPool;

	@RequestMapping(value = { "/send/{topic}" }, method = RequestMethod.POST)
	public String sendMsg(@PathVariable String topic,
			@RequestParam String service, @RequestParam String msg,
			HttpServletRequest request) {

		Message message = new Message(null, topic, msg,
				System.currentTimeMillis(), NetUtils.getIpAddr(request),
				service);
		messageDAO.save(message);
		try {
			Future<RecordMetadata> future = producerPool.borrowObject().send(
					new ProducerRecord<String, String>("kps", NetUtils
							.getIpAddr(request), msg));
			// System.out.println(future.get().toString());
			future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topic;
	}

	@RequestMapping(value = "/show/{topic}", method = RequestMethod.GET)
	public List<Message> showAllMessage(@PathVariable String topic) {
		return messageDAO.findByTopic(topic);
	}

}
