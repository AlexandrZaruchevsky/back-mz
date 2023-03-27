package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.OrganizationDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.OrganizationServiceV1;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/organizations")
public class OrganizationRestControllerV1 {

    private final OrganizationServiceV1 organizationService;
    private final SetupParameters setupParameters;

    public OrganizationRestControllerV1(OrganizationServiceV1 organizationService, SetupParameters setupParameters) {
        this.organizationService = organizationService;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<OrganizationDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        PageRequest pageRequest = pageRequestDtoV1 == null
                ? setupParameters.getPageRequestDefault()
                : PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
        return organizationService.findAll(pageRequest).map(OrganizationDtoV1::create);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public OrganizationDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return organizationService.findByIdWithAllDependencies(id);
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public List<OrganizationDtoV1> findAll(){
        return organizationService.findAll().stream()
                .map(OrganizationDtoV1::create)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public OrganizationDtoV1 add(
            @RequestBody OrganizationDtoV1 organizationDtoV1
    ) throws MyException {
        return OrganizationDtoV1.create(organizationService.add(organizationDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public OrganizationDtoV1 update(
            @RequestBody OrganizationDtoV1 organizationDtoV1
    ) throws MyException {
        return OrganizationDtoV1.create(organizationService.update(organizationDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        organizationService.delete(id);
        return HttpStatus.OK;
    }

}
