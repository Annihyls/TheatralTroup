import java.util.*;

public class Facturation {
    private final Invoice invoice;
    public float totalAmount;
    public int volumeCredits;
    public HashMap<Performance, Float> amounts;

    public Facturation(Invoice invoice){
        this.totalAmount = 0;
        this.volumeCredits = 0;
        this.amounts = new HashMap<>();
        this.invoice = invoice;
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
            this.volumeCredits += Math.max(perf.audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if (Play.Type.COMEDY.equals(play.type)) this.volumeCredits += Math.floor(perf.audience / 5);

            this.amounts.put(perf, amountForThisPlay);
            this.totalAmount += amountForThisPlay;
        }
        this.invoice.customer.addCredits(this.volumeCredits);

    }

    public Invoice getInvoice() {
        return invoice;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }

    public HashMap<Performance, Float> getAmounts() {
        return amounts;
    }
}
