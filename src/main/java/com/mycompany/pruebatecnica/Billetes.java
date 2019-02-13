/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebatecnica;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author angie
 */
@Entity
@Table(name = "billetes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billetes.findAll", query = "SELECT b FROM Billetes b")
    , @NamedQuery(name = "Billetes.findById", query = "SELECT b FROM Billetes b WHERE b.id = :id")
    , @NamedQuery(name = "Billetes.findByDenominacion", query = "SELECT b FROM Billetes b WHERE b.denominacion = :denominacion")
    , @NamedQuery(name = "Billetes.findByCantidad", query = "SELECT b FROM Billetes b WHERE b.cantidad = :cantidad")})
public class Billetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "denominacion")
    private BigInteger denominacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigInteger cantidad;

    public Billetes() {
    }

    public Billetes(Integer id) {
        this.id = id;
    }

    public Billetes(Integer id, BigInteger denominacion, BigInteger cantidad) {
        this.id = id;
        this.denominacion = denominacion;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(BigInteger denominacion) {
        this.denominacion = denominacion;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billetes)) {
            return false;
        }
        Billetes other = (Billetes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebatecnica.Billetes[ id=" + id + " ]";
    }
    
}
