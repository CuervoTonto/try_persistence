package com.example.contracts;

import java.io.Serializable;

public interface IModel<E> extends Serializable
{
    /**
     * the model data to store on persistence
     */
    public String[] toPersistence();

    /**
     * fill model using persistence data 
     */
    public void fromPersistence(String[] data);
}