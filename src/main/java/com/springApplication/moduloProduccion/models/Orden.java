package com.springApplication.moduloProduccion.models;

import java.util.Date;

public class Orden {

    private Long numeroProduccion;
    private String estado;
    private String cliente;
    private String usuario;
    private Date fechaInicio;
    private Date fechaFinal;
    private String[] detalle;

    public Long getNumeroProduccion() {
        return numeroProduccion;
    }

    public void setNumeroProduccion(Long numeroProduccion) {
        this.numeroProduccion = numeroProduccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String[] getDetalle() {
        return detalle;
    }

    public void setDetalle(String[] detalle) {
        this.detalle = detalle;
    }
}
