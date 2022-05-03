package com.tvd12.example.jta;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.xa.XAResource;

public class MyTransaction implements Transaction {

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
        SecurityException, IllegalStateException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean delistResource(XAResource xaRes, int flag) throws IllegalStateException, SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean enlistResource(XAResource xaRes) throws RollbackException, IllegalStateException, SystemException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getStatus() throws SystemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void registerSynchronization(Synchronization sync)
        throws RollbackException, IllegalStateException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void rollback() throws IllegalStateException, SystemException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {
        // TODO Auto-generated method stub

    }

}
