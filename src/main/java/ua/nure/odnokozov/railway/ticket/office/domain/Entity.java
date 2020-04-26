package ua.nure.odnokozov.railway.ticket.office.domain;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -6512438658840436180L;

    private long id;
    
    public Entity() {
    }
    
    public Entity(long id) {
        this.id = id;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
