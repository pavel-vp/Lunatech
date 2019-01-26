package com.lunatech.imdbq.model;

public class TypeCastResponse {
    private Boolean typeCasted;

    public TypeCastResponse(Boolean typeCasted) {
        this.typeCasted = typeCasted;
    }

    public TypeCastResponse() {
    }

    public Boolean getTypeCasted() {
        return typeCasted;
    }

    public void setTypeCasted(Boolean typeCasted) {
        this.typeCasted = typeCasted;
    }
}
