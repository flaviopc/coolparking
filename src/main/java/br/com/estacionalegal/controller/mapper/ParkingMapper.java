package br.com.estacionalegal.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.estacionalegal.controller.dto.ParkingDTO;
import br.com.estacionalegal.model.Parking;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkings) {
        return parkings.stream().map(this::parkingDTO).collect(Collectors.toList());
    }

    public ParkingDTO parkingDTO(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

}
