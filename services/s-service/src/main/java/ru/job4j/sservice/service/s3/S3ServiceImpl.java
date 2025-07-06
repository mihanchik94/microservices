package ru.job4j.sservice.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.sservice.properties.S3Properties;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 s3Client;
    private final S3Properties s3Properties;

    @Override
    public byte[] getPictureData(String key) {
        S3Object s3Object = s3Client.getObject(s3Properties.getBucketName(), key);
        try {
            return s3Object.getObjectContent().readAllBytes();
        } catch (IOException e) {
            log.error("Image by key {} is not exist", key);
            return null;
        }
    }
}
