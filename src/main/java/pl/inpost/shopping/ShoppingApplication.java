package pl.inpost.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.inpost.shopping.core.policy.AmountBasedDiscountPolicyProperties;
import pl.inpost.shopping.core.policy.PercentageBasedDiscountPolicyProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        AmountBasedDiscountPolicyProperties.class,
        PercentageBasedDiscountPolicyProperties.class
})
public class ShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

}
