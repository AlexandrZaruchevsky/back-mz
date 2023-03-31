package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.EquipDtoV1;
import ru.az.mz.dto.v1.EquipParentsDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.services.EquipServiceV1;
import ru.az.mz.services.MyException;

@RestController
@RequestMapping("api/v1/equips")
public class EquipRestControllerV1 {

    private final EquipServiceV1 equipServiceV1;
    private final SetupParameters setupParameters;

    public EquipRestControllerV1(EquipServiceV1 equipServiceV1, SetupParameters setupParameters) {
        this.equipServiceV1 = equipServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping("equip-parents")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public EquipParentsDtoV1 getEquipParents() {
        return equipServiceV1.getEquipParents();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<EquipDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        PageRequest pageRequest = pageRequestDtoV1 != null
                ? PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize())
                : setupParameters.getPageRequestDefault();
        return equipServiceV1.findAll(pageRequest).map(EquipDtoV1::create);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public EquipDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return EquipDtoV1.createWithEquipModel(equipServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EquipDtoV1 add(
            @RequestBody EquipDtoV1 equipDtoV1
    ) throws MyException {
        return EquipDtoV1.create(equipServiceV1.add(equipDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public EquipDtoV1 update(
            @RequestBody EquipDtoV1 equipDtoV1
    ) throws MyException {
        return EquipDtoV1.create(equipServiceV1.update(equipDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return equipServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
