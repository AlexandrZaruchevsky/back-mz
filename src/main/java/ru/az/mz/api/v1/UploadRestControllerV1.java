package ru.az.mz.api.v1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/v1/upload")
@PreAuthorize("hasAuthority('admin:read')")
public class UploadRestControllerV1 {

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public String uploadTest(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if(!file.isEmpty()){
            String s = new String(file.getBytes());
            System.out.println(s);
        }
        return "Upload endpoint";
    }

}
