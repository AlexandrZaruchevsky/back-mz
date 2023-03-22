package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.EmployeeServiceV1;
import ru.az.mz.services.MyException;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeRestControllerV1 {

    private final EmployeeServiceV1 employeeServiceV1;
    private final SetupParameters setupParameters;

    public EmployeeRestControllerV1(EmployeeServiceV1 employeeServiceV1, SetupParameters setupParameters) {
        this.employeeServiceV1 = employeeServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<EmployeeDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        Sort sortBy = Sort.by("lastName", "firstName", "middleName");
        PageRequest pageRequest = setupParameters.getPageRequestDefault().withSort(sortBy);
        if (pageRequestDtoV1 != null) {
            pageRequest = PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
            if ("".equalsIgnoreCase(pageRequestDtoV1.getSortBy().trim())) {
                return employeeServiceV1.findAll(pageRequest.withSort(sortBy))
                        .map(EmployeeDtoV1::createWithPositionAndDepartment);
            }else{
                if("fio".equalsIgnoreCase(pageRequestDtoV1.getSortBy().trim())){
                    return employeeServiceV1.findByFIO(pageRequestDtoV1.getSearch(),pageRequest.withSort(sortBy))
                            .map(EmployeeDtoV1::createWithPositionAndDepartment);
                }else{
                    sortBy = Sort.by("kspd");
                    return employeeServiceV1.findByKspd(pageRequestDtoV1.getSearch(),pageRequest.withSort(sortBy))
                            .map(EmployeeDtoV1::createWithPositionAndDepartment);
                }
            }
        } else {
            return employeeServiceV1.findAll(pageRequest)
                    .map(EmployeeDtoV1::createWithPositionAndDepartment);
        }
//        PageRequest pageRequest = pageRequestDtoV1 == null
//                ? setupParameters.getPageRequestDefault().withSort(Sort.by("lastName", "firstName", "middleName"))
//                : PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize()).withSort(Sort.by("lastName", "firstName", "middleName"));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public EmployeeDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return EmployeeDtoV1.createWithAll(employeeServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EmployeeDtoV1 add(
            @RequestBody EmployeeDtoV1 employeeDtoV1
    ) throws MyException {
        return EmployeeDtoV1.createWithAll(employeeServiceV1.add(employeeDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EmployeeDtoV1 update(
            @RequestBody EmployeeDtoV1 employeeDtoV1
    ) throws MyException {
        return EmployeeDtoV1.create(employeeServiceV1.update(employeeDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return employeeServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
