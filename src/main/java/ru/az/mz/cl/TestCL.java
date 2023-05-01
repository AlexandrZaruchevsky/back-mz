package ru.az.mz.cl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.az.mz.services.OrganizationServiceV1;
import ru.az.mz.services.SubnetServiceV1;
import ru.az.mz.services.impl.EquipNetServiceV1Impl;
import ru.az.mz.services.stats.StatEmplServiceV1;
import ru.az.mz.services.utils.xml.SubnetXml;
import ru.az.sfr.util.glpi.configmachine.service.JAXBService;

@Component
public class TestCL implements CommandLineRunner {

    private final OrganizationServiceV1 organizationServiceV1;
    private final StatEmplServiceV1 statEmplServiceV1;
    private final SubnetServiceV1 subnetServiceV1;
    private final EquipNetServiceV1Impl equipNetServiceV1;

    private final JAXBService<SubnetXml> jaxbService = new JAXBService<>();

    public TestCL(
            OrganizationServiceV1 organizationServiceV1,
            StatEmplServiceV1 statEmplServiceV1,
            SubnetServiceV1 subnetServiceV1,
            EquipNetServiceV1Impl equipNetServiceV1
    ) {
        this.organizationServiceV1 = organizationServiceV1;
        this.statEmplServiceV1 = statEmplServiceV1;
        this.subnetServiceV1 = subnetServiceV1;
        this.equipNetServiceV1 = equipNetServiceV1;
    }

    @Override
    public void run(String... args) throws Exception {
//        List<EmployeeDtoV1> employees = organizationServiceV1.findByIdWithAllEmployees(1L).getEmployees();
//        employees.forEach(System.out::println);
//        8727
//        EmplInfoDtoV1 emplInfo = statEmplServiceV1.getEmplInfoById(8727L);
//        System.out.println(emplInfo);

//        SubnetMap subnetMap = subnetServiceV1.findById(11L);
//        String decompress = StringCompressor.decompress(subnetMap.getSubnetMapXml());
//
//        SubnetXml objectFromFile = jaxbService.createFromXmlString(
//                decompress,
//                SubnetXml.class
//        );
//
//        System.out.println(objectFromFile);
//
//        System.out.println(decompress);
        equipNetServiceV1.addOrUpdateFromSubnetMap();
    }
}
