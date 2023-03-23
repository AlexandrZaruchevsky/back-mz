package ru.az.mz.api.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.az.sfr.util.ad.xml.model.ADXML;
import ru.az.sfr.util.ad.xml.model.dom.ADItem;
import ru.az.sfr.util.ad.xml.model.dom.ADUser;
import ru.az.sfr.util.ad.xml.model.dom.ADUserList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/upload")
@PreAuthorize("hasAuthority('admin:read')")
public class UploadRestControllerV1 {

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public String uploadTest(
            @RequestParam("file") MultipartFile file
    ) throws IOException, ParserConfigurationException, SAXException {
        if(!file.isEmpty()){
            ADUserList parse = ADXML.parse(file.getInputStream());
            parse.getItems().stream()
                    .map(ADItem::getAdUser)
                    .map(ADUser::getDepartment)
                    .filter(Objects::nonNull)
                    .map(String::trim)
//                    .map(s -> s.replaceAll("\\s+",""))
                    .distinct()
                    .forEach(System.out::println);
        }
        return "Upload endpoint";
    }

}
