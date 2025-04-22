package ru.job4j.sservice.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.sservice.dto.StudentPictureDto;
import ru.job4j.sservice.model.StudentPicture;
import ru.job4j.sservice.service.s3.S3Service;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public abstract class StudentPictureMapper {

    @Autowired
    protected S3Service s3Service;


    @Mapping(target = "imageData", expression = "java(s3Service.getPictureData(entity.getKey()))")
    abstract StudentPictureDto toDtoWithData(StudentPicture entity);
}
