package macg.es.MacgOrdenadores.service;

import macg.es.MacgOrdenadores.model.Ordenador;
import macg.es.MacgOrdenadores.repository.OrdenadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrdenadorServiceTest {

    @Mock
    private OrdenadorRepository ordenadorRepository;

    @InjectMocks
    private OrdenadorService ordenadorService;

    private Ordenador ordenador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ordenador = new Ordenador(1L, "Ordenador Test", "#FFFFFF", 1.5, 105, true);
    }

    @Test
    public void testObtenerTodosLosOrdenadores() {
        when(ordenadorRepository.findAll()).thenReturn(Arrays.asList(ordenador));

        List<Ordenador> ordenadores = ordenadorService.obtenerTodosLosOrdenadores();

        assertNotNull(ordenadores);
        assertEquals(1, ordenadores.size());
        assertEquals(ordenador.getNombre(), ordenadores.get(0).getNombre());

        verify(ordenadorRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerOrdenadorPorId() {
        when(ordenadorRepository.findById(1L)).thenReturn(Optional.of(ordenador));

        Optional<Ordenador> result = ordenadorService.obtenerOrdenadorPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(ordenador.getNombre(), result.get().getNombre());

        verify(ordenadorRepository, times(1)).findById(1L);
    }

    @Test
    public void testAgregarOrdenador() {
        when(ordenadorRepository.save(ordenador)).thenReturn(ordenador);

        Ordenador result = ordenadorService.agregarOrdenador(ordenador);

        assertNotNull(result);
        assertEquals(ordenador.getNombre(), result.getNombre());

        verify(ordenadorRepository, times(1)).save(ordenador);
    }

    @Test
    public void testActualizarOrdenador() {
        when(ordenadorRepository.findById(1L)).thenReturn(Optional.of(ordenador));
        when(ordenadorRepository.save(ordenador)).thenReturn(ordenador);

        Optional<Ordenador> result = ordenadorService.actualizarOrdenador(1L, ordenador);

        assertTrue(result.isPresent());
        assertEquals(ordenador.getNombre(), result.get().getNombre());

        verify(ordenadorRepository, times(1)).findById(1L);
        verify(ordenadorRepository, times(1)).save(ordenador);
    }

    @Test
    public void testEliminarOrdenador() {
        when(ordenadorRepository.findById(1L)).thenReturn(Optional.of(ordenador));

        boolean result = ordenadorService.eliminarOrdenador(1L);

        assertTrue(result);

        verify(ordenadorRepository, times(1)).findById(1L);
        verify(ordenadorRepository, times(1)).delete(ordenador);
    }

    @Test
    public void testBuscarPorPeso() {
        when(ordenadorRepository.findByPeso(1.5)).thenReturn(Arrays.asList(ordenador));

        List<Ordenador> result = ordenadorService.buscarPorPeso(1.5);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ordenador.getPeso(), result.get(0).getPeso());

        verify(ordenadorRepository, times(1)).findByPeso(1.5);
    }

    @Test
    public void testBuscarPorNumeroTeclas() {
        when(ordenadorRepository.findByNumeroTeclas(105)).thenReturn(Arrays.asList(ordenador));

        List<Ordenador> result = ordenadorService.buscarPorNumeroTeclas(105);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ordenador.getNumeroTeclas(), result.get(0).getNumeroTeclas());

        verify(ordenadorRepository, times(1)).findByNumeroTeclas(105);
    }

    @Test
    public void testBuscarPorEsIntel() {
        when(ordenadorRepository.findByEsIntel(true)).thenReturn(Arrays.asList(ordenador));

        List<Ordenador> result = ordenadorService.buscarPorEsIntel(true);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ordenador.getEsIntel(), result.get(0).getEsIntel());

        verify(ordenadorRepository, times(1)).findByEsIntel(true);
    }

    @Test
    public void testBuscarPorNombre() {
        when(ordenadorRepository.findByNombreContainingIgnoreCase("Test")).thenReturn(Arrays.asList(ordenador));

        List<Ordenador> result = ordenadorService.buscarPorNombre("Test");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ordenador.getNombre(), result.get(0).getNombre());

        verify(ordenadorRepository, times(1)).findByNombreContainingIgnoreCase("Test");
    }
}
