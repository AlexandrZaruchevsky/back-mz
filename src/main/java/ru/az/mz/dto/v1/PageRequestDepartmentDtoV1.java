package ru.az.mz.dto.v1;

import lombok.Value;

@Value
public class PageRequestDepartmentDtoV1 {

    int pageCurrent;
    int pageSize;
    String sortBy;
    String search;
    int parentId;

}
