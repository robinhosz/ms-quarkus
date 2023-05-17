package io.github.robinhosz.exceptions;
/*
import jakarta.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;

import java.util.ArrayList;
import java.util.List;

public class ConstraintViolationResponse {

    private final List<ConstraintViolationImpl> violacoes = new ArrayList<>();

   *//* private ConstraintViolationResponse(ConstraintViolationException exception) {
        exception.getConstraintViolations().forEach(violations -> violacoes.add(ConstraintViolationImpl.of(violations)));
    }*//*

  *//*  public static ConstraintViolationResponse of(ConstraintViolationException exception) {
        return new ConstraintViolationResponse(exception);
    }*//*

    public List<ConstraintViolationImpl> getViolacoes() {
        return violacoes;
    }

}*/
