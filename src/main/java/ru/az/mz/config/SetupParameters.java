package ru.az.mz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class SetupParameters {

    @Value("${setup.page.current}")
    private int pageCurrent;
    @Value("${setup.page.size}")
    private int pageSize;

    public PageRequest getPageRequestDefault() {
        return PageRequest.of(pageCurrent, pageSize);
    }

}
