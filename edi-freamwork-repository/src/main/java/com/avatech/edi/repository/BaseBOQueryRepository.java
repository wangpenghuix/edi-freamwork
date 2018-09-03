package com.avatech.edi.repository;

import com.avatech.edi.bo.IBusinessObject;
import com.avatech.edi.data.List;

public interface BaseBOQueryRepository<M extends IBusinessObject> {

    List<M> fetch();

    M fetchByKey(Object key);

}
