package com.example.kairo.listoflistsback.framework.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError {

    private Type type;

    private String message;

    @JsonIgnore
    private Exception exception;

    public enum Type {
        ERROR, WARNING, INFO
    }

}
