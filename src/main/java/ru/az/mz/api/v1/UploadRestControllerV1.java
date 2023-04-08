package ru.az.mz.api.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.az.mz.services.MyException;
import ru.az.mz.services.UploadServiceV1;
import ru.az.mz.util.equip.model.EquipCsv;
import ru.az.mz.util.equip.service.CsvService;
import ru.az.sfr.util.ad.xml.model.ADXML;
import ru.az.sfr.util.ad.xml.model.dom.ADItem;
import ru.az.sfr.util.ad.xml.model.dom.ADUser;
import ru.az.sfr.util.ad.xml.model.dom.ADUserList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/upload")
@PreAuthorize("hasAuthority('admin:read')")
public class UploadRestControllerV1 {

    private final UploadServiceV1 uploadServiceV1;
    private final CsvService csvService;

    public UploadRestControllerV1(
            UploadServiceV1 uploadServiceV1,
            CsvService csvService
    ) {
        this.uploadServiceV1 = uploadServiceV1;
        this.csvService = csvService;
    }

    @PostMapping("users")
    @PreAuthorize("hasAuthority('admin:write')")
    public String uploadUsers(
            @RequestParam("file") MultipartFile file
    ) throws IOException, ParserConfigurationException, SAXException, MyException {
        if (!file.isEmpty()) {
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
        }
        return "Upload endpoint";
    }

    @PostMapping("equips")
    @PreAuthorize("hasAuthority('admin:write')")
    public String uploadEquips(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (!file.isEmpty() && Objects.requireNonNull(file.getOriginalFilename()).endsWith(".csv")) {
            List<EquipCsv> equipCsv = csvService.getEquipCsv(file.getInputStream());
            uploadServiceV1.clearEquip();
            uploadServiceV1.loadEquipFromCsv(equipCsv);
        }
        return "Upload equips";
    }

}
