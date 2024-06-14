package com.project.movies.service.impl;

import com.project.movies.model.Movie;
import com.project.movies.model.ApiResponse;
import com.project.movies.service.IDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectorService implements IDirectorService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.movies.url}")
	private String apiUrl;

	@Value("${api.movies.search.page}")
	private String searchPage;

	public List<String> getDirectorsWithMovieCountGreaterThanThreshold(int threshold) {
		Map<String, Integer> directorMap = new HashMap<>();

		int pageNumber = 1;
		int totalPages = Integer.MAX_VALUE;

		while (pageNumber <= totalPages) {
			String url = apiUrl + searchPage + pageNumber;

			try {
				ApiResponse movieListResponse = restTemplate.getForObject(url, ApiResponse.class);

				countDirectors(movieListResponse.getData(), directorMap);
				totalPages = movieListResponse.getTotal_pages();

				pageNumber++;
			} catch (RestClientException e) {
				e.printStackTrace();
				break;
			}
		}

		return filterAndSortDirectors(directorMap, threshold);
	}

	private void countDirectors(List<Movie> movies, Map<String, Integer> directorMap) {
		for (Movie movie : movies) {
			String directorName = movie.getDirector();
			directorMap.put(directorName, directorMap.getOrDefault(directorName, 0) + 1);
		}
	}

	private List<String> filterAndSortDirectors(Map<String, Integer> directorMap, int threshold) {
		return directorMap.entrySet().stream()
				.filter(entry -> entry.getValue() > threshold)
				.map(Map.Entry::getKey)
				.sorted()
				.collect(Collectors.toList());
	}
}
