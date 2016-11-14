package finmath1.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finmath1.entity.DatesD;
import finmath1.repository.DatesDRepository;
import finmath1.service.DatesDService;

@Service
public class DatesDServiceImpl implements DatesDService {

	@Autowired
	private DatesDRepository dates;

	@Override
	public void save(DatesD d) {
		// TODO Auto-generated method stub
		dates.save(d);
	}

	@Override
	public List<DatesD> findAll() {
		// TODO Auto-generated method stub
		return dates.findAll();
	}

	@Override
	public DatesD findOne(int id) {
		// TODO Auto-generated method stub
		return dates.findOne(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		dates.delete(id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		dates.deleteAll();
	}

}
