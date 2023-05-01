package ru.az.sfr.util.glpi.configmachine;

import ru.az.sfr.util.glpi.configmachine.service.FileService;
import ru.az.sfr.util.glpi.configmachine.service.JAXBService;
import ru.az.sfr.util.glpi.configmachine.xmlmodel.v1.RequestV1;

public class App {

    private final static String domain = "0039PFRRU";
    private final static String username = "zaa";
    private final static String password = "12345678";

    public static void main(String[] args) throws Exception {
        FileService fileService = new FileService(domain, username, password);
        String fromInputStream = fileService.createFromInputStream(fileService.getInputStreamFromFileByUrl("smb://ws023021/c$/out.xml"));
        System.out.println(fromInputStream);
        JAXBService<RequestV1> jaxbService = new JAXBService<>();
        RequestV1 requestV11 = jaxbService.getObjectFromFile(fileService.getInputStreamFromFileByUrl("smb://ws023021/c$/out.xml"), RequestV1.class);
        System.out.println(requestV11.getContent().getControllers());
    }

}
