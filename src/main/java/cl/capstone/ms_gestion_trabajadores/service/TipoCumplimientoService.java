package cl.capstone.ms_gestion_trabajadores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.capstone.ms_gestion_trabajadores.Repository.ITipoCumplimientoRepository;
import cl.capstone.ms_gestion_trabajadores.model.Cargo;
import cl.capstone.ms_gestion_trabajadores.model.TipoCumplimiento;

@Service
public class TipoCumplimientoService implements ITipoCumplimientoService {

    @Autowired
    ITipoCumplimientoRepository iTipoCumplimientoRepository;

    @Override
    public List<TipoCumplimiento> getTiposCumplimientos() {
        List<TipoCumplimiento> listaTipoCumplimiento = iTipoCumplimientoRepository.findAll();
        return listaTipoCumplimiento;
    }

    @Override
    public TipoCumplimiento saveTipoCumplimiento(TipoCumplimiento tipoCumplimiento) {

        return iTipoCumplimientoRepository.save(tipoCumplimiento);
    }

    @Override
    public void deleteTipoCumplimiento(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTipoCumplimiento'");
    }

    @Override
    public Cargo findTipoCumplimiento(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findTipoCumplimiento'");
    }

    @Override
    public void editTipoCumplimiento(TipoCumplimiento tipoCumplimiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editTipoCumplimiento'");
    }

}
