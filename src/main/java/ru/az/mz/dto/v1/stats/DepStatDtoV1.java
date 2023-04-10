package ru.az.mz.dto.v1.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.az.mz.model.Department;

import java.util.Optional;

@Data
@AllArgsConstructor
public class DepStatDtoV1 {

    Long depId;
    String depName;
    Long emplCount;

    public static Optional<DepStatDtoV1> create(Department department) {
        if (department!=null && department.getId()!=null) {
            return Optional.of(new DepStatDtoV1(
                    department.getId(),
                    department.getName(),
                    0L
            ));
        }else {
            return Optional.empty();
        }
    }

}
