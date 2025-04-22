package ru.job4j.rservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Component;
import ru.job4j.rservice.service.RedisService;

@Component
public class GetAllStudentsResponseListener extends AbstractResponseListener {

    public GetAllStudentsResponseListener(RedisService redisService) {
        super(redisService);
    }

    @KafkaListener(topics = "${spring.kafka.topics.get-all-students-response-topic.name}",
            groupId = "${spring.kafka.consumer.group-id}", containerFactory = "listenerContainerFactory")
    public void listen(ConsumerRecord<String, String> record) {
        processMessage(record);
    }
}
