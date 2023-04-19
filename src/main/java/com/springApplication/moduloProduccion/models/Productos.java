package com.springApplication.moduloProduccion.models;

public class Productos {

    private Long idProducto;
    private String nombreProducto;
    private String codigoProducto;
    private String[] listaMateriales;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String[] getListaMateriales() {
        return listaMateriales;
    }

    public void setListaMateriales(String[] listaMateriales) {
        this.listaMateriales = listaMateriales;
    }
}
