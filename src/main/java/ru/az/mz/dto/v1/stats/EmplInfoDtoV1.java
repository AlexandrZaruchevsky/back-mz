package ru.az.mz.dto.v1.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.az.mz.dto.v1.EmployeeDtoV1;
import ru.az.mz.model.Employee;
import ru.az.mz.model.EntityStatus;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmplInfoDtoV1 {

    private Long emplId;
    private EmployeeDtoV1 employee;
    private List<ArmInfoV1> armInfos;

    public static Optional<EmplInfoDtoV1> create(Employee employee, List<ArmInfoV1> armInfos) {
        if (employee == null  || !EntityStatus.ACTIVE.equals(employee.getStatus())) return Optional.empty();
        EmplInfoDtoV1 emplInfo = new EmplInfoDtoV1();
        emplInfo.setEmplId(employee.getId());
        emplInfo.setEmployee(EmployeeDtoV1.createWithAll(employee));
        emplInfo.setArmInfos(armInfos);
        return Optional.of(emplInfo);
    }

}
