package finmath1.service;

import java.util.List;

import finmath1.entity.DatesD;

public interface DatesDService {

	void save(DatesD d);

	List<DatesD> findAll();

	DatesD findOne(int id);

	void delete(int id);

	void deleteAll();
}
