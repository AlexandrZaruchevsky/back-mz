package ru.az.mz.services.utils.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.az.mz.services.MyException;
import ru.az.mz.services.SubnetServiceV1;
import ru.az.mz.services.utils.NetServiceV1;
import ru.az.mz.services.utils.SubnetEquip;
import ru.az.mz.services.utils.SubnetScanTask;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

@Service
@Primary
@Slf4j
public class NetServiceV1Impl implements NetServiceV1 {

    private final Map<String, Boolean> mapSubnet = new ConcurrentHashMap<>();
    private final Map<String, Future<List<SubnetEquip>>> tasks = new ConcurrentHashMap<>();
    private final Map<String, ExecutorService> services = new ConcurrentHashMap<>();
    private final SubnetServiceV1 subnetServiceV1;

    public NetServiceV1Impl(SubnetServiceV1 subnetServiceV1) {
        this.subnetServiceV1 = subnetServiceV1;
    }

    @Override
    public boolean isEquipActive(String equipName) {
        return false;
    }

    @Override
    public synchronized boolean isNetScanRunning(String subnet) throws MyException {
        return tasks.get(formatSubnet(subnet)) != null && !tasks.get(formatSubnet(subnet)).isDone();
    }

    @Override
    public void subnetScanStart(String subnet) throws MyException {
        if (isNetScanRunning(formatSubnet(subnet))) return;
        mapSubnet.put(formatSubnet(subnet), true);
        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>());
        log.info("Start subnet [{}] scan", formatSubnet(subnet));
        SubnetScanTask task = new SubnetScanTask(formatSubnet(subnet));
        services.put(formatSubnet(subnet), executorService);
        tasks.put(formatSubnet(subnet), executorService.submit(task));
    }

    @Scheduled(fixedRate = 5000)
    private synchronized void shutdownExecutors() {
//        log.info("Task is done");
        services.keySet()
                .forEach(s -> {
                    if (tasks.get(s).isDone()) {
                        ExecutorService executorService = services.get(s);
                        log.info("Executors {} shutdown", s);
                        executorService.shutdown();
                        // save to base
                        Future<List<SubnetEquip>> listFuture = tasks.get(s);
                        if(listFuture.isDone()){
                            try {
                                subnetServiceV1.add(s,listFuture.get());
                            } catch (JAXBException | InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                        //
                        if (executorService.isShutdown()) {
                            services.remove(s);
                        }
                    }
                });
    }

    @Override
    public List<String> subnetScan() {
        return new ArrayList<>(tasks.keySet());
    }

    @Override
    public List<SubnetEquip> getSubnetMap(String subnet) throws MyException {
        List<SubnetEquip> ff = new ArrayList<>();
        Future<List<SubnetEquip>> future = tasks.get(formatSubnet(subnet));
        if (future != null && future.isDone() && !future.isCancelled()) {
            try {
                ff.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return ff;
    }

    @Override
    public List<String> getListNameExecutors() {
        return new ArrayList<>(services.keySet());
    }

    private synchronized boolean isSubnetScanRunning(String subnetName) {
        Boolean aBoolean = mapSubnet.get(subnetName);
        return aBoolean != null
                ? aBoolean
                : false;
    }

    private boolean subnetValidate(String subnet) {
        Pattern pattern = Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.)){3}$");
        return pattern.matcher(subnet).find();
    }

    private String formatSubnet(@NotNull String subnet) throws MyException {
        String s = subnet.replaceAll("\\s+", "");
        s = s.endsWith(".")
                ? subnet.replaceAll("\\s+", "")
                : subnet.replaceAll("\\s+", "") + ".";
        if (!subnetValidate(s)) throw new MyException("Subnet invalidate", HttpStatus.BAD_REQUEST);
        return s;
    }

}
