package com.example.estructuras.nodos;

public class NodoSimple<T>
{
    /**
     * dato/informacion/objeto del nodo
     */
    public T dato;

    /**
     * el siguiente nodo
     */
    private NodoSimple<T> siguiente;

    /**
     * construye una nueva instancia
     * 
     * @param dato valor/informacion para el nodo
     * @param siguiente siguiente nodo
     */
    public NodoSimple(T dato, NodoSimple<T> siguiente)
    {
        this.dato = dato;
        this.setSiguiente(siguiente);
    }

    /**
     * construye una nueva instancia 
     * 
     * @param dato valor/informacion para el nodo
     */
    public NodoSimple(T dato)
    {
        this(dato, null);
    }

    /**
     * obtiene el siguiente nodo
     * 
     * @return el siguiente nodo o null
     */
    public NodoSimple<T> siguiente()
    {
        return this.siguiente;
    }

    /**
     * establece cual es el siguiente nodo
     * 
     * @param nodo el nodo que sera el siguiente
     */
    public void setSiguiente(NodoSimple<T> nodo)
    {
        this.siguiente = nodo;
    }
}