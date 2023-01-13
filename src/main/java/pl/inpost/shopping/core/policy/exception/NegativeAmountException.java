package pl.inpost.shopping.core.policy.exception;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException() {
        super("Discount cannot be applied to negative amounts.");
    }
}
