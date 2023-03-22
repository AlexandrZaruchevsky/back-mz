package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.PositionDtoV1;
import ru.az.mz.model.Position;

import java.util.List;

public interface PositionServiceV1 extends CrudServiceV1<Position, PositionDtoV1> {

    Page<Position> findByName(String name, Pageable pageable);

    List<Position> findAllByOrgId(Long id);

}
