package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class VersionedEntityPk implements Serializable {
    private static final long serialVersionUID = 6354232374624420251L;
    private Long id;
    private int version;

    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "VersionedEntityPk [id=" + id + ", version=" + version + "]";
    }

}
