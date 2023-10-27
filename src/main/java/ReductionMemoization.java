public class ReductionMemoization {
    public boolean wasAvailableForAReduction;
    public float totalAmountAfterReduction;
    public ReductionMemoization(boolean reducAvailable, float reducAmount) {
        this.wasAvailableForAReduction = reducAvailable;
        this.totalAmountAfterReduction = reducAmount;
    }

    public boolean isWasAvailableForAReduction() {
        return wasAvailableForAReduction;
    }

    public float getTotalAmountAfterReduction() {
        return totalAmountAfterReduction;
    }

}
