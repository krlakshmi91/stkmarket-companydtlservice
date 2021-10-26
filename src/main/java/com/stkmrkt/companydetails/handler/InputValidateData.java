package com.stkmrkt.companydetails.handler;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InputValidateDataValidator.class)
public @interface InputValidateData {

	String message() default "{javax.validation.constraints.Size.message}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
	int min() default 0;
	int max() default Integer.MAX_VALUE;
	String value() default "";
	String regex() default "";
	String required();
	String requestName() default "";
	
	@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		InputValidateData[] value();
	}
}
