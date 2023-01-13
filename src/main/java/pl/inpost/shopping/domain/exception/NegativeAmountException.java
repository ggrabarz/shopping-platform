package pl.inpost.shopping.domain.exception;

public final class NegativeAmountException extends DomainException {
    public NegativeAmountException() {
        super("Discount cannot be applied to negative amounts.");
    }
}
