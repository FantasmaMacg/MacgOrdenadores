package macg.es.MacgOrdenadores.repository;

import macg.es.MacgOrdenadores.model.Ordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenadorRepository extends JpaRepository<Ordenador, Long> {
    List<Ordenador> findByPeso(Double peso);
    List<Ordenador> findByNumeroTeclas(Integer numeroTeclas);
    List<Ordenador> findByEsIntel(Boolean esIntel);
    List<Ordenador> findByNombreContainingIgnoreCase(String nombre);
}