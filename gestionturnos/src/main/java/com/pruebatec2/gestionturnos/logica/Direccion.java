/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatec2.gestionturnos.logica;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Carlos Jaquez
 */
@Entity
public class Direccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String portal;
    private String piso;
    private String puerta;
    private String ciudad;
    private String provincia;
    private String cp;

    @OneToMany(mappedBy = "direccion")
    private List<Ciudadano> ciudadanos;

    public Direccion() {
    }

    public Direccion(String calle, String portal, String piso, String puerta, String ciudad, String provincia, String cp) {
        this.calle = calle;
        this.portal = portal;
        this.piso = piso;
        this.puerta = puerta;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.cp = cp;
    }

    public Direccion(Long id, String calle, String portal, String piso, String puerta, String ciudad, String provincia, String cp, List<Ciudadano> ciudadanos) {
        this.id = id;
        this.calle = calle;
        this.portal = portal;
        this.piso = piso;
        this.puerta = puerta;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.cp = cp;
        this.ciudadanos = ciudadanos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public List<Ciudadano> getCiudadanos() {
        return ciudadanos;
    }

    public void setCiudadanos(List<Ciudadano> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }

    /**
     * Concatena todos los atributos de Direccion para crear un solo String
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(calle)
                .append(", nº")
                .append(portal)
                .append(", ")
                .append(piso)
                .append(puerta)
                .append(", ")
                .append(ciudad)
                .append(", ")
                .append(provincia)
                .append(", ")
                .append(cp);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.calle);
        hash = 41 * hash + Objects.hashCode(this.portal);
        hash = 41 * hash + Objects.hashCode(this.piso);
        hash = 41 * hash + Objects.hashCode(this.puerta);
        hash = 41 * hash + Objects.hashCode(this.ciudad);
        hash = 41 * hash + Objects.hashCode(this.provincia);
        hash = 41 * hash + Objects.hashCode(this.cp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Direccion other = (Direccion) obj;
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.portal, other.portal)) {
            return false;
        }
        if (!Objects.equals(this.piso, other.piso)) {
            return false;
        }
        if (!Objects.equals(this.puerta, other.puerta)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        return Objects.equals(this.cp, other.cp);
    }

}
