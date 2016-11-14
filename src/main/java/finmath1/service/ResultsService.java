package finmath1.service;

import java.util.List;

import finmath1.entity.Results;

public interface ResultsService {

	void save(Results d);

	List<Results> findAll();

	void deleteAll();
}
