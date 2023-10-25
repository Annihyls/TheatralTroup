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
        boolean result = false;
        if(this.credit >= 150) {
            this.credit -= 150;
            result = true;
        }
        return result;
    }

    public void addCredits(int earned) {
        this.credit += earned;
    }
}
