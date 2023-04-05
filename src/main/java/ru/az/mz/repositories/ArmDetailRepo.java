package ru.az.mz.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.Arm;
import ru.az.mz.model.ArmDetail;
import ru.az.mz.model.EntityStatus;

import java.util.List;
import java.util.Optional;

public interface ArmDetailRepo extends PagingAndSortingRepository<ArmDetail, Long> {


    List<ArmDetail> findAllByArmAndStatusOrderByName(Arm arm, EntityStatus status);

    @EntityGraph("ArmDetail.withAll")
    Optional<ArmDetail> findByIdAndStatus(Long id, EntityStatus status);


}
