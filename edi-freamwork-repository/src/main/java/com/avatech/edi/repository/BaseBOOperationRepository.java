package com.avatech.edi.repository;

import com.avatech.edi.bo.IBusinessObject;

public interface BaseBOOperationRepository<M extends IBusinessObject> {

    void save(M model);

    void update(M model);

    void delete(M model);
}
