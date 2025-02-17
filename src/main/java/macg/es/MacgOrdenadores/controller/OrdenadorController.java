package macg.es.MacgOrdenadores.controller;

import macg.es.MacgOrdenadores.model.Ordenador;
import macg.es.MacgOrdenadores.service.OrdenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenadores")
public class OrdenadorController {

    @Autowired
    private OrdenadorService ordenadorService;

    @GetMapping
    public List<Ordenador> obtenerTodosLosOrdenadores() {
        return ordenadorService.obtenerTodosLosOrdenadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordenador> obtenerOrdenadorPorId(@PathVariable Long id) {
        return ordenadorService.obtenerOrdenadorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ordenador> crearOrdenador(@Validated @RequestBody Ordenador ordenador) {
        return ResponseEntity.ok(ordenadorService.agregarOrdenador(ordenador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ordenador> actualizarOrdenador(@PathVariable Long id, @Validated @RequestBody Ordenador ordenador) {
        return ordenadorService.actualizarOrdenador(id, ordenador)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrdenador(@PathVariable Long id) {
        if (ordenadorService.eliminarOrdenador(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/peso")
    public List<Ordenador> buscarPorPeso(@RequestParam Double peso) {
        return ordenadorService.buscarPorPeso(peso);
    }

    @GetMapping("/buscar/numeroTeclas")
    public List<Ordenador> buscarPorNumeroTeclas(@RequestParam Integer numeroTeclas) {
        return ordenadorService.buscarPorNumeroTeclas(numeroTeclas);
    }

    @GetMapping("/buscar/esIntel")
    public List<Ordenador> buscarPorEsIntel(@RequestParam Boolean esIntel) {
        return ordenadorService.buscarPorEsIntel(esIntel);
    }
}