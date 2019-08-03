package com.avatech.edi.model.bo;

public interface IBODocument extends IBusinessObject {

    Long getDocEntry();

    void setDocEntry(Long docEntry);
}
