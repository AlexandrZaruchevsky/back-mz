package ru.az.mz.api.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.inventory.xmlmodel.Request;
import ru.az.mz.inventory.service.FileService;
import ru.az.mz.inventory.service.JAXBService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

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

    @GetMapping("all/{ws}")
    public ResponseEntity<?> getConfigWSV1(
            @PathVariable String ws
    ) throws IOException, JAXBException {
        FileService fileService = new FileService(domain, username, password);
        JAXBService<Request> jaxbService = new JAXBService<>();
        Request request = jaxbService.getObjectFromFile(
                fileService.getInputStreamFromFileByUrl("smb://" + ws + "/c$/out.xml"),
                Request.class
        );
        return ResponseEntity.ok(request.getContent());
    }

    @ExceptionHandler(JAXBException.class)
    public ResponseEntity<?> getException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка чтения файла: " + e.getCause().getMessage());
    }

}
