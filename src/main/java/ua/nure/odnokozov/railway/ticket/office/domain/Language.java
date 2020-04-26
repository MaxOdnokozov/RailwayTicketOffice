package ua.nure.odnokozov.railway.ticket.office.domain;

import java.util.Objects;

public class Language extends Entity {

    private static final long serialVersionUID = 9174523176807936818L;

    private String label;

    public Language(long id) {
        super(id);
    }
    
    public Language(String label) {
        this.label= label;
    }
    
    public Language(long id, String label) {
        super(id);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Language other = (Language) obj;
        return Objects.equals(label, other.label);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Language [id=").append(getId())
                .append(", label=").append(label)
                .append("]").toString();
    }
}
