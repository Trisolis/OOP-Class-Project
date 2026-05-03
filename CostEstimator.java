/*
Handles the cost estimation feature (figure it might as well have its own class)
*/

public class CostEstimator {
    // TODO: Decide on pricing formula or table with group
    
    private static final double BASE_RATE = 5.00;
    private static final double RATE_PER_KG = 2.50;

    public double estimate(double weight) {
        // Placeholder: base + (weight * rate)
        return BASE_RATE + (weight * RATE_PER_KG);
    }
}