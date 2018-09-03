package com.avatech.edi.data;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class ArrayList<E> extends java.util.ArrayList<E> implements List<E>  {

    public ArrayList() {
        super();
    }

    public ArrayList(Collection<? extends E> arg0) {
        super(arg0);
    }

    public ArrayList(int arg0) {
        super(arg0);
    }

    @Override
    public E firstOrDefault() {
        if (this.size() > 0)
            return this.get(0);
        return null;
    }

    @Override
    public E lastOrDefault() {
        if (this.size() > 0)
            return this.get(this.size() - 1);
        return null;
    }

    @Override
    public E firstOrDefault(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        for (int i = 0; i < this.size(); i++) {
            E item = this.get(i);
            if (item == null)
                continue;
            if (filter.test(item))
                return item;
        }
        return null;
    }

    @Override
    public E lastOrDefault(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        for (int i = this.size() - 1; i >= 0; i--) {
            E item = this.get(i);
            if (item == null)
                continue;
            if (filter.test(item))
                return item;
        }
        return null;
    }

    public boolean addAll(E[] c) {
        if (c != null) {
            for (E item : c) {
                this.add(item);
            }
        }
        return true;
    }

    public boolean addAll(Iterable<? extends E> c) {
        if (c != null) {
            for (E item : c) {
                this.add(item);
            }
        }
        return true;
    }
}
