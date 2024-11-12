package cl.capstone.ms_gestion_trabajadores.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.capstone.ms_gestion_trabajadores.model.Trabajador;

@Repository
public interface ITrabajadorRepository extends JpaRepository<Trabajador, Long> {

    public Trabajador findByPrimerNombre(String nombre);

    public String findByPrimerApellido(String apellido);

    public Trabajador findByComunaAndPrimerApellido(String comuna, String apellido);

    public Trabajador findByRun(String run);

    @Query(value = "SELECT c.*, o.*, p.* "
            + " from Customer c, CustomerOrder o ,Product p "
            + " where c.id=o.customer_id "
            + " and o.id=p.customerOrder_id "
            + " and c.id=?1 ", nativeQuery = true)
    List<Map<String, Object>> findByCustomer(Long id);
}
