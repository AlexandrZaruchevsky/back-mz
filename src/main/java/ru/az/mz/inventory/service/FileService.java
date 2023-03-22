package ru.az.mz.inventory.service;

import jcifs.smb1.smb1.NtlmPasswordAuthentication;
import jcifs.smb1.smb1.SmbFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileService {

    private final String domain;
    private final String username;
    private final String password;

    public FileService(
            String domain,
            String username,
            String password
    ) {
        this.domain = domain;
        this.username = username;
        this.password = password;
    }

    public InputStream getInputStreamFromFileByUrl(String url) throws IOException {
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain, username, password);
        SmbFile file = new SmbFile(url, auth);
        return file.getInputStream();
    }

    public String createFromInputStream(InputStream in) throws IOException {
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }

    public InputStream createFromString(String s){
        return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
    }

}
