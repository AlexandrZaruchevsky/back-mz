package ru.az.mz.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.az.mz.dto.v1.stats.EmplStatDtoV1;
import ru.az.mz.services.stats.StatEmplServiceV1;

@RestController
@RequestMapping("api/v1/stats")
public class StatRestControllerV1 {

    private final StatEmplServiceV1 statEmplServiceV1;

    public StatRestControllerV1(StatEmplServiceV1 statEmplServiceV1) {
        this.statEmplServiceV1 = statEmplServiceV1;
    }

    @GetMapping("employees")
    public EmplStatDtoV1 getEmplStat() throws InterruptedException {
        return statEmplServiceV1.getStatistics();
    }


}
