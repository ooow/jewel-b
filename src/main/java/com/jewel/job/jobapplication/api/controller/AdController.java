package com.jewel.job.jobapplication.api.controller;

import com.jewel.job.jobapplication.domain.Ad;
import com.jewel.job.jobapplication.repository.AdRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/ads")
public class AdController {

    private final AdRepository repository;

    public AdController(AdRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Ad> getAds() {
//        return AdConverter.convert(repository.findAll());
        return repository.findAll();
    }
}
