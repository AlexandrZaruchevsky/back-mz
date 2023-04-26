package ru.az.mz.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.az.mz.services.MyException;
import ru.az.mz.services.utils.NetServiceV1;
import ru.az.mz.services.utils.SubnetEquip;

import java.util.List;

@RestController
@RequestMapping("all/subnet")
public class SubnetRestControllerV1 {

    private final NetServiceV1 netServiceV1;

    public SubnetRestControllerV1(NetServiceV1 netServiceV1) {
        this.netServiceV1 = netServiceV1;
    }

    @GetMapping
    public void subnetScanStart(
            @RequestParam(required = false, defaultValue = "") String subnetName
    ) throws MyException {
        netServiceV1.subnetScanStart(subnetName);
    }

    @GetMapping("subnet/run")
    public boolean isSubnetScanRunning(
            @RequestParam(required = false, defaultValue = "") String subnetName
    ) throws MyException {
        return netServiceV1.isNetScanRunning(subnetName);
    }

    @GetMapping("subnet/map")
    public List<SubnetEquip> getMapSubnet(
            @RequestParam String subnet
    ) throws MyException {
        return netServiceV1.getSubnetMap(subnet);
    }

    @GetMapping("subnet/executors")
    public List<String> getListNameExecutors(){
        return netServiceV1.getListNameExecutors();
    }

    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> getException(MyException ex){
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ex.getMessage());
    }

}
