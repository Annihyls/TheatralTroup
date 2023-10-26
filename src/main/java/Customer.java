import java.util.UUID;

public class Customer {
    private String name;
    private UUID numCustomer;
    private int credit;

    public Customer(String name, int credit){
        this.name = name;
        this.numCustomer = UUID.randomUUID();
        this.credit = credit;
    }

    public String getName() {
        return name;
    }
    public int getCredit() {
        return this.credit;
    }

    public boolean isAvailableForAReduction() {
        return this.credit >= 150;
    }

    public void addCredits(int earned) {
        this.credit += earned;
    }

    public void removeCredits() {
        this.credit -= 150;
    }
}
