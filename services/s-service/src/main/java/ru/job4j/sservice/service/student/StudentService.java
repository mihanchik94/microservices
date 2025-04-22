package ru.job4j.sservice.service.student;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.job4j.sservice.dto.StudentDto;

import java.util.List;


@WebService(targetNamespace = "http://example.com/soap/", name = "StudentService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface StudentService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "getStudents",
            targetNamespace = "http://example.com/soap/",
            className = "ru.job4j.sservice.service.GetStudentsRequest")
    @WebMethod(action = "urn:GetStudents")
    @ResponseWrapper(
            localName = "getStudentsResponse",
            targetNamespace = "http://example.com/soap/",
            className = "ru.job4j.sservice.service.GetStudentsResponse")
    JAXBElement<List<StudentDto>> getStudents();


    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "getStudents",
            targetNamespace = "http://example.com/soap/",
            className = "ru.job4j.sservice.service.GetStudentByGradeBookNumberRequest")
    @WebMethod(action = "urn:GetStudents")
    @ResponseWrapper(
            localName = "getStudentByGradeBookNumberResponse",
            targetNamespace = "http://example.com/soap/",
            className = "ru.job4j.sservice.service.GetStudentByGradeBookNumberResponse")
    StudentDto getStudentByGradeBookNumber(String getGradeBookNumber);
}
