package finmath1.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import finmath1.entity.DatesD;
import finmath1.entity.Results;
import finmath1.entity.SimpleO;
import finmath1.service.DatesDService;
import finmath1.service.ResultsService;
import finmath1.service.SimpleOService;

@Controller
public class FinMathController {

	@Autowired
	private DatesDService datesD;

	@Autowired
	private SimpleOService simple;

	@Autowired
	private ResultsService res;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String admin(Model model) {
		model.addAttribute("dates", datesD.findAll());

		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("dates", datesD.findAll());
		model.addAttribute("results", res.findAll());

		return "home";
	}

	@RequestMapping(value = "/calculate", method = RequestMethod.GET)
	public String calculate(@RequestParam String T, @RequestParam String radioCALC, @RequestParam String PV,

			@RequestParam String FV, @RequestParam String average, @RequestParam String inflation,

			@RequestParam String tau, Model model) {
		double FVnew = 0;
		double PVnew = 0;
		double averD = 0;
		double FVinf = 0;
		double PVinf = 0;
		ArrayList<DatesD> dates = new ArrayList<>();
		SimpleO simpleo;

		int Tint = 1;
		if (T.equals("1")) {
			Tint = 1;
		}
		if (T.equals("2")) {
			Tint = 2;
		}
		if (T.equals("3")) {
			Tint = 3;
		}

		if (radioCALC.equals("FV")) {

			simpleo = new SimpleO(0, Double.parseDouble(PV), Tint);
			FVnew = simple.findFV(simpleo, datesD.findAll());
			System.out.println(FVnew);

		}

		if (radioCALC.equals("PV")) {

			simpleo = new SimpleO(Double.parseDouble(FV), 0, Tint);
			PVnew = simple.findPV(simpleo, datesD.findAll());
			System.out.println(PVnew);

		}
		if (average.equals("true")) {
			simpleo = new SimpleO(0, 0, Tint);
			averD = simple.average(simpleo, datesD.findAll());
			System.out.println(averD);

		}

		if (inflation.equals("true")) {
			if (FVnew == 0) {

				PVinf = simple.PVinf(Tint, Double.parseDouble(FV), datesD.findAll(), Double.parseDouble(tau));
				FVinf = Double.parseDouble(FV);
			}
			if (PVnew == 0) {
				FVinf = simple.FVinf(Tint, Double.parseDouble(PV), datesD.findAll(), Double.parseDouble(tau));
				PVinf = Double.parseDouble(PV);

			}

			System.out.println(Tint + "T");
		}

		if (FVnew == 0)
			FVnew = Double.parseDouble(FV);
		if (PVnew == 0)
			PVnew = Double.parseDouble(PV);

		Results result = new Results(PVnew, FVnew, averD, PVinf, FVinf);
		res.save(result);

		return "redirect:/home";
	}

	@RequestMapping(value = "/adddata", method = RequestMethod.GET)
	public String add(@ModelAttribute DatesD dates) {
		datesD.save(dates);
		return "redirect:/home";
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String clear() {
		datesD.deleteAll();
		return "redirect:/home";
	}

	@RequestMapping(value = "/clearres", method = RequestMethod.GET)
	public String clearres() {
		res.deleteAll();
		return "redirect:/home";
	}
}
