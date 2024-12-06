package HCQLDEMO;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOperations 
{
	public static void main(String []args)
	 {
		 HCQLOperations operations = new HCQLOperations();
		 //operations.addStudent();
		 operations.restrictionsdemo();
		// operations.orderdemo();
		 //operations.aggregatefunctions();
		// operations.hcqldemo();
	 }
	public void addStudent()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Student student = new Student();
		student.setId(3);
		student.setName("Supriya");
		student.setGender("FEMALE");
		student.setAge(26);
		student.setDepartment("CSE");
		student.setEmail("suppi@gmail.com");
		student.setContact("5656565656 ");
		
		session.persist(student);
		t.commit();
		
		System.out.println("Student Added Successfully");
		
		session.close();
		sf.close();
	}
	public void restrictionsdemo()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		 SessionFactory sf = configuration.buildSessionFactory();
		 Session session = sf.openSession();
		
		 CriteriaBuilder cb = session.getCriteriaBuilder();
		 CriteriaQuery<Student> cq = cb.createQuery(Student.class); 
		 Root<Student> root = cq.from(Student.class);
		  
		  System.out.println("*All Student Objects*");
		  List<Student> students =session.createQuery(cq).getResultList();
		  System.out.println("Totla Students="+students.size());
		  for(Student s:students) 
		    { 
		    // you can also use accessor method of student object
		     System.out.println(s.toString());
		  } 
		 
		
		/* CriteriaBuilder cb = session.getCriteriaBuilder(); 
		 CriteriaQuery<Student> cq= cb.createQuery(Student.class); 
		  //from Student [complete object]
		 Root<Student> root = cq.from(Student.class);
		  
		  cq.select(root).where(cb.equal(root.get("department"), "CSE"));
	
		  System.out.println("*All Student Objects*"); 
		  List<Student> students = session.createQuery(cq).getResultList();
		    System.out.println("Totla Students="+students.size());
		  for(Student s:students)
		  { 
		  // you can also use accessor method of student object 
			 System.out.println(s.toString());
		  } */
		  
//		  CriteriaBuilder cb = session.getCriteriaBuilder(); 
//		  CriteriaQuery<Student> cq= cb.createQuery(Student.class); 
//		  //from Student [complete object]
//		  Root<Student> root = cq.from(Student.class);
		
		  //cq.select(root).where(cb.lessThan(root.get("age"), 50));
	  //cq.select(root).where(cb.greaterThan(root.get("age"),45));//less than or equal to 
