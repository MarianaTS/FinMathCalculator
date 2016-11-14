package finmath1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DatesD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String start;
	private String end;
	private double d;

	@Override
	public String toString() {
		return "DatesD [id=" + id + ", start=" + start + ", end=" + end + ", d=" + d + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public DatesD(String start, String end, double d) {
		super();

		this.start = start;
		this.end = end;
		this.d = d;
	}

	public DatesD() {

		// TODO Auto-generated constructor stub
	}

}
