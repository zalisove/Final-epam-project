package com.zalisove.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Root of all entities which have identifier field.
 *
 * @author O.S.Kyrychenko
 */

public abstract class Entity implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 8463772981696398434L;
    @JsonIgnore
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
