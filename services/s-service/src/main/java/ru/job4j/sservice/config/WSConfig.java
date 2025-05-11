package ru.job4j.sservice.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.CXFBusFactory;
import jakarta.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.job4j.sservice.properties.WSProperties;
import ru.job4j.sservice.service.student.soap.SoapStudentServiceImpl;

@Configuration
@EnableTransactionManagement(proxyTargetClass = false)
public class WSConfig {

    @Autowired
    private SoapStudentServiceImpl studentService;

    @Bean
    public Bus bus() {
        return new CXFBusFactory().createBus();
    }

    @Bean
    public Endpoint getStudentsEndpoint(WSProperties wsProperties) {
        String address = String.format("http://%s:%s/%s/%s", wsProperties.getWsHost(), wsProperties.getWsPort(),
                wsProperties.getWsPrefix(), wsProperties.getStudentEndpoint());
        return Endpoint.publish(address, studentService);
    }
}