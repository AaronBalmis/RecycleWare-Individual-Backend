package com.proyecto.daw.controller;

import com.proyecto.daw.model.DashboardAdminDTO;
import com.proyecto.daw.model.Donation;
import com.proyecto.daw.model.Producto;
import com.proyecto.daw.repository.ProductoRepository;
import com.proyecto.daw.repository.RequestRepository;
import com.proyecto.daw.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private DonationRepository donacionRepo;

    @Autowired
    private RequestRepository solicitudRepo;

    @GetMapping("/impacto-global")
    public ResponseEntity<Integer> getImpactoGlobal() {
        return ResponseEntity.ok(3450);
    }

    @GetMapping("/alertas-stock")
    public ResponseEntity<List<String>> getAlertasStock() {
        List<String> alertas = Arrays.asList("Monitores", "Ratones");
        return ResponseEntity.ok(alertas);
    }

    // ANGULAR (Panel Administración)

    @GetMapping("/admin-resumen")
    public ResponseEntity<DashboardAdminDTO> getResumenAdministracion() {

        long totalProductos = productoRepo.count();
        long equiposOperativos = productoRepo.countByEstadoNombre("Operativo");
        long donacionesPendientes = donacionRepo.countByEstadoNombre("Pendiente");
        long solicitudesPendientes = solicitudRepo.countByStateNombre("Pendiente");
        List<Producto> ultimosProductos = productoRepo.findTop5ByOrderByIdDesc();
        List<Donation> ultimasDonaciones = donacionRepo.findTop5ByOrderByIdDesc();

        List<Object[]> statsCategoria = productoRepo.countProductosPorCategoria();
        Map<String, Long> productosPorCategoria = new HashMap<>();
        for (Object[] stat : statsCategoria) {
            productosPorCategoria.put((String) stat[0], (Long) stat[1]);
        }

        DashboardAdminDTO resumen = new DashboardAdminDTO(
                totalProductos, equiposOperativos, donacionesPendientes, solicitudesPendientes,
                ultimosProductos, ultimasDonaciones,
                productosPorCategoria);

        return ResponseEntity.ok(resumen);
    }
}