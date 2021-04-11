package com.tvd12.example.jta;

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