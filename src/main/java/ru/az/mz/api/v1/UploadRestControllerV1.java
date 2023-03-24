package ru.az.mz.api.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.az.mz.services.MyException;
import ru.az.mz.services.UploadServiceV1;
import ru.az.sfr.util.ad.xml.model.ADXML;
import ru.az.sfr.util.ad.xml.model.dom.ADItem;
import ru.az.sfr.util.ad.xml.model.dom.ADUser;
import ru.az.sfr.util.ad.xml.model.dom.ADUserList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/upload")
@PreAuthorize("hasAuthority('admin:read')")
public class UploadRestControllerV1 {

    private final UploadServiceV1 uploadServiceV1;

    public UploadRestControllerV1(UploadServiceV1 uploadServiceV1) {
        this.uploadServiceV1 = uploadServiceV1;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public String uploadTest(
            @RequestParam("file") MultipartFile file
    ) throws IOException, ParserConfigurationException, SAXException, MyException {
        if(!file.isEmpty()){
            ADUserList parse = ADXML.parse(file.getInputStream());
            List<ADUser> adUsers = parse.getItems().stream()
                    .map(ADItem::getAdUser)
                    .collect(Collectors.toList());
            uploadServiceV1.clearEmployeesWithDependencies();
            uploadServiceV1.loadTopDeps(adUsers);
            uploadServiceV1.loadDeps(adUsers);
            uploadServiceV1.loadPositions(adUsers);
            uploadServiceV1.loadPointOfPresences(adUsers);
            uploadServiceV1.loadEmployees(adUsers);
//            List<String> topDepartments = parse.getItems().stream()
//                    .map(ADItem::getAdUser)
//                    .map(ADUser::getCompany)
//                    .filter(Objects::nonNull)
//                    .map(String::trim)
////                    .map(s -> s.replaceAll("\\s+",""))
//                    .distinct()
//                    .sorted()
//                    .collect(Collectors.toList());

        }
        return "Upload endpoint";
    }

}
