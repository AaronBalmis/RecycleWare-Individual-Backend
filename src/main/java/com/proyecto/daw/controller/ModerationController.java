package com.proyecto.daw.controller; // Asegúrate de que esta ruta es la tuya

import com.proyecto.daw.model.Donation;
import com.proyecto.daw.model.DonationState;
import com.proyecto.daw.model.Request;
import com.proyecto.daw.model.RequestState;
import com.proyecto.daw.repository.DonationRepository;
import com.proyecto.daw.repository.DonationStateRepository;
import com.proyecto.daw.repository.RequestRepository;
import com.proyecto.daw.repository.RequestStateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.daw.repository.ContactRepository;

import java.util.List;

@RestController
@RequestMapping("/moderacion")
public class ModerationController {
    @Autowired
    private DonationRepository donacionRepo;

    @Autowired
    private RequestRepository solicitudRepo;

    @Autowired
    private DonationStateRepository estadoDonacionRepo;

    @Autowired
    private RequestStateRepository estadoSolicitudRepo;

    @Autowired
    private ContactRepository contactRepo;

    @GetMapping("/donaciones-pendientes")
    public ResponseEntity<List<Donation>> getDonacionesPendientes() {
        List<Donation> pendientes = donacionRepo.findByEstadoNombre("Pendiente");
        return ResponseEntity.ok(pendientes);
    }

    @GetMapping("/solicitudes-pendientes")
    public ResponseEntity<List<Request>> getSolicitudesPendientes() {
        List<Request> pendientes = solicitudRepo.findByStateNombre("Pendiente");
        return ResponseEntity.ok(pendientes);
    }

    @PutMapping("/donaciones/{id}/aprobar")
    public ResponseEntity<?> aprobarDonacion(@PathVariable Integer id) {
        return donacionRepo.findById(id).map(donacion -> {
            DonationState aprobado = estadoDonacionRepo.findByNombre("En Recogida").get();
            donacion.setEstado(aprobado);
            donacionRepo.save(donacion);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/solicitudes/{id}/aprobar")
    public ResponseEntity<?> aprobarSolicitud(@PathVariable Integer id) {
        return solicitudRepo.findById(id).map(solicitud -> {
            RequestState aprobado = estadoSolicitudRepo.findByNombre("Aprobada").get();
            solicitud.setState(aprobado);
            solicitudRepo.save(solicitud);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/solicitudes/{id}/rechazar")
    public ResponseEntity<?> rechazarSolicitud(@PathVariable Integer id) {
        return solicitudRepo.findById(id).map(solicitud -> {
            RequestState denegado = estadoSolicitudRepo.findByNombre("Denegada").get();
            solicitud.setState(denegado);
            solicitudRepo.save(solicitud);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/donaciones/{id}/rechazar")
    public ResponseEntity<?> rechazarDonacion(@PathVariable Integer id) {
        return donacionRepo.findById(id).map(donacion -> {
            donacionRepo.delete(donacion);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/mensajes/{id}/eliminar")
    public ResponseEntity<?> eliminarMensaje(@PathVariable Integer id) {
        if (contactRepo.existsById(id)) {
            contactRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/solicitudes-aprobadas")
    public ResponseEntity<List<Request>> getSolicitudesAprobadas() {
        return ResponseEntity.ok(solicitudRepo.findByStateNombre("Aprobada"));
    }

    @GetMapping("/donaciones-en-curso")
    public ResponseEntity<List<Donation>> getDonacionesEnCurso() {
        List<Donation> enRecogida = donacionRepo.findByEstadoNombre("En Recogida");
        List<Donation> recibidas = donacionRepo.findByEstadoNombre("Recibido");
        enRecogida.addAll(recibidas); 
        return ResponseEntity.ok(enRecogida);
    }

    @PutMapping("/solicitudes/{id}/entregar")
    public ResponseEntity<?> entregarSolicitud(@PathVariable Integer id) {
        return solicitudRepo.findById(id).map(solicitud -> {
            RequestState entregada = estadoSolicitudRepo.findByNombre("Entregada").get();
            solicitud.setState(entregada);
            solicitudRepo.save(solicitud);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/donaciones/{id}/recibir")
    public ResponseEntity<?> recibirDonacion(@PathVariable Integer id) {
        return donacionRepo.findById(id).map(donacion -> {
            DonationState recibido = estadoDonacionRepo.findByNombre("Recibido").get();
            donacion.setEstado(recibido);
            donacionRepo.save(donacion);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/donaciones/{id}/procesar")
    public ResponseEntity<?> procesarDonacion(@PathVariable Integer id) {
        return donacionRepo.findById(id).map(donacion -> {
            DonationState procesado = estadoDonacionRepo.findByNombre("Procesado").get();
            donacion.setEstado(procesado);
            donacionRepo.save(donacion);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/solicitudes-historial")
    public ResponseEntity<List<Request>> getHistorialSolicitudes() {
        List<Request> entregadas = solicitudRepo.findByStateNombre("Entregada");
        List<Request> denegadas = solicitudRepo.findByStateNombre("Denegada");
        entregadas.addAll(denegadas); 
        return ResponseEntity.ok(entregadas);
    }

    @GetMapping("/donaciones-historial")
    public ResponseEntity<List<Donation>> getHistorialDonaciones() {
        List<Donation> procesadas = donacionRepo.findByEstadoNombre("Procesado");
        return ResponseEntity.ok(procesadas);
    }
}