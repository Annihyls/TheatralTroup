import java.util.*;

public class Facturation {
    private final Invoice invoice;
    private AmountCreditMemoization acm;
    public boolean wasAvailableForAReduction;
    public float totalAmountAfterReduction;
    public HashMap<Performance, Float> amounts;

    public Facturation(Invoice invoice){
        this.acm = new AmountCreditMemoization();
        this.amounts = new HashMap<>();
        this.invoice = invoice;
        this.wasAvailableForAReduction = false;
    }

    public void calculFacture() {
        for (Performance perf : this.invoice.performances) {
            Play play = perf.play;
            float amountForThisPlay = 0;
            switch (play.type) {
                case TRAGEDY:
                    amountForThisPlay = 400;
                    if (perf.audience > 30) {
                        amountForThisPlay += 10 * (perf.audience - 30);
                    }
                    break;
                case COMEDY:
                    amountForThisPlay = 300;
                    if (perf.audience > 20) {
                        amountForThisPlay += 100 + 5 * (perf.audience - 20);
                    }
                    amountForThisPlay += 3 * perf.audience;
                    break;
                default:
                    throw new Error("unknown type: ${play.type}");
            }
            // add volume credits
            this.acm.addVolumeCredit(Math.max(perf.audience - 30, 0));
            // add extra credit for every ten comedy attendees
            if (Play.Type.COMEDY.equals(play.type)){
                int perfCredit = (int) Math.floor((double) perf.audience / 5);
                this.acm.addVolumeCredit(perfCredit);
            }

            this.amounts.put(perf, amountForThisPlay);
            this.acm.addTotalAmount(amountForThisPlay);
        }
        this.invoice.customer.addCredits(this.acm.getVolumeCredits());
        reductionCalculator();
    }

    private void reductionCalculator() {
        if(this.invoice.customer.isAvailableForAReduction()) {
            this.wasAvailableForAReduction = true;
            this.invoice.customer.removeCredits();
            this.totalAmountAfterReduction = this.acm.getTotalAmount() - 15;
        }
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public float getTotalAmount() {
        return acm.getTotalAmount();
    }

    public int getVolumeCredits() {
        return acm.getVolumeCredits();
    }

    public HashMap<Performance, Float> getAmounts() {
        return amounts;
    }
    public float getTotalAmountAfterReduction() {
        return totalAmountAfterReduction;
    }
    public boolean getWasAvailableForAReduction() {
        return wasAvailableForAReduction;
    }

    public String getCustomerName() {
        return this.invoice.customer.getName();
    }

    public List<Performance> getPerformances() {
        return this.invoice.performances;
    }

    public int getCustomerCredits() {
        return this.invoice.customer.getCredit();
    }
}
