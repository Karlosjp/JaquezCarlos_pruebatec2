package com.pruebatec2.gestionturnos.logica;

import com.pruebatec2.gestionturnos.utilidades.Estado;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Carlos Jaquez
 */
@Entity
public class Turno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private LocalDate fecha;
    private Estado estado;

    @OneToOne(mappedBy = "turno")
    private Tramite tramite;

    @ManyToOne
    @JoinColumn(name = "ciudadano_id")
    private Ciudadano ciudadano;

    public Turno() {
    }

    public Turno(String numero, LocalDate fecha, Estado estado, Tramite tramite) {
        this.numero = numero;
        this.fecha = fecha;
        this.estado = estado;
        this.tramite = tramite;
    }

    public Turno(Long id, String numero, LocalDate fecha, Estado estado, Tramite tramite, Ciudadano ciudadano) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.estado = estado;
        this.tramite = tramite;
        this.ciudadano = ciudadano;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    @Override
    public String toString() {
        return "Turno{" + "numero=" + numero + ", fecha=" + fecha + ", estado=" + estado + ", tramite=" + tramite + '}';
    }

}
