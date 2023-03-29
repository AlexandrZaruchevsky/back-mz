package ru.az.mz.cl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.services.OrganizationServiceV1;

import java.util.List;

//@Component
public class TestCL implements CommandLineRunner {

    private final OrganizationServiceV1 organizationServiceV1;

    public TestCL(OrganizationServiceV1 organizationServiceV1) {
        this.organizationServiceV1 = organizationServiceV1;
    }

    @Override
    public void run(String... args) throws Exception {
        List<EmployeeDtoV1> employees = organizationServiceV1.findByIdWithAllEmployees(1L).getEmployees();
        employees.forEach(System.out::println);
    }
}
