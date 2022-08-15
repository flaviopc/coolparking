package br.com.estacionalegal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estacionalegal.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
