package ru.job4j.rservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.rservice.service.RedisService;

@Component
public class GetStudentByGradeBookNumberResponseListener extends AbstractResponseListener {


    public GetStudentByGradeBookNumberResponseListener(RedisService redisService) {
        super(redisService);
    }

    @KafkaListener(topics = "${spring.kafka.topics.get-student-by-grade-book-number-response-topic.name}",
            groupId = "${spring.kafka.consumer.group-id}", containerFactory = "listenerContainerFactory")
    public void listen(ConsumerRecord<String, String> record) {
        processMessage(record);
    }
}
