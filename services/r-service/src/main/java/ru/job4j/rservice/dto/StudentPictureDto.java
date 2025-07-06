package ru.job4j.rservice.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;

import java.io.Serializable;

@XmlRootElement
@Builder(setterPrefix = "with")
public class StudentPictureDto implements Serializable {
    private String key;
    private byte[] imageData;

    public StudentPictureDto() {
    }

    public StudentPictureDto(String key, byte[] imageData) {
        this.key = key;
        this.imageData = imageData;
    }

    @XmlElement
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @XmlElement
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}