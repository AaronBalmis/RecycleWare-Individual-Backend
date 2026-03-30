package com.proyecto.daw.service;

import com.proyecto.daw.model.Review;
import com.proyecto.daw.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> obtenerResenasActivas() {
        return reviewRepository.findByActivaTrue();
    }
}