package com.example.estructuras.listas;

import java.util.Iterator;

import com.example.estructuras.nodos.NodoDoble;

public class ListaDobleCircular<E> extends ListaDoble<E>
{
    @Override
    protected void agregarEn(int posicion, NodoDoble<E> nodo)
    {
        if (posicion == 0) {
            enlazarNodoPrimero(nodo);
        } else if (posicion == longitud) {
            enlazarNodoUltimo(nodo);
        } else {
            NodoDoble<E> prev = obtenerNodo(posicion - 1);
            NodoDoble<E> prox = prev.siguiente();
            nodo.setAnterior(prev);
            nodo.setSiguiente(prox);
            prev.setSiguiente(nodo);
            prox.setAnterior(nodo);
        }

        longitud += 1;
    }
    
    private void enlazarNodoPrimero(NodoDoble<E> nodo)
    {
        if (longitud == 0) {
            primero = ultimo = nodo;
            nodo.setSiguiente(nodo);
            nodo.setAnterior(nodo);
        } else {
            nodo.setAnterior(ultimo);
            nodo.setSiguiente(primero);
            ultimo.setSiguiente(nodo);
            primero.setAnterior(nodo);
            primero = nodo;
        }
    }

    private void enlazarNodoUltimo(NodoDoble<E> nodo)
    {
        if (longitud == 0) primero = ultimo = nodo;

        primero.setAnterior(nodo);
        ultimo.setSiguiente(nodo);

        if (longitud != 0) {
            nodo.setSiguiente(primero);
            nodo.setAnterior(ultimo);
            ultimo = nodo;
        }
    }

    // iteradores -------------------------------------------------------------

    @Override
    public Iterator<E> iterator()
    {
        return this.iteradorCircular(0);
    }

    @Override
    public Iterator<E> iteradorInverso()
    {
        return this.iteradorCircular(longitud - 1);
    }

    public Iterator<E> iteradorCircular(int inicio, int recorridos, boolean reversa)
    {
        return new IteradorCircular(inicio, recorridos, reversa);
    }

    public Iterator<E> iteradorCircular(int inicio)
    {
        return this.iteradorCircular(inicio, 1, false);
    }

    // clases -----------------------------------------------------------------

    private class IteradorCircular implements Iterator<E>
    {
        private NodoDoble<E> inicio;
        private NodoDoble<E> actual;
        private boolean reversa;

        /**
         * contador para los recorridos restantes
         */
        private int recorridos;

        public IteradorCircular(int inicio, int reccoridos, boolean reversa)
        {
            this.inicio = obtenerNodo(inicio);
            this.actual = this.inicio;
            this.recorridos = reccoridos;
            this.reversa = reversa;
        }

        public boolean tieneRecorridos()
        {
            return this.recorridos > 0;
        }

        @Override
        public boolean hasNext()
        {
            return actual != null && (tieneRecorridos() || actual != inicio);
        }

        @Override
        public E next()
        {
            if (this.actual.equals(this.inicio)) {
                this.recorridos -= 1;
            }

            E elemento = this.actual.dato;
            this.actual = reversa ? this.actual.anterior() : this.actual.siguiente();

            return elemento;
        }
    }
}