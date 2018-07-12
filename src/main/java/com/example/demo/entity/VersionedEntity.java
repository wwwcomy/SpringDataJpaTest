package com.example.demo.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author wwwcomy
 * @date 2018/07/12
 */
@Entity
public class VersionedEntity {

    private VersionedEntityPk pk;
    private String name;
    private String description;

    @EmbeddedId
    public VersionedEntityPk getPk() {
        return pk;
    }

    public void setPk(VersionedEntityPk pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
