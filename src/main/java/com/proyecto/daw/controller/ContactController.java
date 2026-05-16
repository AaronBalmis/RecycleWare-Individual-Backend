package com.proyecto.daw.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.daw.model.Contact;
import com.proyecto.daw.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacto")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createContactMessage(@Valid @RequestBody Contact contact) {
        ResponseEntity<Map<String, Object>> response;
        
        if (contact == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", "El asunto de la solicitud no puede estar vacío");
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        } else {
           
            if (contact.getNombre() == null || contact.getNombre().trim().isEmpty()
                    || contact.getCorreo() == null || contact.getCorreo().trim().isEmpty()
                    || contact.getMensaje() == null || contact.getMensaje().trim().isEmpty()) {
                Map<String, Object> map = new HashMap<>();
                map.put("ERROR", "Los campos 'nombre', 'correo' y 'mensaje' son obligatorios");
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
              
                Contact contactPost = contactService.guardarMensaje(contact);
                
                Map<String, Object> map = new HashMap<>();
                map.put("mensaje", "Mensaje enviado con éxito. ¡Nos pondremos en contacto pronto!");
                map.put("insertContact", contactPost);
                
                response = ResponseEntity.status(HttpStatus.CREATED).body(map);
            }
        }
        return response;
    }

    
    @GetMapping("/todos")
    public ResponseEntity<List<Contact>> getAllMessages() {
        List<Contact> mensajes = contactService.obtenerTodos();
        
        return ResponseEntity.status(HttpStatus.OK).body(mensajes);
    }
}