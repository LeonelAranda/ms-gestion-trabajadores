package cl.capstone.ms_gestion_trabajadores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.IAlojamientoTransporteRepository;
import cl.capstone.ms_gestion_trabajadores.model.AlojamientoTransporte;

@Service
public class AlojamientoTransporteService implements IAlojamientoTransporteService {

    @Autowired
    IAlojamientoTransporteRepository AlojamientoTransporteRepository;

    @Override
    public AlojamientoTransporte saveAlojamientoTransporte(AlojamientoTransporte alojamientoTransporte) {

        AlojamientoTransporteRepository.save(alojamientoTransporte);

        return alojamientoTransporte;

    }
}
