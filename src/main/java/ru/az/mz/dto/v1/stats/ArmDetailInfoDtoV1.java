package ru.az.mz.dto.v1.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import ru.az.mz.model.ArmDetail;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmDetailInfoDtoV1 {

    private Long id;
    private String name;
    private String domainName;
    private String ipV4;
    private String description;
    private EquipInfoV1 equip;

    public static Optional<ArmDetailInfoDtoV1> create(ArmDetail armDetail) {
        if (armDetail == null) return Optional.empty();
        ArmDetailInfoDtoV1 armDetailInfo = new ArmDetailInfoDtoV1();
        BeanUtils.copyProperties(armDetail, armDetailInfo);
        EquipInfoV1.create(armDetail.getEquip())
                .ifPresent(armDetailInfo::setEquip);
        return Optional.of(armDetailInfo);
    }

}
