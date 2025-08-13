package com.tesoramobil.aportacion.conrollers;
import com.tesoramobil.aportacion.entities.AportacionEntity;
import com.tesoramobil.aportacion.models.ApiResponse;
import com.tesoramobil.aportacion.services.AportacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aportaciones")
@Tag(name = "Aportaciones", description = "Operaciones relacionadas con aportaciones de los usuarios a las cooperaciones")
public class AportacionController {

    @Autowired
    private AportacionService aportacionService;

    @Operation(summary = "Crear una nueva aportación")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aportación creada exitosamente")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<AportacionEntity>> crear(@RequestBody AportacionEntity aportacion) {
        AportacionEntity creada = aportacionService.guardarAportacion(aportacion);
        return ResponseEntity.ok(new ApiResponse<>("OK", "Aportación creada exitosamente", creada));
    }

    @Operation(summary = "Obtener todas las aportaciones")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Listado de aportaciones")
    })
    @GetMapping("getAll")
    public ResponseEntity<ApiResponse<List<AportacionEntity>>> obtenerTodas() {
        List<AportacionEntity> lista = aportacionService.listarTodas();
        return ResponseEntity.ok(new ApiResponse<>("OK", "Listado de aportaciones", lista));
    }

    @Operation(summary = "Obtener una aportación por su ID")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aportación encontrada"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Aportación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AportacionEntity>> obtenerPorId(@PathVariable Long id) {
        AportacionEntity encontrada = aportacionService.obtenerPorId(id);
        if (encontrada != null) {
            return ResponseEntity.ok(new ApiResponse<>("OK", "Aportación encontrada", encontrada));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("NOT_FOUND", "Aportación no encontrada", null));
        }
    }

    @Operation(summary = "Obtener aportaciones por ID de cooperación")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aportaciones por cooperación encontradas")
    })
    @GetMapping("/cooperacion/{cooperacionId}")
    public ResponseEntity<ApiResponse<List<AportacionEntity>>> porCooperacion(@PathVariable Long cooperacionId) {
        List<AportacionEntity> lista = aportacionService.obtenerPorCooperacion(cooperacionId);
        return ResponseEntity.ok(new ApiResponse<>("OK", "Aportaciones por cooperación encontradas", lista));
    }

    @Operation(summary = "Obtener aportaciones por ID de usuario")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Aportaciones por usuario encontradas")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<List<AportacionEntity>>> porUsuario(@PathVariable Long usuarioId) {
        List<AportacionEntity> lista = aportacionService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(new ApiResponse<>("OK", "Aportaciones por usuario encontradas", lista));
    }
}
