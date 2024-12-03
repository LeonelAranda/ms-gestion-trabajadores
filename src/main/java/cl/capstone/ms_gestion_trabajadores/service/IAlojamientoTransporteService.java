package cl.capstone.ms_gestion_trabajadores.service;

import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.model.AlojamientoTransporte;

@Service
public interface IAlojamientoTransporteService {
    public AlojamientoTransporte saveAlojamientoTransporte(AlojamientoTransporte alojamientoTransporte);

}
