package com.tesoramobil.aportacion.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesoramobil.aportacion.entities.AportacionEntity;
import com.tesoramobil.aportacion.repositories.AportacionRepository;
import com.tesoramobil.aportacion.services.AportacionService;

import java.util.List;

@Service
public class AportacionServiceImpl implements AportacionService {

	@Autowired
    AportacionRepository aportacionRepository;


    @Override
    public AportacionEntity guardarAportacion(AportacionEntity aportacion) {
        return aportacionRepository.save(aportacion);
    }

    @Override
    public List<AportacionEntity> listarTodas() {
        return aportacionRepository.findAll();
    }

    @Override
    public AportacionEntity obtenerPorId(Long id) {
        return aportacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aportación no encontrada con id: " + id));
    }

    @Override
    public List<AportacionEntity> obtenerPorCooperacion(Long cooperacionId) {
        return aportacionRepository.findByCooperacionId(cooperacionId);
    }

    @Override
    public List<AportacionEntity> obtenerPorUsuario(Long usuarioId) {
        return aportacionRepository.findByUsuarioId(usuarioId);
    }
}