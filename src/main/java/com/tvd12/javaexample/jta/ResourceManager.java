package com.tvd12.javaexample.jta;

/**
 * The Interface ResourceManager.
 * 
 * @author vivek.mishra
 */
public interface ResourceManager
{

    /**
     * Do commit.
     */
    void doCommit();

    /**
     * Do rollback.
     */
    void doRollback();
}