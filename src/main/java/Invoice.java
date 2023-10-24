import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public String getCustomer() {
    return customer;
  }

  public List<Performance> getPerformances() {
    return performances;
  }
}
