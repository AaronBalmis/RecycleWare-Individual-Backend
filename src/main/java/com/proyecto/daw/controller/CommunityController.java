package com.proyecto.daw.controller;

import com.proyecto.daw.model.Review;
import com.proyecto.daw.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comunidad")
public class CommunityController {
    @Autowired
    private ReviewService reviewService;

    
    @GetMapping("/resenas")
    public List<Review> getResenas() {
        return reviewService.obtenerResenasActivas();
    }
}
