package com.stkmrkt.companydetails.handler;

import static com.stkmrkt.companydetails.constants.Constants.INPUT_FIELD_INVALID;
import static com.stkmrkt.companydetails.constants.Constants.INPUT_FIELD_LENGTH_INVALID;
import static com.stkmrkt.companydetails.constants.Constants.INPUT_FIELD_REQUIRED;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.util.StringUtils;

import com.stkmrkt.companydetails.exception.InputValidationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputValidateDataValidator implements ConstraintValidator<InputValidateData, Object> {

	private String fieldName;
	private int min;
	private int max;
	private String regex;
	boolean isOptional = true;

	@Override
	public void initialize(InputValidateData constraintAnnotation) {
		fieldName = constraintAnnotation.value();
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
		regex = constraintAnnotation.regex();
		if (constraintAnnotation.required().equalsIgnoreCase("Y"))
			isOptional = false;

		if (min <= 0 && !isOptional) {
			min = 1;
		}
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String objValue = null;
		fieldName = ((ConstraintValidatorContextImpl) context).getConstraintViolationCreationContexts().get(0).getPath()
				.asString();

		objValue = getValueAsString(value);
		
		if (!isOptional)
			checkNullOrEmpty(objValue);
		
		if (objValue != null) {
			checkFieldLength(objValue);
			checkRegex(objValue);
		}
			
		return true;
	}

	private void checkRegex(String objValue) {
		if(!StringUtils.hasText(regex))
			return;
		
		if (StringUtils.hasText(objValue)) {
			Pattern regPattern = Pattern.compile(regex, 2);
			Matcher matcher = regPattern.matcher(objValue);
			if (!matcher.find()) {
				log.error("{} is Invalid", fieldName);
				throw new InputValidationException(INPUT_FIELD_INVALID, fieldName);
			}
		}
	}
	
	private void checkFieldLength(String objValue) {
		if(!(objValue.length()>=min && objValue.length()<=max)){
			log.error("{} - Feild length mismatch", fieldName);
			throw new InputValidationException(INPUT_FIELD_LENGTH_INVALID, fieldName);
		}
	}
	
	private void checkNullOrEmpty(String objValue) {
		if (!StringUtils.hasText(objValue)) {
			log.error("{} is empty", fieldName);
			throw new InputValidationException(INPUT_FIELD_REQUIRED, fieldName);
		}
	}

	private String getValueAsString(Object value) {
		if (value == null || value.hashCode() == 0)
			return null;

		String strVal = null;
		if (value instanceof String) {
			strVal = (String) value;
		} else if (value instanceof Integer) {
			strVal = String.valueOf((Integer) value);
		} else if (value instanceof Long) {
			strVal = String.valueOf((Long) value);
		} else if (value instanceof Double) {
			strVal = String.valueOf((Double) value);
		} else if (value instanceof BigDecimal) {
			strVal = String.valueOf((BigDecimal) value);
		} else {
			return strVal;
		}

		if (strVal == null || strVal.trim().hashCode() == 0)
			return null;
		return strVal.trim();
	}

}
