package com.orisinterview.cpiwebserver.models;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.Annotation;

public class CpiResponseBody implements ResponseBody {
    private int cpiValue;
    private String notes;

    public CpiResponseBody(int cpiValue) {
        this(cpiValue, "");
    }

    public CpiResponseBody(int cpiValue, String notes) {
        this.cpiValue = cpiValue;
        this.notes = notes;
    }

    public int getCpiValue() {
        return cpiValue;
    }

    public void setCpiValue(int cpiValue) {
        this.cpiValue = cpiValue;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
