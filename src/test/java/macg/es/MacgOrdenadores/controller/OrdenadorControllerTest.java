package macg.es.MacgOrdenadores.controller;

import macg.es.MacgOrdenadores.model.Ordenador;
import macg.es.MacgOrdenadores.service.OrdenadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrdenadorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrdenadorService ordenadorService;

    @InjectMocks
    private OrdenadorController ordenadorController;

    private Ordenador ordenador;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ordenadorController).build();

        // Crear un objeto Ordenador para las pruebas
        ordenador = new Ordenador(1L, "Ordenador 1", "#FFFFFF", 1.5, 104, true);
    }

    @Test
    public void testObtenerTodosLosOrdenadores() throws Exception {
        when(ordenadorService.obtenerTodosLosOrdenadores()).thenReturn(Arrays.asList(ordenador));

        mockMvc.perform(get("/api/ordenadores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ordenador 1"));
    }

    @Test
    public void testObtenerOrdenadorPorId() throws Exception {
        when(ordenadorService.obtenerOrdenadorPorId(1L)).thenReturn(Optional.of(ordenador));

        mockMvc.perform(get("/api/ordenadores/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ordenador 1"));
    }

    @Test
    public void testObtenerOrdenadorPorId_NotFound() throws Exception {
        when(ordenadorService.obtenerOrdenadorPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/ordenadores/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCrearOrdenador() throws Exception {
        when(ordenadorService.agregarOrdenador(any(Ordenador.class))).thenReturn(ordenador);

        mockMvc.perform(post("/api/ordenadores")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Ordenador 1\", \"colorHex\":\"#FFFFFF\", \"peso\":1.5, \"numeroTeclas\":104, \"esIntel\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ordenador 1"));
    }

    @Test
    public void testActualizarOrdenador() throws Exception {
        when(ordenadorService.actualizarOrdenador(eq(1L), any(Ordenador.class))).thenReturn(Optional.of(ordenador));

        mockMvc.perform(put("/api/ordenadores/{id}", 1L)
                        .contentType("application/json")
                        .content("{\"nombre\":\"Ordenador 1\", \"colorHex\":\"#FFFFFF\", \"peso\":1.5, \"numeroTeclas\":104, \"esIntel\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ordenador 1"));
    }

    @Test
    public void testActualizarOrdenador_NotFound() throws Exception {
        when(ordenadorService.actualizarOrdenador(eq(1L), any(Ordenador.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/ordenadores/{id}", 1L)
                        .contentType("application/json")
                        .content("{\"nombre\":\"Ordenador 1\", \"colorHex\":\"#FFFFFF\", \"peso\":1.5, \"numeroTeclas\":104, \"esIntel\":true}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEliminarOrdenador() throws Exception {
        when(ordenadorService.eliminarOrdenador(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/ordenadores/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testEliminarOrdenador_NotFound() throws Exception {
        when(ordenadorService.eliminarOrdenador(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/ordenadores/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testBuscarPorPeso() throws Exception {
        when(ordenadorService.buscarPorPeso(1.5)).thenReturn(Arrays.asList(ordenador));

        mockMvc.perform(get("/api/ordenadores/buscar/peso")
                        .param("peso", "1.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ordenador 1"));
    }

    @Test
    public void testBuscarPorNumeroTeclas() throws Exception {
        when(ordenadorService.buscarPorNumeroTeclas(104)).thenReturn(Arrays.asList(ordenador));

        mockMvc.perform(get("/api/ordenadores/buscar/numeroTeclas")
                        .param("numeroTeclas", "104"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ordenador 1"));
    }

    @Test
    public void testBuscarPorEsIntel() throws Exception {
        when(ordenadorService.buscarPorEsIntel(true)).thenReturn(Arrays.asList(ordenador));

        mockMvc.perform(get("/api/ordenadores/buscar/esIntel")
                        .param("esIntel", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ordenador 1"));
    }
}
