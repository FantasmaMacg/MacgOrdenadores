package macg.es.MacgOrdenadores.service;

import macg.es.MacgOrdenadores.model.Ordenador;
import macg.es.MacgOrdenadores.repository.OrdenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenadorService {

    private final OrdenadorRepository ordenadorRepository;

    @Autowired
    public OrdenadorService(OrdenadorRepository ordenadorRepository) {
        this.ordenadorRepository = ordenadorRepository;
    }

    public List<Ordenador> obtenerTodosLosOrdenadores() {
        return ordenadorRepository.findAll();
    }

    public Optional<Ordenador> obtenerOrdenadorPorId(Long id) {
        return ordenadorRepository.findById(id);
    }

    public Ordenador agregarOrdenador(Ordenador ordenador) {
        return ordenadorRepository.save(ordenador);
    }

    public Optional<Ordenador> actualizarOrdenador(Long id, Ordenador detallesOrdenador) {
        return ordenadorRepository.findById(id).map(ordenador -> {
            ordenador.setNombre(detallesOrdenador.getNombre());
            ordenador.setColorHex(detallesOrdenador.getColorHex());
            ordenador.setPeso(detallesOrdenador.getPeso());
            ordenador.setNumeroTeclas(detallesOrdenador.getNumeroTeclas());
            ordenador.setEsIntel(detallesOrdenador.getEsIntel());
            return ordenadorRepository.save(ordenador);
        });
    }

    public boolean eliminarOrdenador(Long id) {
        return ordenadorRepository.findById(id).map(ordenador -> {
            ordenadorRepository.delete(ordenador);
            return true;
        }).orElse(false);
    }

    public List<Ordenador> buscarPorPeso(Double peso) {
        return ordenadorRepository.findByPeso(peso);
    }

    public List<Ordenador> buscarPorNumeroTeclas(Integer numeroTeclas) {
        return ordenadorRepository.findByNumeroTeclas(numeroTeclas);
    }

    public List<Ordenador> buscarPorEsIntel(Boolean esIntel) {
        return ordenadorRepository.findByEsIntel(esIntel);
    }

    public List<Ordenador> buscarPorNombre(String nombre) {
        return ordenadorRepository.findByNombreContainingIgnoreCase(nombre);
    }
}