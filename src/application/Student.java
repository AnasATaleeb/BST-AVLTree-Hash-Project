package application;

public class Student implements Comparable<Student> {
	private int id;
	private String name;
	private String gender;
	private double avg;

	public Student(String name, int id, double avg, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.avg = avg;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override 
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Student o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return name + "/" + id + "/" + gender + "/" + avg;
	}

}
