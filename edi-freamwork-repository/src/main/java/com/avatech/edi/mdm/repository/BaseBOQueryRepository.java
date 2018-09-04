package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.IBusinessObject;
import com.avatech.edi.mdm.data.List;

public interface BaseBOQueryRepository<M extends IBusinessObject> {

    List<M> fetch();

    M fetchByKey(Object key);

}
