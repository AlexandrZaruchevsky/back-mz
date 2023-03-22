package ru.az.mz.api.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.dto.v1.PointOfPresenceDtoV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.PointOfPresenceServiceV1;

@RestController
@RequestMapping("api/v1/point-of-presences")
public class PointOfPresenceRestControllerV1 {

    private final PointOfPresenceServiceV1 pointOfPresenceServiceV1;
    private final SetupParameters setupParameters;


    public PointOfPresenceRestControllerV1(PointOfPresenceServiceV1 pointOfPresenceServiceV1, SetupParameters setupParameters) {
        this.pointOfPresenceServiceV1 = pointOfPresenceServiceV1;
        this.setupParameters = setupParameters;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public Page<PointOfPresenceDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        PageRequest pageRequest = pageRequestDtoV1 == null
                ? setupParameters.getPageRequestDefault()
                : PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
        return pointOfPresenceServiceV1.findAll(pageRequest).map(PointOfPresenceDtoV1::createWithOrganization);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:read','user:write')")
    public PointOfPresenceDtoV1 findById(
            @PathVariable Long id
    ) throws MyException {
        return PointOfPresenceDtoV1.createWithOrganization(pointOfPresenceServiceV1.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public PointOfPresenceDtoV1 add(
            @RequestBody PointOfPresenceDtoV1 pointOfPresenceDtoV1
    ) throws MyException {
        return PointOfPresenceDtoV1.createWithOrganization(pointOfPresenceServiceV1.add(pointOfPresenceDtoV1));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public PointOfPresenceDtoV1 update(
            @RequestBody PointOfPresenceDtoV1 pointOfPresenceDtoV1
    ) throws MyException {
        return PointOfPresenceDtoV1.createWithOrganization(pointOfPresenceServiceV1.update(pointOfPresenceDtoV1));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        return pointOfPresenceServiceV1.delete(id)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
    }

}
