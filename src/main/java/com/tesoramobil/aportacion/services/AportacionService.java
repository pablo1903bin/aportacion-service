package com.tesoramobil.aportacion.services;

import java.util.List;

import com.tesoramobil.aportacion.entities.AportacionEntity;

public interface AportacionService {
	
    AportacionEntity guardarAportacion(AportacionEntity aportacion);
    List<AportacionEntity> listarTodas();
    AportacionEntity obtenerPorId(Long id);
    List<AportacionEntity> obtenerPorCooperacion(Long cooperacionId);
    List<AportacionEntity> obtenerPorUsuario(Long usuarioId);
    
}