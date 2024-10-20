package com.example.estructuras.listas;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

import com.example.estructuras.nodos.NodoSimple;

public class ListaSimple<E> implements Iterable<E>, Serializable
{
    /**
     * el primer nodo de la lista
     */
    private NodoSimple<E> primero;

    /**
     * el ultimo nodo de la lista
     */
    private NodoSimple<E> ultimo;

    /**
     * la cantidad de nodos en la lista
     */
    private int longitud;

    /**
     * construye una nueva instancia
     */
    public ListaSimple()
    {
        this.primero = null;
        this.ultimo = null;
        this.longitud = 0;
    }

    public static <E> ListaSimple<E> delArray(E[] elements)
    {
        ListaSimple<E> lista = new ListaSimple<>();

        for (int i = 0; i < elements.length; i++) {
            lista.agregar(elements[i]);
        }

        return lista;
    }

    /**
     * agrega un elemento al final
     * 
     * @param e el elemento a agregar
     */
    public void agregar(E e)
    {
        agregar(longitud, e);
    }

    /**
     * agrega un elemento en una posicion espeficia
     * 
     * @param posicion la posicion para el elemento
     * @param e el elemento a agregar
     */
    public void agregar(int posicion, E e)
    {
        if (! (posicion >= 0 && posicion <= longitud)) {
            throw new IndexOutOfBoundsException();
        }

        agregarEn(posicion, new NodoSimple<E>(e));
    }

    /**
     * agrega un elemento si la condicion es verdadera
     * 
     * @param condicion condicion a comprobar
     * @param e elemento a agregar
     * 
     * @return el elemento fue agregado
     */
    public boolean agregarSiCumple(boolean condicion, E e)
    {
        if (condicion == true) this.agregar(e);

        return condicion;
    }

    /**
     * agrega un elemento si la condicion es verdadera
     * 
     * @param condicion condicion a comprobar (el elemento acutal con otro de la lista)
     * @param e elemento a agregar
     * 
     * @return el elemento fue agregado
     */
    public boolean agregarSiCumple(BiPredicate<E, E> condicion, E e)
    {
        Iterator<E> it = iterator();
        boolean result = false;

        if (this.estaVacia()) {
            result = true;
        } else {
            while (it.hasNext() && result == false) {
                result = condicion.test(e, it.next());
            }
        }

        if (result) this.agregar(e);

        return result;
    }

    /**
     * obtiene el elmento de la posicion dada
     * 
     * @param posicion la posicion del elemento
     * 
     * @return el elemento en la posicion
     */
    public E obtener(int posicion)
    {
        if (! posicionValida(posicion)) {
            throw new NoSuchElementException();
        }

        return obtenerNodo(posicion).dato;
    }

    /**
     * remueve el elemento de la posicion dada
     * 
     * @param posicion la posicion del elemento
     */
    public void remover(int posicion)
    {
        if (! posicionValida(posicion)) {
            throw new NoSuchElementException();
        }

        removerNodo(posicion);
    }

    public void remover(E e)
    {
        NodoSimple<E> aux = primero;
        int index = 0;

        while (aux != null) {
            if (aux.dato.equals(e)) {
                if (aux == primero) {
                    primero = primero.siguiente();

                    if (longitud == 1) {
                        ultimo = primero;
                    }
                } else if (aux == ultimo) {
                    ultimo = obtenerNodo(longitud - 1);
                    ultimo.setSiguiente(null);

                    if (longitud == 1) {
                        primero = ultimo;
                    }
                } else {
                    NodoSimple<E> temp = obtenerNodo(index - 1);
                    temp.setSiguiente(temp.siguiente().siguiente());
                }

                longitud--;
            }

            index++;
            aux = aux.siguiente();
        }
    }

    /**
     * remueve todos los elementos de la lista
     */
    public void limpiar()
    {
        primero = null;
        longitud = 0;
    }

