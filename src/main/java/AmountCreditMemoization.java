public class AmountCreditMemoization {
    private final float totalAmount;
    private final int volumeCredits;

    public AmountCreditMemoization(float totalAmount, int volumeCredits){
        this.totalAmount = totalAmount;
        this.volumeCredits = volumeCredits;
    }
    public float getTotalAmount() {
        return totalAmount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
