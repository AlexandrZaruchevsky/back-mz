package ru.az.mz.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.az.mz.services.EquipNetServiceV1;
import ru.az.mz.services.SubnetMapServiceV1;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("all/equip-net")
public class EquipNetRestControllerV1 {

    private final EquipNetServiceV1 equipNetServiceV1;
    private final SubnetMapServiceV1 subnetMapServiceV1;

    public EquipNetRestControllerV1(
            EquipNetServiceV1 equipNetServiceV1,
            SubnetMapServiceV1 subnetMapServiceV1
    ) {
        this.equipNetServiceV1 = equipNetServiceV1;
        this.subnetMapServiceV1 = subnetMapServiceV1;
    }

    @GetMapping("subnet-list")
    public ResponseEntity<?> findAllSubnet(
            @RequestParam(defaultValue = "all", required = false) String status
    ) {
        if ("".equalsIgnoreCase(status.trim()) || "all".equalsIgnoreCase(status.trim())) {
            return ResponseEntity.ok(subnetMapServiceV1.findAllByStatus());
        } else {
            return ResponseEntity.ok(
                    subnetMapServiceV1.findAllByStatus().stream()
                            .filter(subnetMapProjectionV1 -> status.trim().equalsIgnoreCase(subnetMapProjectionV1.getStatus()))
            );
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllBySubnetId(
            @RequestParam(defaultValue = "-1", required = false) Long subnet
    ) {
        if (subnet == -1) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("incorrect subnet");
        }
        return ResponseEntity
                .ok(equipNetServiceV1.getEquipNetList(subnet));
    }

}
