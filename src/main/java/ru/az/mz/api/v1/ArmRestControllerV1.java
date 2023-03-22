package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.ArmDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.ArmServiceV1;
import ru.az.mz.services.MyException;

@RestController
@RequestMapping("api/v1/arms")
public class ArmRestControllerV1 {

    private final ArmServiceV1 armServiceV1;
    private final SetupParameters setupParameters;

    public ArmRestControllerV1(ArmServiceV1 armServiceV1, SetupParameters setupParameters) {
        this.armServiceV1 = armServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read','admin:write')")
    public Page<ArmDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        PageRequest pageRequest = pageRequestDtoV1 == null
                ? setupParameters.getPageRequestDefault()
                : PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
        return armServiceV1.findAll(pageRequest).map(ArmDtoV1::createWithEmployee);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read','admin:write')")
    public ArmDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return ArmDtoV1.createWithAll(armServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ArmDtoV1 add(
            @RequestBody ArmDtoV1 armDtoV1
    ) throws MyException {
        return ArmDtoV1.create(armServiceV1.add(armDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ArmDtoV1 update(
            @RequestBody ArmDtoV1 armDtoV1
    ) throws MyException {
        return ArmDtoV1.create(armServiceV1.update(armDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return armServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
