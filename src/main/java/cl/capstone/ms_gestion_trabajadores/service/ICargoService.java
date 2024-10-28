package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.model.Cargo;

@Service
public interface ICargoService {

    public List<Cargo> getCargos();

    public void saveCargo(Cargo cargo);

    public void deleteCargo(Long id);

    public Cargo findCargo(Long id);

    public void editCargo(Long id, String nuevoCargo, String nuevaDescripcion);

}
