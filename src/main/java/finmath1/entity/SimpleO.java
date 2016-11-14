package finmath1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimpleO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double FV;
	private double PV;

	private int T;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getFV() {
		return FV;
	}

	public void setFV(double fV) {
		FV = fV;
	}

	public double getPV() {
		return PV;
	}

	public void setPV(double pV) {
		PV = pV;
	}

	public int getT() {
		return T;
	}

	public void setT(int t) {
		T = t;
	}

	public SimpleO(double fV, double pV, int t) {
		super();

		FV = fV;
		PV = pV;

		T = t;
	}

	public SimpleO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
