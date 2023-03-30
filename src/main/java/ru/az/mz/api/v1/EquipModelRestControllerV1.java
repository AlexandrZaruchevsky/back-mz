package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.EquipModelDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.EquipModelServiceV1;
import ru.az.mz.services.MyException;

@RestController
@RequestMapping("api/v1/equip-models")
public class EquipModelRestControllerV1 {

    private final EquipModelServiceV1 equipModelServiceV1;
    private final SetupParameters setupParameters;

    public EquipModelRestControllerV1(
            EquipModelServiceV1 equipModelServiceV1,
            SetupParameters setupParameters
    ) {
        this.equipModelServiceV1 = equipModelServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<EquipModelDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) throws MyException {
        return equipModelServiceV1.findAllByName(pageRequestDtoV1);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public EquipModelDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return EquipModelDtoV1.createWithEquipType(equipModelServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EquipModelDtoV1 add(
            @RequestBody EquipModelDtoV1 equipModelDtoV1
    ) throws MyException {
        return EquipModelDtoV1.create(equipModelServiceV1.add(equipModelDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EquipModelDtoV1 update(
            @RequestBody EquipModelDtoV1 equipModelDtoV1
    ) throws MyException {
        return EquipModelDtoV1.create(equipModelServiceV1.update(equipModelDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return equipModelServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
