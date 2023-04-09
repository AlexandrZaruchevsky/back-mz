package ru.az.mz.api.v1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.EmployeeServiceV1;

@RestController
@RequestMapping("api/v1/all")
public class GuestRestControllerV1 {

    private final EmployeeServiceV1 employeeServiceV1;

    public GuestRestControllerV1(@Qualifier("guestEmployeeService")EmployeeServiceV1 employeeServiceV1) {
        this.employeeServiceV1 = employeeServiceV1;
    }

    @GetMapping("employees")
    public Page<EmployeeDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ){
        return employeeServiceV1.findAll(pageRequestDtoV1);
    }

}
