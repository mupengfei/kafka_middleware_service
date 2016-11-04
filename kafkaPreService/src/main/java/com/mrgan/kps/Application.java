package com.mrgan.kps;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;

import com.mrgan.kps.kafka.producer.ProducerFactory;
import com.mrgan.kps.kafka.producer.ProducerPool;

@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {
	private static Logger logger = LogManager.getLogger(Application.class
			.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(2046);
	}

	// @Bean
	// public ProducerPool generateProducerPool(
	// @Qualifier("producerFactory") ProducerFactory producerFactory) {
	// return new ProducerPool(producerFactory);
	// }

	@Bean
	public GenericObjectPoolConfig genericObjectPoolConfig() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setJmxEnabled(false);
		return config;
	}
}
