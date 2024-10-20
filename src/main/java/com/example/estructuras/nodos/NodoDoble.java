package com.example.estructuras.nodos;

public class NodoDoble<T>
{
    /**
     * dato/informacion/objeto del nodo
     */
    public T dato;

    /**
     * el siguiente nodo
     */
    private NodoDoble<T> anterior;

    /**
     * el nodo anterior
     */
    private NodoDoble<T> siguiente;

    /**
     * contruye una nueva instancia
     * 
     * @param anterior anterior nodo
     * @param dato dato/informacion del nodo
     * @param siguiente siguiente nodo
     */
    public NodoDoble(NodoDoble<T> anterior, T dato, NodoDoble<T> siguiente)
    {
        this.dato = dato;
        this.setAnterior(anterior);
        this.setSiguiente(siguiente);
    }

    /**
     * contruye una nueva instancia con el siguiente nodo en null
     * 
     * @param anterior anterior nodo
     * @param dato dato/informacion del nodo
     */
    public NodoDoble(NodoDoble<T> anterior, T dato)
    {
        this(anterior, dato, null);
    }

    /**
     * contruye una nueva instancia con el anterior nodo en null
     * 
     * @param dato dato/informacion del nodo
     * @param siguiente siguiente nodo
     */
    public NodoDoble(T dato, NodoDoble<T> siguiente)
    {
        this(null, dato, siguiente);
    }

    /**
     * construye una nueva instancia con el anterior y siguiente nodo en null
     * 
     * @param dato dato/informacion del nodo
     */
    public NodoDoble(T dato)
    {
        this(null, dato, null);
    }

    /**
     * establece el nodo anterior a este
     * 
     * @param anterior el anterior nodo
     */
    public void setAnterior(NodoDoble<T> anterior)
    {
        this.anterior = anterior;
    }

    /**
     * establece el nodo siguiente a este
     * 
     * @param siguiente el siguiente nodo
     */
    public void setSiguiente(NodoDoble<T> siguiente)
    {
        this.siguiente = siguiente;
    }

    /**
     * obtiene el nodo anterior a este
     * 
     * @return nodo anterior
     */
    public NodoDoble<T> anterior()
    {
        return this.anterior;
    }

    /**
     * obtiene el nodo siguiente a este
     * 
     * @return siguiente nodo
     */
    public NodoDoble<T> siguiente()
    {
        return this.siguiente;
    }
}