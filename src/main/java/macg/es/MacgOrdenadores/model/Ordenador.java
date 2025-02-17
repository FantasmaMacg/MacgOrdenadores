package macg.es.MacgOrdenadores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Ordenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El color no puede estar vacío")
    private String colorHex;

    @NotNull(message = "El peso no puede ser nulo")
    @Positive(message = "El peso debe ser positivo")
    private Double peso;

    @NotNull(message = "El número de teclas no puede ser nulo")
    @Positive(message = "El número de teclas debe ser positivo")
    private Integer numeroTeclas;

    @NotNull(message = "El campo esIntel no puede ser nulo")
    private Boolean esIntel;

    // Constructor sin parámetros
    public Ordenador() {
    }

    // Constructor con parámetros
    public Ordenador(Long id, String nombre, String colorHex, Double peso, Integer numeroTeclas, Boolean esIntel) {
        this.id = id;
        this.nombre = nombre;
        this.colorHex = colorHex;
        this.peso = peso;
        this.numeroTeclas = numeroTeclas;
        this.esIntel = esIntel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getNumeroTeclas() {
        return numeroTeclas;
    }

    public void setNumeroTeclas(Integer numeroTeclas) {
        this.numeroTeclas = numeroTeclas;
    }

    public Boolean getEsIntel() {
        return esIntel;
    }

    public void setEsIntel(Boolean esIntel) {
        this.esIntel = esIntel;
    }
}
