package cl.capstone.ms_gestion_trabajadores.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.capstone.ms_gestion_trabajadores.dto.RegistroAprobadosDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroDTO;
import cl.capstone.ms_gestion_trabajadores.dto.RegistroFiltrosDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class RegistroRepository {

    @Autowired
    private EntityManager entityManager;

    public List<RegistroDTO> obtenerRegistros(RegistroFiltrosDTO filtro) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("OBTENER_REGISTROS");

        // Registrar parámetros
        query.registerStoredProcedureParameter("TIPO_CUMPLIMIENTO", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("CARGO", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("RUT", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("FAENA", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        // Establecer valores para los parámetros dinámicos
        query.setParameter("TIPO_CUMPLIMIENTO", filtro.getTipoCumplimiento());
        query.setParameter("CARGO", filtro.getCargo());
        query.setParameter("RUT", filtro.getRut());
        query.setParameter("FAENA", filtro.getFaena());

        // Ejecutar el SP
        query.execute();

        // Mapear resultados a DTO
        List<Object[]> resultados = query.getResultList();
        return resultados.stream().map(obj -> new RegistroDTO(
                obj[0] instanceof BigDecimal ? ((BigDecimal) obj[0]).longValue() : ((Long) obj[0]), // ID_FAENA
                (String) obj[1], // NOMBRE_FAENA
                (String) obj[2], // PRIMER_NOMBRE
                (String) obj[3], // PRIMER_APELLIDO
                (String) obj[4], // EMAIL
                (String) obj[5], // NOMBRE_CARGO
                (String) obj[6], // NOMBRE_CUMPLIMIENTO
                (String) obj[7] // NOMBRE_CUMPLIMIENTO

        )).collect(Collectors.toList());

    }

    public List<RegistroAprobadosDTO> obtenerRegistrosAprobados(RegistroFiltrosDTO filtro, int idFaena) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("OBTENER_REGISTROS_APROBADOS");

        // Registrar parámetros
        query.registerStoredProcedureParameter("TIPO_CUMPLIMIENTO", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("CARGO", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("RUT", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("FAENA", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_resultado", void.class, ParameterMode.REF_CURSOR);

        // Establecer valores para los parámetros dinámicos
        query.setParameter("TIPO_CUMPLIMIENTO", filtro.getTipoCumplimiento());
        query.setParameter("CARGO", filtro.getCargo());
        query.setParameter("RUT", filtro.getRut());
        query.setParameter("FAENA", idFaena);

        // Ejecutar el SP
        query.execute();

        // Mapear resultados a DTO
        List<Object[]> resultados = query.getResultList();
        return resultados.stream().map(obj -> new RegistroAprobadosDTO(
                obj[0] instanceof BigDecimal ? ((BigDecimal) obj[0]).longValue() : ((Long) obj[0]), // ID_FAENA
                (String) obj[1], // NOMBRE_FAENA
                (String) obj[2], // PRIMER_NOMBRE
                (String) obj[3], // PRIMER_APELLIDO
                (String) obj[4], // EMAIL
                (String) obj[5], // NOMBRE_CARGO
                (String) obj[6] // NOMBRE_CUMPLIMIENTO

        )).collect(Collectors.toList());

    }
}
