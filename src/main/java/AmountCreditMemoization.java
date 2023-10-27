public class AmountCreditMemoization {
    private float totalAmount;
    private int volumeCredits;

    public AmountCreditMemoization(){
        this.totalAmount = 0;
        this.volumeCredits = 0;
    }
    public float getTotalAmount() {
        return totalAmount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }

    public void addVolumeCredit(int value) {
        this.volumeCredits += value;
    }

    public void addTotalAmount(float value) {
        this.totalAmount += value;
    }

}
