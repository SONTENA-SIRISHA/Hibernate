package CRUDOPERATIONS;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Controller 
{
    public static void main(String args[])
    {
       Controller controller = new Controller();
//           controller.addproduct();
//           controller.getproductbyid();
//           controller.updateproduct();
             controller.deleteproduct();
    }
    public void addproduct()
    {
    	Configuration cfg = new Configuration();
    	cfg.configure("hibernate.cfg.xml");
    	
    	SessionFactory sf = cfg.buildSessionFactory();
    	Session session = sf.openSession();
    	
    	Transaction t = session.beginTransaction();
    	Product p = new Product();
    	p.setId(7);
    	p.setName("GADGETS");
    	p.setCategory("REMOTE");
    	p.setCost(2500);
    	
    	session.persist(p); // insert operation
    	
    	t.commit();
    	System.out.println("Product Added Successfully");
    	
    	session.close();
    	sf.close();
    }
    public void getproductbyid()
    {
      Configuration cfg=new Configuration();
      cfg.configure("hibernate.cfg.xml");
      SessionFactory sf=cfg.buildSessionFactory();
 	 Session session=sf.openSession();
 	    
 	 Scanner sc=new Scanner(System.in);
 	 System.out.println("Enter Product ID:");
 	  int pid=sc.nextInt();
 	// There is no need to take transaction object because there is no DML Operation
 	  Product product = session.find(Product.class, pid);
 	    if(product!=null)
 	    {
 	      System.out.println("Product ID="+product.getId());
 	      System.out.println("Product Name="+product.getName());
 	      System.out.println("Product Category="+product.getCategory());
 	      System.out.println("Product Cost="+product.getCost());
 	    
 	    }
 	    else
 	    {
 	      System.out.println("Product ID Not Found");
 	    }
 	    sc.close();
 	    session.close();
 	    sf.close();  
    }
    public void updateproduct()
	  {
	    Configuration cfg=new Configuration();
	    cfg.configure("hibernate.cfg.xml");
	    
	    SessionFactory sf=cfg.buildSessionFactory();
	    Session session=sf.openSession();
	    Transaction t=session.beginTransaction();
	    
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Enter Product ID:");
	    int pid=sc.nextInt();
	    
	   Product product=session.find(Product.class, pid);
	    
	    if(product!=null)
	    {
	      System.out.println("Enter Product Category:");
	      String pcategory=sc.next();
	      System.out.println("Enter Product Cost:");
	      double pcost=sc.nextDouble();
	      System.out.println("Enter Product Name:");
	      String pname=sc.next();
	      
	      product.setCategory(pcategory);
	      product.setCost(pcost);
	      product.setName(pname);
	      
	       t.commit();
	       System.out.println("Product Updated Successfully");
	    }
	    else
	    {
	      System.out.println("Product ID Not Found");
	    }
	    sc.close();
	    session.close();
	    sf.close();
	  }
    public void deleteproduct()
	  {
	    Configuration cfg=new Configuration();
	    cfg.configure("hibernate.cfg.xml");
	    
	    SessionFactory sf=cfg.buildSessionFactory();
	    Session session=sf.openSession();
	    
	    Transaction t=session.beginTransaction();
	    
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Enter Product ID to  Delete:");

	    int pid=sc.nextInt();
	    
	    Product product=session.find(Product.class, pid);
	    
	    if(product!=null)
	    {
	      session.remove(product);
	      t.commit();
	      System.out.println("Product Deleted Successfully");
	    }
	    else
	    {
	      System.out.println("Product ID Not Found");
	    }
	    sc.close();
	    session.close();
	    sf.close();  
}
}