package ru.az.mz.cl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.az.mz.services.AppInitService;

@Component
public class InitCL implements CommandLineRunner {

    private final AppInitService appInitService;

    public InitCL(AppInitService appInitService) {
        this.appInitService = appInitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (appInitService.count()==0){
            appInitService.init();
        }

    }

}
