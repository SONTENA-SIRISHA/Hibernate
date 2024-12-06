package HQLDemo;

import java.util.List;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;


public class HQLDemoOperations 
{
	 public static void main(String args[])
	  {
	    HQLDemoOperations operations=new HQLDemoOperations();
//	    operations.addstudent();
//	    operations.displayallstudentcompleteobject();
//	    operations.displayallstudentspartialobject();
//       operations.aggregatefunctions();
//	    operations.updatepositionalparams();
//        operations.updatenameparams();
//	    operations.deletepositionalparams();
//        operations.deletenameparams();
//	    operations.displaystudentbyidpositionalparams();
//	    operations.displaystudentbyidnamedparams();
//	    operations.displaystudent();
    operations.paginationdemo();
	    
	  }
	  
	  public void addstudent()
	  {
		  
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      Transaction t =  session.beginTransaction();
	      
	      StudentDemo student = new StudentDemo();
	      student.setId(10);
	      student.setName("PAVS");
	      student.setCgpa(9.4);
	      student.setYear(3);
	      
	      
	      session.persist(student );
	      t.commit();
	      System.out.println("Student  Added Successfully");
	      session.close();
	      sf.close();
	  }
	  public void displayallstudentcompleteobject()//complete object
	  {
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      String hql = "from StudentDemo";//select * from student_table
	      
	      Query<StudentDemo> qry = session.createQuery(hql,StudentDemo.class);
	      List<StudentDemo> studentlist = qry.getResultList();
	      
	      System.out.println("Total Students="+studentlist.size());
	      
	      for(StudentDemo s : studentlist)
	      {
	    	  System.out.println("ID="+s.getId());
	    	  System.out.println("Name="+s.getName());
	    	  System.out.println("Cgpa="+s.getCgpa());
	    	  System.out.println("Year="+s.getYear());   	  
	      }
	      session.close();
	      sf.close();
	      
	      }
	  public void displayallstudentspartialobject()//partial object
	  {
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      String hql="select s.id,s.name from StudentDemo s";
	      Query qry = session.createQuery(hql,Object[].class);
	     List<Object[]> studentlist = qry.getResultList();
	     
	     System.out.println("Total Students="+studentlist.size());
	     
	     for(Object[] obj : studentlist)
	     {
	    	 System.out.println("ID="+obj[0]);
	    	 System.out.println("Name="+obj[1]);
	    	 
	     }
	     session.close();
	     sf.close();
	  }
	  public void displaystudent()
	  {
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      Scanner sc=new Scanner(System.in);
	      System.out.println("Enter Student Name:");
	      String sname=sc.next();  
	      System.out.println("Enter Student Cgpa:");
	      String scgpa=sc.next();  
	      
	      String hql="from StudentDemo where name=?1 and cgpa>=?2";
	      Query<StudentDemo> qry = session.createQuery(hql,StudentDemo.class);
		  qry.setParameter(1, sname);
	      qry.setParameter(2, Double.parseDouble(scgpa));
	      
	      List<StudentDemo> studentlist = qry.getResultList();	
	      
	     System.out.println("Students Count="+studentlist.size());
	      
	      for(StudentDemo s : studentlist)
	      {
	    	  System.out.println("ID="+s.getId());
	    	  System.out.println("Name="+s.getName());
	    	  System.out.println("Cgpa="+s.getCgpa());
	    	  System.out.println("Year="+s.getYear());
	    	  
	      }
	          sc.close();
	          session.close();
	          sf.close();
	      }
	  public void deletenameparams()
	  {
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      Transaction t =  session.beginTransaction();
	      
	      Scanner sc = new Scanner(System.in);
	      System.out.println("Enter Student ID to Delete:");
	      int sid=sc.nextInt();
	      
	      MutationQuery query = session.createMutationQuery("delete from StudentDemo where id=:v");
	      query.setParameter("v",sid);
	      
	     int n =  query.executeUpdate();
	     t.commit();
	     if(n>0)
	     {
	    	 System.out.println(n+"Student(s) Deleted Successfully"); 
	     }
	     else
	     {
	    	 System.out.println("Student ID Not Found");
	     }
	     
	     
	     session.close();
	     sf.close(); 
	}
	  public void updatenameparams()
	  {
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      Transaction t =  session.beginTransaction();
	      
	      Scanner sc = new Scanner(System.in);
	      System.out.println("Enter Student ID:");
	      int sid=sc.nextInt();
	      System.out.println("Enter Student Name:");
	      String sname=sc.next();
	      
	      MutationQuery query = session.createMutationQuery("update StudentDemo set name=:v1 where id=:v2");
	      query.setParameter("v1", sname);
	      query.setParameter("v2", sid);
	      
	     int n =  query.executeUpdate();
	     t.commit();
	     System.out.println(n+"Student(s) Updated Successfully");
	     
	     session.close();
	     sf.close();	  
	  }
	  public void updatepositionalparams()
	  {
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      Transaction t =  session.beginTransaction();
	      
	      Scanner sc = new Scanner(System.in);
	      System.out.println("Enter Student ID:");
	      int sid=sc.nextInt();
	      System.out.println("Enter Student Name:");
	      String sname=sc.next();
	      
	      MutationQuery query = session.createMutationQuery("update Product set name=?1 where id=?2");
	      query.setParameter(1, sname);
	      query.setParameter(2, sid);
	      
	     int n =  query.executeUpdate();
	     t.commit();
	     System.out.println(n+"Student(s) Updated Successfully");
	     
	     session.close();
	     sf.close();
		  
	  }
	  public void aggregatefunctions()
	  {

		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      
	      SessionFactory sf = configuration.buildSessionFactory();
	      Session session = sf.openSession();
	      
	      String hql1="select count(*)from StudentDemo";
	      Query<Long> qry1 = session.createQuery(hql1,Long.class);
	      Long count = qry1.getSingleResult();
	      System.out.println("Total Students Count="+count);
	      
	      String hql2="select sum(cgpa)from StudentDemo";
	      Query<Double> qry2 = session.createQuery(hql2,Double.class);
	      Double totalcgpa = qry2.getSingleResult();
	      System.out.println("Total Cgpa="+totalcgpa);
	      
	      String hql3="select avg(cgpa)from StudentDemo";
	      Query<Double> qry3 = session.createQuery(hql3,Double.class);
	      Double avgcgpa = qry3.getSingleResult();
	      System.out.println("Average Year="+avgcgpa);
	      
	      String hql4="select min(year)from StudentDemo";
	      Query<Integer> qry4 = session.createQuery(hql4,Integer.class);
	      int minyear = qry4.getSingleResult();
	      System.out.println("Minimum year="+minyear);
	      
	      String hql5="select max(year)from StudentDemo";
	      Query<Integer> qry5 = session.createQuery(hql4,Integer.class);
	      int maxyear = qry4.getSingleResult();
	      System.out.println("Maximum year="+minyear);
	      
	      session.close();
	      sf.close();  
	  }
	  public void paginationdemo()
	   {
		  Configuration configuration = new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	        
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();
	        
	        String hql = "from StudentDemo"; 
	        
	        Query<StudentDemo> qry = session.createQuery(hql, StudentDemo.class);
	        qry.setFirstResult(4);//add +1 and print the records
	        qry.setMaxResults(10);//print number of records
	        List<StudentDemo> studentlist =  qry.getResultList();
	        
	        System.out.println("Total Products="+studentlist.size());
	        
	         for( StudentDemo s : studentlist) 
	         {
	           System.out.println(s.toString());
	         }
	         
	         session.close();
	         sf.close();
	   }
	  
}