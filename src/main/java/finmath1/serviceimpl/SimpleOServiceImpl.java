package finmath1.serviceimpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finmath1.entity.DatesD;
import finmath1.entity.SimpleO;
import finmath1.repository.SimpleORepository;
import finmath1.service.SimpleOService;

@Service
public class SimpleOServiceImpl implements SimpleOService {

	@Autowired
	private SimpleORepository s;

	@Override
	public void save(SimpleO simple) {
		// TODO Auto-generated method stub
		s.save(simple);
	}

	@Override
	public List<SimpleO> findAll() {
		// TODO Auto-generated method stub
		return s.findAll();
	}

	@Override
	public SimpleO findOne(int id) {
		// TODO Auto-generated method stub
		return s.findOne(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		s.delete(id);
	}

	@Override
	public double findPV(SimpleO simple, List<DatesD> dates) {
		// TODO Auto-generated method stub
		// DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		double sum = 0;
		for (DatesD iter : dates) {

			LocalDate start = LocalDate.parse((CharSequence) iter.getStart());
			LocalDate end = LocalDate.parse((CharSequence) iter.getEnd());
			double d = iter.getD();
			if (simple.getT() == 1) {
				double t = (end.getYear() - start.getYear()) * 365 + (end.getMonthValue() - start.getMonthValue()) * 30
						+ (end.getDayOfMonth() - start.getDayOfMonth());
				sum += (t / 360) * (d / 100);

			}

			if (simple.getT() == 2) {
				double t = ChronoUnit.DAYS.between(start, end);

				sum += (t / 360) * (d / 100);
			}

			if (simple.getT() == 3) {
				double t = ChronoUnit.DAYS.between(start, end);
				sum += (t / 365) * (d / 100);
			}

		}

		return simple.getFV() * (1. - sum);
	}

	@Override
	public double findFV(SimpleO simple, List<DatesD> dates) {
		// TODO Auto-generated method stub
		double sum = 0.;
		for (DatesD iter : dates) {

			LocalDate start = LocalDate.parse((CharSequence) iter.getStart());
			LocalDate end = LocalDate.parse((CharSequence) iter.getEnd());
			double d = iter.getD();
			if (simple.getT() == 1) {
				double t = (end.getDayOfYear() - start.getYear()) * 365
						+ (end.getMonthValue() - start.getMonthValue()) * 30
						+ (end.getDayOfMonth() - start.getDayOfMonth());
				sum += (t / 360) * (d / 100);

			}

			if (simple.getT() == 2) {
				double t = ChronoUnit.DAYS.between(start, end);
				sum += (t / 360) * (d / 100);

			}

			if (simple.getT() == 3) {
				double t = ChronoUnit.DAYS.between(start, end);
				sum += (t / 365.) * (d / 100.);

			}

		}

		return simple.getPV() / (1.0 - sum);
	}

	@Override
	public double average(SimpleO simple, List<DatesD> dates) {
		double sum = 0;
		for (DatesD iter : dates) {

			LocalDate start = LocalDate.parse((CharSequence) iter.getStart());
			LocalDate end = LocalDate.parse((CharSequence) iter.getEnd());
			double d = iter.getD();
			if (simple.getT() == 1) {
				double t = (end.getYear() - start.getYear()) * 365 + (end.getMonthValue() - start.getMonthValue()) * 30
						+ (end.getDayOfMonth() - start.getDayOfMonth());
				sum += (t) * (d / 100);

			}

			if (simple.getT() == 2) {
				double t = ChronoUnit.DAYS.between(start, end);
				sum += (t) * (d / 100);
			}

			if (simple.getT() == 3) {
				double t = ChronoUnit.DAYS.between(start, end);
				sum += (t) * (d / 100);

			}

		}
		LocalDate start = LocalDate.parse((CharSequence) dates.get(0).getStart());
		LocalDate end = LocalDate.parse((CharSequence) dates.get(dates.size() - 1).getEnd());
		System.out.println((double) ChronoUnit.DAYS.between(start, end) + "between");
		return (sum / (double) ChronoUnit.DAYS.between(start, end)) * 100;

	}

	@Override
	public double PVinf(int T, double FV, List<DatesD> dates, double tau) {
		double sum = 0;
		double dinf = 0;
		for (DatesD iter : dates) {

			LocalDate start = LocalDate.parse((CharSequence) iter.getStart());
			LocalDate end = LocalDate.parse((CharSequence) iter.getEnd());
			double d = iter.getD();

			if (T == 1) {
				double t = (end.getYear() - start.getYear()) * 365 + (end.getMonthValue() - start.getMonthValue()) * 30
						+ (end.getDayOfMonth() - start.getDayOfMonth());
				dinf = ((tau / 100 + d / 100) / (1 + (t / 360) * tau / 100));
				sum += (t / 360) * (d);

			}

			if (T == 2) {
				double t = ChronoUnit.DAYS.between(start, end);
				dinf = ((tau / 100 + d / 100) / (1 + (t / 360) * tau / 100));
				sum += (t / 360) * (d);
			}

			if (T == 3) {
				double t = ChronoUnit.DAYS.between(start, end);
				dinf = ((tau / 100 + d / 100) / (1 + (t / 365) * tau / 100));
				sum += (t / 365) * (d);
			}

		}

		return FV * (1. - sum);
	}

	@Override
	public double FVinf(int T, double PV, List<DatesD> dates, double tau) {
		double sum = 0.;
		double dinf = 0;
		for (DatesD iter : dates) {

			LocalDate start = LocalDate.parse((CharSequence) iter.getStart());
			LocalDate end = LocalDate.parse((CharSequence) iter.getEnd());
			double d = iter.getD();
			if (T == 1) {
				double t = (end.getYear() - start.getYear()) * 365 + (end.getMonthValue() - start.getMonthValue()) * 30
						+ (end.getDayOfMonth() - start.getDayOfMonth());
				dinf = ((tau / 100 + d / 100) / (1. + (t / 360) * (tau / 100)));
				sum += (t / 360) * (dinf);

			}

			if (T == 2) {
				double t = ChronoUnit.DAYS.between(start, end);
				dinf = ((tau / 100 + d / 100) / (1. + (t / 360) * (tau / 100)));
				sum += (t / 360) * (dinf);

			}

			if (T == 3) {
				double t = ChronoUnit.DAYS.between(start, end);
				dinf = ((tau / 100 + d / 100) / (1. + (t / 365) * (tau / 100)));
				sum += (t / 365) * (dinf);

			}

		}

		return PV / (1. - sum);
	}

}
