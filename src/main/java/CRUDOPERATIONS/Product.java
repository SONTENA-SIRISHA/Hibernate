package CRUDOPERATIONS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product_table")
public class Product 
{
  @Id
  private int id;
  @Column(name = "pcategory",length=50,nullable = false)
  private String category;
  @Column(name="pname",length = 50,nullable = false)
  private String name;
  @Column(name = "pcost",nullable = false)
  private double cost;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public double getCost() {
    return cost;
  }
  public void setCost(double cost) {
    this.cost = cost;
  }

}