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

import java.util.List;

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
        return armServiceV1.findAll(pageRequestDtoV1);
    }

    @GetMapping("list-choice")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public List<ArmDtoV1> findAllByNameForChoice(
            @RequestParam(defaultValue = "", required = false) String name
    ){
        return armServiceV1.findAllByNameForChoice(name);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
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
