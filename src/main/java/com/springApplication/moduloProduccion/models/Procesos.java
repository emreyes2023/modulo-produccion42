package com.springApplication.moduloProduccion.models;

public class Procesos {

    private Long codigoProducto;

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    private String nombreProducto;
    private String[] procesos;

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String[] getProcesos() {
        return procesos;
    }

    public void setProcesos(String[] procesos) {
        this.procesos = procesos;
    }
}
