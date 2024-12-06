package HQLDemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_table")
public class StudentDemo 
{
	@Id
	private int id;
	@Column(name="sname",length=50,nullable=false)
	private String name;
	@Column(name="scgpa",nullable=false)
	private double cgpa;
	@Column(name="syear",nullable=false)
	private int year;

	@Override
	public String toString() {
		return "StudentDemo [id=" + id + ", name=" + name + ", cgpa=" + cgpa + ", year=" + year + "]";
	}
	public int getId() {
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
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	

}
