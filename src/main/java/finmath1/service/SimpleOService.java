package finmath1.service;

import java.util.List;

import finmath1.entity.DatesD;
import finmath1.entity.SimpleO;

public interface SimpleOService {
	void save(SimpleO simple);

	List<SimpleO> findAll();

	SimpleO findOne(int id);

	void delete(int id);

	double findPV(SimpleO simple, List<DatesD> dates);

	double findFV(SimpleO simple, List<DatesD> dates);

	double average(SimpleO simple, List<DatesD> dates);

	double FVinf(int T, double PV, List<DatesD> dates, double tau);

	double PVinf(int T, double FV, List<DatesD> dates, double tau);

}
