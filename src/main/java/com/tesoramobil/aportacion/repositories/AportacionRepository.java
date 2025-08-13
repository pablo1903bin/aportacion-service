package com.tesoramobil.aportacion.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tesoramobil.aportacion.entities.AportacionEntity;
import java.util.List;



public interface AportacionRepository extends JpaRepository<AportacionEntity, Long> {
	
    List<AportacionEntity> findByCooperacionId(Long cooperacionId);
    List<AportacionEntity> findByUsuarioId(Long usuarioId);
    
}