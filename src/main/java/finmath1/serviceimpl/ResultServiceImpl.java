package finmath1.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finmath1.entity.Results;
import finmath1.repository.ResultsRepository;
import finmath1.service.ResultsService;

@Service
public class ResultServiceImpl implements ResultsService {

	@Autowired
	private ResultsRepository rep;

	@Override
	public void save(Results d) {
		// TODO Auto-generated method stub
		rep.save(d);
	}

	@Override
	public List<Results> findAll() {
		// TODO Auto-generated method stub
		return rep.findAll();
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		rep.deleteAll();

	}

}
