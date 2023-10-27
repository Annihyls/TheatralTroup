public class ReductionMemoization {
    private boolean wasAvailableForAReduction = false;
    private float totalAmountAfterReduction;
    private static final int REDUC_VALUE = 15;


    public ReductionMemoization(boolean reducAvailable, float totalAmount) {
        this.wasAvailableForAReduction = reducAvailable;
        this.totalAmountAfterReduction = totalAmount - REDUC_VALUE;
    }

    public boolean isWasAvailableForAReduction() {
        return wasAvailableForAReduction;
    }

    public float getTotalAmountAfterReduction() {
        return totalAmountAfterReduction;
    }

    public void setWasAvailableForAReduction(boolean b) {
        this.wasAvailableForAReduction = b;
    }
}
