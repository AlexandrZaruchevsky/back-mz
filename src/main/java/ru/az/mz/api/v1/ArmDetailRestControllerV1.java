package ru.az.mz.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.dto.v1.ArmDetailDtoV1;
import ru.az.mz.services.ArmDetailServiceV1;
import ru.az.mz.services.MyException;

import java.util.List;

@RestController
@RequestMapping("api/v1/arm-details")
public class ArmDetailRestControllerV1 {

    private final ArmDetailServiceV1 armDetailServiceV1;

    public ArmDetailRestControllerV1(ArmDetailServiceV1 armDetailServiceV1) {
        this.armDetailServiceV1 = armDetailServiceV1;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public List<ArmDetailDtoV1> findAllByArm(
            @RequestParam(defaultValue = "-1", required = false) Long id
    ) {
        return armDetailServiceV1.findAllByArm(id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public ArmDetailDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return ArmDetailDtoV1.createWithAll(armDetailServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public ArmDetailDtoV1 add(
            @RequestBody ArmDetailDtoV1 armDetailDtoV1
    ) throws MyException {
        return ArmDetailDtoV1.create(armDetailServiceV1.add(armDetailDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public ArmDetailDtoV1 update(
            @RequestBody ArmDetailDtoV1 armDetailDtoV1
    ) throws MyException {
        return ArmDetailDtoV1.create(armDetailServiceV1.update(armDetailDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public HttpStatus delete(
        @PathVariable Long id
    ) throws MyException {
        return armDetailServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
