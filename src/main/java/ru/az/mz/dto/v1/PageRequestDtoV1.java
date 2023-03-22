package ru.az.mz.dto.v1;

import lombok.Value;

@Value
public class PageRequestDtoV1 {

    int pageCurrent;
    int pageSize;
    String sortBy;
    String search;
    long parentId;

}
