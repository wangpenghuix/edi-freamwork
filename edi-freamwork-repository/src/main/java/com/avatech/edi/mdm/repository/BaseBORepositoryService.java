package com.avatech.edi.mdm.repository;

import com.avatech.edi.model.bo.IBusinessObject;

public abstract class BaseBORepositoryService<T extends IBusinessObject> {

    protected abstract void save(T bo) ;

    protected abstract void update(T bo) ;

    protected abstract void delete(T bo) ;

    protected abstract void callTranscation(T bo,String transType);

    public final void saveBO(T bo) {
        //bo.check();
        this.save(bo);
        this.callTranscation(bo,"A");
    }

    public final void updateBO(T bo){
        //bo.check();
        this.update(bo);
        this.callTranscation(bo,"U");
    }

    public final void deleteBO(T bo){
        this.callTranscation(bo,"D");
        this.delete(bo);
    }

}
