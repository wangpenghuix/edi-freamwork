package com.avatech.edi.model.bo;

public abstract class BusinessObject<T extends IBusinessObject> implements IBusinessObject {

    private final String NO = "N";

    private String isDelete;

    @Override
    public String getIsDelete() {
        return isDelete;
    }

    @Override
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public BusinessObject() {
        this.initial();
    }

    @Override
    public void initial() {
        this.isDelete = NO;
    }

    @Override
    public void check() {

    }
}
