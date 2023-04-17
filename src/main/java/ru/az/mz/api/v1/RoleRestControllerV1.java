package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.dto.v1.RoleDtoV1;
import ru.az.mz.model.Permission;
import ru.az.mz.services.MyException;
import ru.az.mz.services.RoleServiceV1V1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/roles")
public class RoleRestControllerV1 {

    private final SetupParameters setupParameters;
    private final RoleServiceV1V1 roleServiceV1;

    public RoleRestControllerV1(SetupParameters setupParameters, RoleServiceV1V1 roleServiceV1) {
        this.setupParameters = setupParameters;
        this.roleServiceV1 = roleServiceV1;
    }

    @GetMapping("permissions")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public ResponseEntity<List<String>> getPermissions() {
        return ResponseEntity.ok(
                Stream.of(Permission.values())
                        .map(Enum::name)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Page<RoleDtoV1> findAll(
            PageRequestDtoV1 pageRequest
    ) {
        PageRequest pr = pageRequest == null
                ? setupParameters.getPageRequestDefault()
                : PageRequest.of(pageRequest.getPageCurrent(), pageRequest.getPageSize());
        return roleServiceV1.findAll(pr).map(RoleDtoV1::createWithPermissions);
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public List<RoleDtoV1> findAll(){
        return roleServiceV1.findAllRolesDto();
    }

    @GetMapping("{id}")
    public RoleDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return RoleDtoV1.createWithPermissions(roleServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public RoleDtoV1 add(
            @RequestBody RoleDtoV1 roleDtoV1
    ) throws MyException {
        return RoleDtoV1.createWithPermissions(roleServiceV1.add(roleDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public RoleDtoV1 update(
            @RequestBody RoleDtoV1 roleDtoV1
    ) throws MyException {
        return RoleDtoV1.createWithPermissions(roleServiceV1.update(roleDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return roleServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.CONFLICT;
    }

}
