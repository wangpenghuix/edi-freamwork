package com.avatech.edi.bo;

public interface IBusinessObject extends IBORules,IBOLogics{

    String getIsDelete();

    void setIsDelete(String isDelete);

}
