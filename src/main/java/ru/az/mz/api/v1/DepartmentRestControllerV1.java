package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.DepartmentDtoV2;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.DepartmentServiceV1;
import ru.az.mz.services.MyException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentRestControllerV1 {

    private final DepartmentServiceV1 departmentServiceV1;
    private final SetupParameters setupParameters;

    public DepartmentRestControllerV1(
            DepartmentServiceV1 departmentServiceV1,
            SetupParameters setupParameters
    ) {
        this.departmentServiceV1 = departmentServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<DepartmentDtoV2> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) throws MyException {
        return departmentServiceV1.findAll(pageRequestDtoV1).map(DepartmentDtoV2::createWithOrganization);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public DepartmentDtoV2 findById(
            @PathVariable Long id
    ) throws MyException {
        return DepartmentDtoV2.createWithOrganization(departmentServiceV1.findById(id));
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('user:read', 'user:write')")
    public List<DepartmentDtoV2> findAllByOrg(
            @RequestParam(defaultValue = "0") Long orgId
    ) throws MyException {
        return orgId > 0
                ? departmentServiceV1.findAllByOrgId(orgId).stream()
                .map(DepartmentDtoV2::createWithOrganization)
                .collect(Collectors.toList())
                : departmentServiceV1.findAll().stream()
                .map(DepartmentDtoV2::createWithOrganization)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public DepartmentDtoV2 add(
            @RequestBody DepartmentDtoV2 departmentDto
    ) throws MyException {
        return DepartmentDtoV2.create(departmentServiceV1.add(departmentDto));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public DepartmentDtoV2 update(
            @RequestBody DepartmentDtoV2 departmentDto
    ) throws MyException {
        return DepartmentDtoV2.create(departmentServiceV1.update(departmentDto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        departmentServiceV1.delete(id);
        return HttpStatus.OK;
    }

}
