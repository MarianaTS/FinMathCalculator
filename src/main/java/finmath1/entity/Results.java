package finmath1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Results {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double PV;
	private double FV;
	private double averD;
	private double PVinf;
	private double FVinf;

	public double getPV() {
		return PV;
	}

	public void setPV(double pV) {
		PV = pV;
	}

	public double getFV() {
		return FV;
	}

	public void setFV(double fV) {
		FV = fV;
	}

	public double getAverD() {
		return averD;
	}

	public void setAverD(double averD) {
		this.averD = averD;
	}

	public double getPVinf() {
		return PVinf;
	}

	public void setPVinf(double pVinf) {
		PVinf = pVinf;
	}

	public double getFVinf() {
		return FVinf;
	}

	public void setFVinf(double fVinf) {
		FVinf = fVinf;
	}

	public Results(double pV, double fV, double averD, double pVinf, double fVinf) {
		super();
		PV = pV;
		FV = fV;
		this.averD = averD;
		PVinf = pVinf;
		FVinf = fVinf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Results() {
		super();
		// TODO Auto-generated constructor stub
	}

}
