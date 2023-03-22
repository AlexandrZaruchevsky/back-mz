package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.EquipTypeDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.EquipTypeServiceV1;
import ru.az.mz.services.MyException;

@RestController
@RequestMapping("api/v1/equip-types")
public class EquipTypeRestControllerV1 {

    private final EquipTypeServiceV1 equipTypeServiceV1;
    private final SetupParameters setupParameters;


    public EquipTypeRestControllerV1(EquipTypeServiceV1 equipTypeServiceV1, SetupParameters setupParameters) {
        this.equipTypeServiceV1 = equipTypeServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<EquipTypeDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        PageRequest pageRequest = pageRequestDtoV1 == null
                ? setupParameters.getPageRequestDefault()
                : PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
        return equipTypeServiceV1.findAll(pageRequest).map(EquipTypeDtoV1::create);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public EquipTypeDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return EquipTypeDtoV1.create(equipTypeServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EquipTypeDtoV1 add(
            @RequestBody EquipTypeDtoV1 equipTypeDtoV1
    ) throws MyException {
        return EquipTypeDtoV1.create(equipTypeServiceV1.add(equipTypeDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EquipTypeDtoV1 update(
            @RequestBody EquipTypeDtoV1 equipTypeDtoV1
    ) throws MyException {
        return EquipTypeDtoV1.create(equipTypeServiceV1.update(equipTypeDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return equipTypeServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
