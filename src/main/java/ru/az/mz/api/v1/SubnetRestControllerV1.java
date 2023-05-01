package ru.az.mz.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.dto.v1.SubnetEquipDtoV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.SubnetServiceV1;
import ru.az.mz.services.utils.NetServiceV1;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("all/subnet")
public class SubnetRestControllerV1 {

    private final NetServiceV1 netServiceV1;

    private final SubnetServiceV1 subnetServiceV1;

    public SubnetRestControllerV1(NetServiceV1 netServiceV1, SubnetServiceV1 subnetServiceV1) {
        this.netServiceV1 = netServiceV1;
        this.subnetServiceV1 = subnetServiceV1;
    }

    @GetMapping
    public void subnetScanStart(
            @RequestParam(required = false, defaultValue = "") String subnetName
    ) throws MyException {
        netServiceV1.subnetScanStart(subnetName);
    }

    @GetMapping("run")
    public boolean isSubnetScanRunning(
            @RequestParam(required = false, defaultValue = "") String subnetName
    ) throws MyException {
        return netServiceV1.isNetScanRunning(subnetName);
    }

    @GetMapping("map")
    public List<SubnetEquipDtoV1> getMapSubnet(
            @RequestParam Long id,
            @RequestParam(defaultValue = "active", required = false) String status
    ) throws MyException {
        return "active".equalsIgnoreCase(status)
                ? subnetServiceV1.getSubnetEquipsBySubnetId(id).stream()
                        .filter(SubnetEquipDtoV1::isActive)
                        .collect(Collectors.toList())
                : subnetServiceV1.getSubnetEquipsBySubnetId(id);
    }

    @GetMapping("executors")
    public List<String> getListNameExecutors() {
        return netServiceV1.getListNameExecutors();
    }

    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> getException(MyException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ex.getMessage());
    }

}
