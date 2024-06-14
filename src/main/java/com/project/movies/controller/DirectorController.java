package com.project.movies.controller;

import java.util.List;

import com.project.movies.service.impl.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DirectorController implements DirectorControllerSpec {

	@Autowired
	private DirectorService directorService;

	public List<String> getDirectors(int threshold) {
		return directorService.getDirectorsWithMovieCountGreaterThanThreshold(threshold);
	}
}
