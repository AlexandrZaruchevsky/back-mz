package ru.az.mz.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.stats.EmplInfoDtoV1;
import ru.az.mz.dto.v1.stats.EmplStatDtoV1;
import ru.az.mz.services.stats.StatEmplServiceV1;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/stats")
public class StatRestControllerV1 {

    private final StatEmplServiceV1 statEmplServiceV1;

    public StatRestControllerV1(StatEmplServiceV1 statEmplServiceV1) {
        this.statEmplServiceV1 = statEmplServiceV1;
    }

    @GetMapping("employees")
    public EmplStatDtoV1 getEmplStat() {
        return statEmplServiceV1.getStatistics();
    }

    @GetMapping("employee/arms")
    public ResponseEntity<EmplInfoDtoV1> getArmByUsername(
            @RequestParam(name = "username",required = false, defaultValue = "") String username
    ){
        Optional<EmplInfoDtoV1> emplInfo = statEmplServiceV1.getEmplInfoByUsername(username);
        return emplInfo.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(emplInfo.get());
    }

    @GetMapping("dep/{depId}/employees")
    public List<EmployeeDtoV1> findAllByDep(
            @PathVariable Long depId
    ) throws InterruptedException {
//        Thread.sleep(3000);
        return statEmplServiceV1.findAllByDep(depId);
    }


}
