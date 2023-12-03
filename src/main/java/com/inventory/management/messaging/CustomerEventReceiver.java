package com.inventory.management.messaging;

import com.inventory.management.entity.Event;
import com.inventory.management.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerEventReceiver {

    @Autowired
    private CustomerService service;

    private static final String TOPIC_ORDER = "orderBroadcastTopic";
    @KafkaListener(topics = TOPIC_ORDER, groupId = "customer-group", containerFactory = "tranRecordListener")
    private void listenOrderEvents(Event event) throws Exception {
        log.info("Received message :" + event + " in " + TOPIC_ORDER);
        System.out.println("Received message :" + event + " in " + TOPIC_ORDER);
        service.syncCustomerOrder(event);
    }
}
