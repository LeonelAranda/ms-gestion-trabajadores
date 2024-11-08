package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.model.Cargo;
import cl.capstone.ms_gestion_trabajadores.model.TipoCumplimiento;

@Service
public interface ITipoCumplimientoService {

    public List<TipoCumplimiento> getTiposCumplimientos();

    public TipoCumplimiento saveTipoCumplimiento(TipoCumplimiento tipoCumplimiento);

    public void deleteTipoCumplimiento(Long id);

    public Cargo findTipoCumplimiento(Long id);

    public void editTipoCumplimiento(TipoCumplimiento tipoCumplimiento);
}
