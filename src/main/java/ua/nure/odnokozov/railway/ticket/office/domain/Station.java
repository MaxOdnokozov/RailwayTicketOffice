package ua.nure.odnokozov.railway.ticket.office.domain;

import java.util.Map;

public class Station extends Entity {

    private static final long serialVersionUID = 4275938680325289253L;

    private Map<Long, String> names;

    public Station() {
    }

    public Station(Map<Long, String> names) {
        this.names = names;
    }
    
    public Station(long id, Map<Long, String> names) {
        super(id);
        this.names = names;
    }

    public Map<Long, String> getNames() {
        return names;
    }

    public void setNames(Map<Long, String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Station [id=").append(getId())
                .append(", names=").append(names)
                .append("]").toString();
    }
}


