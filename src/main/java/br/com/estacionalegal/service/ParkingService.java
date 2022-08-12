package br.com.estacionalegal.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.estacionalegal.exception.ParkingNotFoundException;
import br.com.estacionalegal.model.Parking;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap();
    static {
        var id = getUUID();
        var parking = new Parking(id, "license", "SE", "Fiat-Toro", "Branco");
        parkingMap.put(id, parking);
        id = getUUID();
        parking = new Parking(id, "license", "SE", "Gol-Volkswagem", "preto");
        parkingMap.put(id, parking);
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        var parking = parkingMap.get(id);
        if (parking == null)
            throw new ParkingNotFoundException(id);
        return parking;
    }

    public Parking create(Parking parking) {
        String uuid = getUUID();
        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parking);
        return parking;
    }

    public Parking update(String id, Parking parking) {
        Parking parkingUpdate = findById(id);
        parkingUpdate.setColor(parking.getColor());
        parkingUpdate.setState(parking.getState());
        parkingUpdate.setLicense(parking.getLicense());
        parkingMap.replace(id, parkingUpdate);
        return parkingUpdate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

}
