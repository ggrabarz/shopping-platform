package pl.inpost.shopping.domain.exception;

public sealed class DomainException extends RuntimeException permits NegativeAmountException, NegativePriceException, ProductNotFound {
    public DomainException(String message) {
        super(message);
    }
}
