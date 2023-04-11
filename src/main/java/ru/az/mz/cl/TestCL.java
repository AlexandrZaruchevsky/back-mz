package ru.az.mz.cl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.az.mz.dto.v1.stats.EmplInfoDtoV1;
import ru.az.mz.services.OrganizationServiceV1;
import ru.az.mz.services.stats.StatEmplServiceV1;

@Component
public class TestCL implements CommandLineRunner {

    private final OrganizationServiceV1 organizationServiceV1;
    private final StatEmplServiceV1 statEmplServiceV1;

    public TestCL(OrganizationServiceV1 organizationServiceV1, StatEmplServiceV1 statEmplServiceV1) {
        this.organizationServiceV1 = organizationServiceV1;
        this.statEmplServiceV1 = statEmplServiceV1;
    }

    @Override
    public void run(String... args) throws Exception {
//        List<EmployeeDtoV1> employees = organizationServiceV1.findByIdWithAllEmployees(1L).getEmployees();
//        employees.forEach(System.out::println);
//        8727
        EmplInfoDtoV1 emplInfo = statEmplServiceV1.getEmplInfoById(8727L);
        System.out.println(emplInfo);
    }
}
