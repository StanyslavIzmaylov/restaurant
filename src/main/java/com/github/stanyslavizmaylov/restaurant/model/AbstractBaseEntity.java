package com.github.stanyslavizmaylov.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.stanyslavizmaylov.restaurant.HasId;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseEntity implements HasId {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "global_seq")
    @Hidden
    protected Integer id;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
