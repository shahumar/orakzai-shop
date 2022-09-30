package org.orakzai.lab.shop.domain.business.generic.model;

import org.hibernate.Hibernate;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;

public abstract class SalesManagerEntity<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>> implements Serializable, Comparable<E> {
    private static final long serialVersionUID = -3988499137919577054L;

    public static final Collator DEFAULT_STRING_COLLATOR = Collator.getInstance(Locale.ENGLISH);

    static {
        DEFAULT_STRING_COLLATOR.setStrength(Collator.PRIMARY);
    }

    public abstract K getId();

    public abstract void setId(K id);

    public boolean isNew() {
        return getId() == null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if(Hibernate.getClass(obj) != Hibernate.getClass(this)) return false;
        SalesManagerEntity<K, E> entity = (SalesManagerEntity<K, E>) obj;
        K id = entity.getId();
        if (id == null)  return false;
        return id.equals(this.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        K id = getId();
        hash = 31 * hash + ((id==null)? 0 : id.hashCode());
        return hash;
    }

    @Override
    public int compareTo(E o) {
        if (this == o) return 0;
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("entity.")
                .append(Hibernate.getClass(this).getSimpleName())
                .append("<")
                .append(getId())
                .append("-")
                .append(super.toString())
                .append(">");
        return str.toString();
    }
}
