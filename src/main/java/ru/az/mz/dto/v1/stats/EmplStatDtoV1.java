package ru.az.mz.dto.v1.stats;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EmplStatDtoV1 {

    Long total;
    Long countActive;
    Long countNotActive;
    Long countDeleted;
    List<DepStatDtoV1> stats;

}
