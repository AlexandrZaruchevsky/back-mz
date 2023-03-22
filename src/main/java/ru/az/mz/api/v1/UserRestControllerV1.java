package ru.az.mz.api.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.dto.v1.LoginPasswordDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.dto.v1.UserAuthDtoV1;
import ru.az.mz.dto.v1.UserDtoV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.UserServiceV1V1;

@RestController
@RequestMapping("api/v1/users")
public class UserRestControllerV1 {

    @Value("${setup.page.current}")
    private int pageCurrent;
    @Value("${setup.page.size}")
    private int pageSize;

    private final UserServiceV1V1 userServiceV1;

    public UserRestControllerV1(UserServiceV1V1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Page<UserDtoV1> findAll(
            PageRequestDtoV1 pageRequestDtoV1
    ) {
        PageRequest of = pageRequestDtoV1 == null
                ? PageRequest.of(pageCurrent, pageSize)
                : PageRequest.of(pageRequestDtoV1.getPageCurrent(), pageRequestDtoV1.getPageSize());
        return userServiceV1.findAll(of).map(UserDtoV1::create);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<UserDtoV1> add(
            @RequestBody UserAuthDtoV1 userAuthDtoV1
    ) throws MyException {
        return ResponseEntity.ok(UserDtoV1.createWithRole(userServiceV1.add(userAuthDtoV1)));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<UserDtoV1> update(
            @RequestBody UserAuthDtoV1 userAuthDtoV1
    ) throws MyException {
        return ResponseEntity.ok(UserDtoV1.createWithRole(userServiceV1.update(userAuthDtoV1)));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public ResponseEntity<UserDtoV1> findById(
            @PathVariable Long id
    ) throws MyException {
        return ResponseEntity.ok(UserDtoV1.createWithRole(userServiceV1.findById(id)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public HttpStatus delete(
            @PathVariable Long id
    ) throws MyException {
        userServiceV1.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping("change-password")
    @PreAuthorize("hasAuthority('admin:write')")
    public HttpStatus changePassword(
            @RequestBody(required = false) LoginPasswordDtoV1 loginPasswordDtoV1
    ) throws MyException {
        if (loginPasswordDtoV1 == null || "".equals(loginPasswordDtoV1.getPassword().trim()))
            throw new MyException(HttpStatus.BAD_REQUEST);
        userServiceV1.changePassword(loginPasswordDtoV1.getUsername(), loginPasswordDtoV1.getPassword());
        return HttpStatus.OK;
    }

    @GetMapping("{id}/change-status")
    @PreAuthorize("hasAuthority('admin:write')")
    public HttpStatus changeStatus(
            @PathVariable Long id
    ) throws MyException {
        userServiceV1.changeStatus(id);
        return HttpStatus.OK;
    }

}
