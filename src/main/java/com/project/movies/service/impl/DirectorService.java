package com.project.movies.service.impl;

import com.project.movies.model.Movie;
import com.project.movies.model.ApiResponse;
import com.project.movies.service.IDirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorService implements IDirectorService {

	private final RestTemplate restTemplate;

	private final String API_URL = "https://directa24-movies.wiremockapi.cloud/api/movies";
	private final String API_URL_SEARCH_PAGE = "/search?page=";

	public List<String> getDirectorsWithMovieCountGreaterThanThreshold(int threshold)
	{
		Map<String, Integer> directorMap = new HashMap<>();

		int pageNumber = 1;
		ApiResponse movieListResponse = apiResponse(pageNumber);

		while (pageNumber <= movieListResponse.getTotal_pages()) {
			try {
				movieListResponse = apiResponse(pageNumber);
				countDirectors(movieListResponse.getData(), directorMap);
				pageNumber++;
			} catch (RestClientException e) {
				e.printStackTrace();
				break;
			}
		}

		return filterAndSortDirectors(directorMap, threshold);
	}

	private ApiResponse apiResponse(int pageNumber)
	{
		return restTemplate.getForObject(API_URL.concat(API_URL_SEARCH_PAGE).concat(String.valueOf(pageNumber)), ApiResponse.class);
	}

	private void countDirectors(List<Movie> movies, Map<String, Integer> directorMap)
	{
		for (Movie movie : movies) {
			String directorName = movie.getDirector();
			directorMap.put(directorName, directorMap.getOrDefault(directorName, 0) + 1);
		}
	}

	private List<String> filterAndSortDirectors(Map<String, Integer> directorMap, int threshold)
	{
		return directorMap.entrySet().stream()
				.filter(entry -> entry.getValue() > threshold)
				.map(Map.Entry::getKey)
				.sorted()
				.collect(Collectors.toList());
	}
}
