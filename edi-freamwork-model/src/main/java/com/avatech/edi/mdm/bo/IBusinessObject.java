package com.avatech.edi.mdm.bo;

public interface IBusinessObject extends IBORules,IBOLogics{

    String getIsDelete();

    void setIsDelete(String isDelete);

}