    /**
     * agrega un nodo en la primera posicion
     * 
     * @param nodo el nodo a agregar
     */
    private void agregarPrimero(NodoSimple<E> nodo)
    {
        if (longitud == 0) {
            primero = ultimo = nodo;
        } else {
            nodo.setSiguiente(primero);
            primero = nodo;
        }

        longitud++;
    }

    /**
     * agrega un nodo en la ultima posicion
     * 
     * @param nodo el nodo a agregar
     */
    private void agregarUltimo(NodoSimple<E> nodo)
    {
        if (longitud == 0) {
            primero = ultimo = nodo;
        } else {
            ultimo.setSiguiente(nodo);
            ultimo = nodo;
        }

        longitud++;
    }

    /**
     * agrega un nodo en una posicion especifica (debe ser valida)
     * 
     * @param posicion la posicion para el nodo
     * @param nodo el nodo a agregar
     */
    protected void agregarEn(int posicion, NodoSimple<E> nodo)
    {
        if (! (posicion >= 0 && posicion <= longitud)) return;

        if (posicion == 0) {
            agregarPrimero(nodo);
        } else if (posicion == longitud) {
            agregarUltimo(nodo);
        } else {
            NodoSimple<E> pre = obtenerNodo(posicion - 1);
            nodo.setSiguiente(pre.siguiente());
            pre.setSiguiente(nodo);
            longitud++;
        }
    }

    /**
     * obtiene el nodo de la posicion valida dada (no-safe)
     * 
     * @param posicion la posicion del nodo
     * 
     * @return el nodo
     */
    protected NodoSimple<E> obtenerNodo(int posicion)
    {
        NodoSimple<E> nodo = primero;

        for (int i = 0; i < posicion; i++) nodo = nodo.siguiente();

        return nodo;
    }

    /**
     * remueve un nodo en una dada posicion valida (no-safe)
     * 
     * @param posicion la posicion del nodo
     */
    protected void removerNodo(int posicion)
    {
        if (! posicionValida(posicion)) return;

        if (posicion == 0) {
            primero = primero.siguiente();
        } else {
            NodoSimple<E> pre = obtenerNodo(posicion - 1);
            pre.setSiguiente(pre.siguiente().siguiente());
        }

        longitud--;
    }

    /**
     * comprueba si una posicion es valida
     * 
     * @param posicion la posicion a comprobar
     * 
     * @return "true" si es valida o "false" en otro caso
     */
    public boolean posicionValida(int posicion)
    {
        return posicion >= 0 && posicion < longitud;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new ListaIterador(0);
        // return new SimpleIterator<E>(this, primero, 0);
    }

    /**
     * comprueba si no hay elementos
     * 
     * @return "true" si no hay elementos, "false" deo otra forma
     */
    public boolean estaVacia()
    {
        return longitud == 0;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray()
    {
        Object[] arr = new Object[longitud];
        NodoSimple<E> aux = primero;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = aux.dato;
            aux = aux.siguiente();
        }

        return (E[]) arr;
    }

    protected NodoSimple<E> primero()
    {
        return this.primero;
    }

    /**
     * obtiene la cantidad de elementos
     * 
     * @return cantidad de elementos
     */
    public int longitud()
    {
        return this.longitud;
    }


    /**
     * iterador propio de la clase
     */
    protected class ListaIterador implements Iterator<E>
    {
        private NodoSimple<E> siguiente;
        private int indice;

        public ListaIterador(int indice)
        {
            if (! posicionValida(indice)) {
                throw new NoSuchElementException();
            }

            this.siguiente = obtenerNodo(indice);
            this.indice = indice - 1;
        }

        @Override
        public boolean hasNext()
        {
            return indice < longitud() - 1;
        }

        @Override
        public E next()
        {
            E dato = this.siguiente.dato;
            this.siguiente = this.siguiente.siguiente();
            this.indice++;

            return dato;
        }
    }
}