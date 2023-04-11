package ru.az.mz.dto.v1.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import ru.az.mz.model.Arm;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmInfoV1 {

    private Long id;
    private String name;
    private String office;
    private String mol;
    private String molFio;
    private String description;
    private Long popId;
    private String popName;
    private List<ArmDetailInfoDtoV1> armDetails;

    public static Optional<ArmInfoV1> create(Arm arm) {
        if (arm == null) return Optional.empty();
        ArmInfoV1 armInfo = new ArmInfoV1();
        BeanUtils.copyProperties(arm, armInfo);
        armInfo.setArmDetails(
                arm.getArmDetails() != null
                        ? arm.getArmDetails().stream()
                                .map(ArmDetailInfoDtoV1::create)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.toList())
                        : Collections.emptyList()
        );
        return Optional.of(armInfo);
    }

}
