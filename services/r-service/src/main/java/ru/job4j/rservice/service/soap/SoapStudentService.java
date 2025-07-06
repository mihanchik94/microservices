package ru.job4j.rservice.service.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://example.com/soap/", name = "StudentService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SoapStudentService {

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
    void publishGetStudents(String key);


    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(
            localName = "getStudentByGradeBookNumber",
            targetNamespace = "http://example.com/soap/",
            className = "ru.job4j.sservice.service.GetStudentByGradeBookNumberRequest")
    @WebMethod(action = "urn:GetStudentByGradeBookNumber")
    @ResponseWrapper(
            localName = "getStudentByGradeBookNumberResponse",
            targetNamespace = "http://example.com/soap/",
            className = "ru.job4j.sservice.service.GetStudentByGradeBookNumberResponse")
    void publishGetStudentByGradeBookNumber(String key, String gradeBookNumber);
}

