package com.avatech.edi.mdm.repository;

import com.avatech.edi.model.bo.IBusinessObject;
import com.avatech.edi.common.data.List;

public interface BaseBOQueryRepository<M extends IBusinessObject> {

    List<M> fetch();

    M fetchByKey(Object key);

}
