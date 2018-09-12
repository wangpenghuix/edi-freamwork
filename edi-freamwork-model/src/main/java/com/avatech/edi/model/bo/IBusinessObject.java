package com.avatech.edi.model.bo;

public interface IBusinessObject extends IBORules,IBOLogics{

    String getIsDelete();

    void setIsDelete(String isDelete);

}
