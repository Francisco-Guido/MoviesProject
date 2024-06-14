package com.project.movies.service;

import java.util.List;

public interface IDirectorService {
	List<String> getDirectorsWithMovieCountGreaterThanThreshold(int threshold);
}
