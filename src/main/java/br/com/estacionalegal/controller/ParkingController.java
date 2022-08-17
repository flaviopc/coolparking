package br.com.estacionalegal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estacionalegal.controller.dto.ParkingCreateDTO;
import br.com.estacionalegal.controller.dto.ParkingDTO;
import br.com.estacionalegal.controller.mapper.ParkingMapper;
import br.com.estacionalegal.model.Parking;
import br.com.estacionalegal.service.ParkingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        var parking = parkingService.findById(id);
        var result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
        var parking = parkingService.checkOut(id);
        var result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parkinCreateDTO) {
        var parking = parkingMapper.toParking(parkinCreateDTO);
        var parkingCreated = parkingService.create(parking);
        var result = parkingMapper.parkingDTO(parkingCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO parkinCreateDTO) {
        var parking = parkingMapper.toParking(parkinCreateDTO);
        var parkingCreated = parkingService.update(id, parking);
        var result = parkingMapper.parkingDTO(parkingCreated);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
