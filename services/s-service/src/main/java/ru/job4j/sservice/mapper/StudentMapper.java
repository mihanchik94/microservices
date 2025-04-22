package ru.job4j.sservice.mapper;

import org.mapstruct.*;
import ru.job4j.sservice.dto.StudentDto;
import ru.job4j.sservice.model.Student;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = StudentPictureMapper.class,
        builder = @Builder(disableBuilder = true))
public interface StudentMapper {

    @Mapping(target = "studentPictureDto", source = "studentPicture")
    StudentDto toDto(Student student);

    List<StudentDto> toDtoList(List<Student> students);
}
