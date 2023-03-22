package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.dto.v1.PositionDtoV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.PositionServiceV1;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/positions")
public class PositionRestControllerV1 {

    private final PositionServiceV1 positionServiceV1;
    private final SetupParameters setupParameters;

    public PositionRestControllerV1(PositionServiceV1 positionServiceV1, SetupParameters setupParameters) {
        this.positionServiceV1 = positionServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<PositionDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        PageRequest pageRequest = pageRequestDtoV1 == null
                ? setupParameters.getPageRequestDefault()
                : PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
        return positionServiceV1.findAll(pageRequest).map(PositionDtoV1::create);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public PositionDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return PositionDtoV1.create(positionServiceV1.findById(id));
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public List<PositionDtoV1> findAllByOrgId(
            @RequestParam(defaultValue = "0") Long orgId
    ) {
        return orgId > 0
                ? positionServiceV1.findAllByOrgId(orgId).stream()
                    .map(PositionDtoV1::create)
                    .collect(Collectors.toList())
                : positionServiceV1.findAll().stream()
                    .map(PositionDtoV1::create)
                    .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public PositionDtoV1 add(
            @RequestBody PositionDtoV1 positionDtoV1
    ) throws MyException {
        return PositionDtoV1.create(positionServiceV1.add(positionDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public PositionDtoV1 update(
            @RequestBody PositionDtoV1 positionDtoV1
    ) throws MyException {
        return PositionDtoV1.create(positionServiceV1.update(positionDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return positionServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
