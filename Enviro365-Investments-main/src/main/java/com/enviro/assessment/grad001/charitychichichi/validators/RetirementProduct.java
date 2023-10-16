package com.enviro.assessment.grad001.charitychichichi.validators;

import com.enviro.assessment.grad001.charitychichichi.domain.Investor;
import com.enviro.assessment.grad001.charitychichichi.domain.Product;
import com.enviro.assessment.grad001.charitychichichi.domain.Type;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Objects;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = RetirementProductValidator.class)
@Documented
public @interface RetirementProduct {
    String message() default "If product type is RETIREMENT, investor age must be greater than or equal to 65";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class RetirementProductValidator implements ConstraintValidator<RetirementProduct, Product> {

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {
        if (Objects.isNull(product) || Objects.isNull(product.getType())) {
            return true;
        }

        if (product.getType() == Type.RETIREMENT) {
            Investor investor = product.getInvestor();
            return !Objects.isNull(investor) && investor.getAge() >= 65;
        }

        return true;
    }
}
