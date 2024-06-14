package com.project.movies.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface DirectorControllerSpec {

    @Operation(summary = "This endpoint is to get a list of directors (sorted alphabetically) with more movies than you indicate. - " +
            "Este endpoint es para obtener un listado de directores (Ordenado alfabeticamente) con más peliculas de lo que usted indique.")

    @GetMapping("directors")
    List<String> getDirectors(@RequestParam("threshold") int threshold);
}
