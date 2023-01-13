package pl.inpost.shopping.domain.exception;

public final class NegativePriceException extends DomainException {
    public NegativePriceException() {
        super("Discount cannot be applied to negative prices.");
    }
}