//		  cq.select(root).where(cb.le(root.get("age"),40 )); //greater than or equal to
//		  cq.select(root).where(cb.ge(root.get("age"),40 ));
//		  cq.select(root).where(cb.notEqual(root.get("department"),"CSE" )); // NOT EQUAL
		  
  //Like Operations:
		  //cq.select(root).where(cb.like(root.get("department"), "C%")); // starts with C
		  //cq.select(root).where(cb.like(root.get("department"), "%E"));
		  //cq.select(root).where(cb.like(root.get("department"), "%T")); //ends with E
		 //cq.select(root).where(cb.like(root.get("department"), "%SI%")); //si as sub string
		 // cq.select(root).where(cb.like(root.get("email"), "%gmail%")); //gmail as substring
		   //cq.select(root).where(cb.like(root.get("name"), "___D")); //ends with d & length should be 4
		  //cq.select(root).where(cb.between(root.get("age"), 30, 50));
		  
		  //you can apply not criteria for all above comparison criteria
		  //cq.select(root).where(cb.not(cb.equal(root.get("department"), "CSE")));
		  
		  //List<String> depts = Arrays.asList("CSE","ECE","ME");
		 // cq.select(root).where(root.get("department").in(depts));
		  
	     System.out.println("*Student Objects with comparision criteria*"); 
		  List<Student> students1 = session.createQuery(cq).getResultList();
		  System.out.println("Totla Students="+students1.size());
		  for(Student s:students1) 
		  { 
			// you can also use accessor method of student object 
			System.out.println(s.toString());
		   }
		  
		session.close();
		sf.close();
	}
	public void orderdemo() //ascending / descending
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class); 
		Root<Student> root = cq.from(Student.class);
		
		  //ascending order based on name
		 //cq.orderBy(cb.asc(root.get("name")));
		
		//descending order based on age
		cq.orderBy(cb.desc(root.get("name")));
		

		  System.out.println("****Order By Demo****"); 
		  List<Student> students = session.createQuery(cq).getResultList();
		    System.out.println("Student Count="+students.size());
		  for(Student s:students)
		  { 
		  // you can also use accessor method of student object 
			 System.out.println(s.toString());
		  } 
		  
		  session.close();
		  sf.close();
		
	}
	public void aggregatefunctions()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq1 = cb1.createQuery(Long.class); 
		Root<Student> root1 = cq1.from(Student.class);
		cq1.select(cb1.count(root1.get("name")));
		Long count1 = session.createQuery(cq1).getSingleResult();
		System.out.println("Total Students Count="+count1);
		
		CriteriaBuilder cb2 = session.getCriteriaBuilder();
		CriteriaQuery<Double> cq2 = cb2.createQuery(Double.class); 
		Root<Student> root2 = cq2.from(Student.class);
		cq2.select(cb2.sum(root2.get("age")));
		double totalage = session.createQuery(cq2).getSingleResult();
		System.out.println("Sum Of Students="+totalage);
		
		CriteriaBuilder cb3 = session.getCriteriaBuilder();
		CriteriaQuery<Double> cq3 = cb3.createQuery(Double.class); 
		Root<Student> root3 = cq3.from(Student.class);
		cq3.select(cb3.avg(root3.get("age")));
		double avgage= session.createQuery(cq3).getSingleResult();
		System.out.println("Average Count="+avgage);
		
		CriteriaBuilder cb4 = session.getCriteriaBuilder();
		CriteriaQuery<Integer> cq4 = cb4.createQuery(Integer.class); 
		Root<Student> root4 = cq4.from(Student.class);
		cq4.select(cb4.min(root4.get("id")));
		int minsid= session.createQuery(cq4).getSingleResult();
		System.out.println("Minimum Student ID="+minsid);
		
		CriteriaBuilder cb5 = session.getCriteriaBuilder();
		CriteriaQuery<Integer> cq5 = cb5.createQuery(Integer.class); 
		Root<Student> root5 = cq5.from(Student.class);
		cq5.select(cb5.max(root5.get("id")));
		int maxsid= session.createQuery(cq5).getSingleResult();
		System.out.println("Maximum Student ID="+maxsid);
		
		CriteriaBuilder cb6 = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq6 = cb6.createQuery(Long.class); 
		Root<Student> root6 = cq6.from(Student.class);
		cq6.select(cb6.countDistinct(root6.get("department")));
		long distinctcount= session.createQuery(cq6).getSingleResult();
		System.out.println("Distinct Department Count="+distinctcount);
		
		session.close();
		sf.close();	
	}
	
	public void hcqldemo()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class); 
		Root<Student> root = cq.from(Student.class);
	    
//		cq.select(root).where(cb.equal(root.get("department"), "CSE"));
//		cq.orderBy(cb.desc(root.get("age")));
//		cq.select(root).where(cb.lessThan(root.get("age"), 50));
//		cq.select(root).where(cb.greaterThan(root.get("age"),45));//less than or equal to 
//		cq.select(root).where(cb.le(root.get("age"),30 )); //greater than or equal to
//		cq.select(root).where(cb.ge(root.get("age"),30 ));//equal to
		cq.select(root).where(cb.notEqual(root.get("department"),"CSE" )); // NOT EQUAL
		
		List<Student> students =session.createQuery(cq).getResultList();
		System.out.println("Students Count="+students.size());
		for(Student s:students) 
		{ 
		  System.out.println(s.toString());
		} 
		session.close();
		sf.close();
		
	}
	
}