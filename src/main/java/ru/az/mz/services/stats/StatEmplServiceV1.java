package ru.az.mz.services.stats;

import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.dto.v1.stats.EmplInfoDtoV1;
import ru.az.mz.dto.v1.stats.EmplStatDtoV1;

import java.util.List;
import java.util.Optional;

public interface StatEmplServiceV1 {

    EmplStatDtoV1 getStatistics();

    List<EmployeeDtoV1> findAllByDep(Long depId);

    EmplInfoDtoV1 getEmplInfoById(Long emplId);

    Optional<EmplInfoDtoV1> getEmplInfoByUsername(String username);

}
