package cl.capstone.ms_gestion_trabajadores.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.capstone.ms_gestion_trabajadores.model.TrabajadorFaena;

@Repository
public interface IRegistroRepository extends JpaRepository<TrabajadorFaena, Long> {

    Optional<TrabajadorFaena> findByTrabajadorRunAndIdFaena(String run, Long idFaena);

}
