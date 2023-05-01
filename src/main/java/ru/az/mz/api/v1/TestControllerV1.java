package ru.az.mz.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.az.sfr.util.glpi.configmachine.service.FileService;
import ru.az.sfr.util.glpi.configmachine.service.JAXBService;
import ru.az.sfr.util.glpi.configmachine.xmlmodel.v1.RequestV1;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/")
public class TestControllerV1 {

    @Value("${security.ldap.domain}")
    private String domain;
    @Value("${security.ldap.username}")
    private String username;
    @Value("${security.ldap.password}")
    private String password;

    @GetMapping
    public String getInfo() {
        return "API version V1";
    }

//    @GetMapping("all/{ws}")
//    public ResponseEntity<?> getConfigWSV1(
//            @PathVariable String ws
//    ) throws Exception {
//        FileService fileService = new FileService(domain, username, password);
//        JAXBService<RequestV1> jaxbService = new JAXBService<>();
//        RequestV1 request = jaxbService.getObjectFromFile(
//                fileService.getInputStreamFromFileByUrl("smb://" + ws + "/c$/out.xml"),
//                RequestV1.class
//        );
//        return ResponseEntity.ok(request.getContent());
//    }

    @ExceptionHandler(JAXBException.class)
    public ResponseEntity<?> getException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка чтения файла: " + e.getCause().getMessage());
    }

    @GetMapping(value = "all/ws")
    public ResponseEntity<?> getWSInfo(
            @RequestParam(defaultValue = "ws023101", required = false) String name
    ) throws Exception {
        FileService fileService = new FileService(domain, username, password);
        ObjectMapper objectMapper = new ObjectMapper();
        JAXBService<RequestV1> jaxbService = new JAXBService<>();
        RequestV1 requestV11 = jaxbService.getObjectFromFile(fileService.getInputStreamFromFileByUrl("smb://"+name+"/c$/out.xml"), RequestV1.class);
        return ResponseEntity.ok(objectMapper.writeValueAsString(requestV11));
    }

}
