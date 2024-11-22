package cl.capstone.ms_gestion_trabajadores.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;

@Repository
public interface ITrabajadorFaenaRepository extends JpaRepository<TrabajadorFaena, Long> {

}
