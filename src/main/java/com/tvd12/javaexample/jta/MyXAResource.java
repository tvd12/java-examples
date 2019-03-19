package com.tvd12.javaexample.jta;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class MyXAResource implements XAResource {

	@Override
	public void commit(Xid xid, boolean onePhase) throws XAException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end(Xid xid, int flags) throws XAException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forget(Xid xid) throws XAException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTransactionTimeout() throws XAException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSameRM(XAResource xares) throws XAException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int prepare(Xid xid) throws XAException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Xid[] recover(int flag) throws XAException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollback(Xid xid) throws XAException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setTransactionTimeout(int seconds) throws XAException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void start(Xid xid, int flags) throws XAException {
		// TODO Auto-generated method stub
		
	}

}
