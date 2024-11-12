package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.ICargoRepository;
import cl.capstone.ms_gestion_trabajadores.model.Cargo;

@Service
public class CargoService implements ICargoService {

    @Autowired
    ICargoRepository cargoRepository;

    @Override
    public List<Cargo> getCargos() {

        List<Cargo> listaCargos = cargoRepository.findAll();
        return listaCargos;
    }

    @Override
    public Cargo saveCargo(Cargo cargo) {

        cargoRepository.save(cargo);

        return cargo;

    }

    @Override
    public void deleteCargo(Long id) {

        cargoRepository.deleteById(id);

    }

    @Override
    public Cargo findCargo(Long id) {

        Cargo cargo = cargoRepository.findById(id).orElse(null);
        return cargo;
    }

    @Override
    public void editCargo(Long id, String nuevoCargo, String nuevaDescripcion) {

        Cargo cargo = this.findCargo(id);
        cargo.setNombre(nuevoCargo);
        cargo.setDescripcion(nuevaDescripcion);

        this.saveCargo(cargo);
    }

}
