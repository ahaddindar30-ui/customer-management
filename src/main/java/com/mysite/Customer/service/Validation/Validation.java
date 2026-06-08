package com.mysite.Customer.service.Validation;

import com.mysite.Customer.service.exception.ValidationException;
@FunctionalInterface
public interface Validation<T> {
    void validate(T t)throws ValidationException;
}
