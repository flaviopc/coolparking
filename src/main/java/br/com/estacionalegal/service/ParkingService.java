package br.com.estacionalegal.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        return parkingMap.get(id);
    }

    public Parking create(Parking parking) {
        String uuid = getUUID();
        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parking);
        return parking;
    }
}
