package com.avatech.edi.mdm.repository;

import com.avatech.edi.mdm.bo.IBusinessObject;

public abstract class BOOperationRepositoryWithTranscation<M extends IBusinessObject> implements BaseBOOperationRepository<M>{

    private final static String ADD = "A";
    private final static String UPDATE = "U";
    private final static String DELETE = "D";


    public abstract void callTranscation(M model,String opType);

    /**
     * save model
     * @param model
     */
    public final void saveWithTranscation(M model){
        model.check();
        this.save(model);
        this.callTranscation(model,ADD);
    }

    /**
     * update model
     * @param model
     */
    public final void updateWithTranscation(M model){
        model.check();
        this.update(model);
        this.callTranscation(model,UPDATE);
    }

    /**
     * delete model
     * @param model
     */
    public final void deleteBO(M model){
        this.callTranscation(model,DELETE);
        this.delete(model);
    }


}
