package ru.az.mz.services.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SubnetScanTask implements Callable<List<SubnetEquip>> {

    @Getter
    private final String subnet;
    @Getter
    private final AtomicBoolean subnetScanRunning = new AtomicBoolean(false);

    public SubnetScanTask(String subnet) {
        this.subnet = subnet;
    }

    @Override
    public List<SubnetEquip> call() throws Exception {
        log.info("Start Task");
        subnetScanRunning.set(true);
        List<SubnetEquip> subnetEquipList = IntStream.range(1, 256)
                .parallel()
                .mapToObj(value -> subnet + value)
                .map(this::ping)
                .collect(Collectors.toList());
        subnetScanRunning.set(false);
        return subnetEquipList;
    }

    private SubnetEquip ping(String domainNameOrIpAddress) {
        if (domainNameOrIpAddress == null)
            return new SubnetEquip();
        long t0 = System.currentTimeMillis();
        try {
            InetAddress inetAddress = InetAddress.getByName(domainNameOrIpAddress);
            if (inetAddress.isReachable(100)) {
                log.info("PING {},  time {}", domainNameOrIpAddress, System.currentTimeMillis() - t0);
                return new SubnetEquip(
                        inetAddress.getHostAddress(),
                        inetAddress.getHostName(),
                        inetAddress.getCanonicalHostName(),
                        true,
                        System.currentTimeMillis() - t0,
                        LocalDateTime.now());
            }
            return new SubnetEquip(domainNameOrIpAddress);
        } catch (IOException e) {
            log.info("PING {}, isn't active  time {}", domainNameOrIpAddress, System.currentTimeMillis() - t0);
            return new SubnetEquip(domainNameOrIpAddress);
        }
    }

}
