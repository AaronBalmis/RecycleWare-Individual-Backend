package com.proyecto.daw.model;

import java.util.List;
import com.proyecto.daw.model.Producto;
import com.proyecto.daw.model.Donation;
import java.util.Map;

public class DashboardAdminDTO {
    private long totalProductos;
    private long equiposOperativos;
    private long donacionesPendientes;
    private long solicitudesPendientes;
    private List<Producto> ultimosProductos;
    private List<Donation> ultimasDonaciones;
    private Map<String, Long> productosPorCategoria;

    // Constructor vacío
    public DashboardAdminDTO() {
    }

    // Constructor con parámetros
    public DashboardAdminDTO(long totalProductos, long equiposOperativos,
            long donacionesPendientes, long solicitudesPendientes,
            List<Producto> ultimosProductos, List<Donation> ultimasDonaciones,
            Map<String, Long> productosPorCategoria) {
        this.totalProductos = totalProductos;
        this.equiposOperativos = equiposOperativos;
        this.donacionesPendientes = donacionesPendientes;
        this.solicitudesPendientes = solicitudesPendientes;
        this.ultimosProductos = ultimosProductos;
        this.ultimasDonaciones = ultimasDonaciones;
        this.productosPorCategoria = productosPorCategoria;
    }

    // Getters y Setters
    public long getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(long totalProductos) {
        this.totalProductos = totalProductos;
    }

    public long getEquiposOperativos() {
        return equiposOperativos;
    }

    public void setEquiposOperativos(long equiposOperativos) {
        this.equiposOperativos = equiposOperativos;
    }

    public long getDonacionesPendientes() {
        return donacionesPendientes;
    }

    public void setDonacionesPendientes(long donacionesPendientes) {
        this.donacionesPendientes = donacionesPendientes;
    }

    public long getSolicitudesPendientes() {
        return solicitudesPendientes;
    }

    public void setSolicitudesPendientes(long solicitudesPendientes) {
        this.solicitudesPendientes = solicitudesPendientes;
    }

    public List<Producto> getUltimosProductos() {
        return ultimosProductos;
    }

    public void setUltimosProductos(List<Producto> ultimosProductos) {
        this.ultimosProductos = ultimosProductos;
    }

    public List<Donation> getUltimasDonaciones() {
        return ultimasDonaciones;
    }

    public void setUltimasDonaciones(List<Donation> ultimasDonaciones) {
        this.ultimasDonaciones = ultimasDonaciones;
    }

    public Map<String, Long> getProductosPorCategoria() {
        return productosPorCategoria;
    }

    public void setProductosPorCategoria(Map<String, Long> productosPorCategoria) {
        this.productosPorCategoria = productosPorCategoria;
    }
}