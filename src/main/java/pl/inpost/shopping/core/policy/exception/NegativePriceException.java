package pl.inpost.shopping.core.policy.exception;

public class NegativePriceException extends RuntimeException {
    public NegativePriceException() {
        super("Discount cannot be applied to negative prices.");
    }
}
