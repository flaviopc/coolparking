package br.com.estacionalegal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.estacionalegal.exception.ParkingNotFoundException;
import br.com.estacionalegal.model.Parking;
import br.com.estacionalegal.repository.ParkingRepository;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parking) {
        String uuid = getUUID();
        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking update(String id, Parking parking) {
        Parking parkingUpdate = findById(id);
        parkingUpdate.setColor(parking.getColor());
        parkingUpdate.setState(parking.getState());
        parkingUpdate.setLicense(parking.getLicense());
        parkingRepository.save(parkingUpdate);
        return parkingUpdate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking checkOut(String id) {
        var parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }

}
