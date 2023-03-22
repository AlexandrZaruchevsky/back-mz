package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import ru.az.mz.dto.v1.PointOfPresenceDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Organization;
import ru.az.mz.model.PointOfPresence;

import java.util.List;

public interface PointOfPresenceServiceV1 extends CrudServiceV1<PointOfPresence, PointOfPresenceDtoV1> {

    Page<PointOfPresence> findByName(String name, Pageable pageable);

    List<PointOfPresence> findAllByOrgId(Long id);
}
