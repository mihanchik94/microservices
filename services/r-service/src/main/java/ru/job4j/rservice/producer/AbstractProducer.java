package ru.job4j.rservice.producer;

import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.rservice.util.XmlSerializer;



@Slf4j
@AllArgsConstructor
public abstract class AbstractProducer<T> {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final XmlSerializer<T> serializer;
    private String topicName;

    public void publish(String key, T request) {
        try {
            String xmlRequest = serializer.serialize(request);
            kafkaTemplate.send(topicName, key, xmlRequest);
        } catch (JAXBException e) {
            log.error("error during serializing: " + e);
            throw new SerializationFailedException(e.getMessage());
        }
        log.info("request {} was sent to kafka topic {}", request, topicName);
    }
}