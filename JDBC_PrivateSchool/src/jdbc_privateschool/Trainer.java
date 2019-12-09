
package jdbc_privateschool;

import java.util.Objects;


/**
 *
 * @author Georgia_Papavgeri
 */
public class Trainer {

    private int id;
    private String trFirstName;
    private String trLastName;
    private String trSubject;

    public Trainer(int id, String trFirstName, String trLastName, String trSubject) {
        this.id = id;
        this.trFirstName = trFirstName;
        this.trLastName = trLastName;
        this.trSubject = trSubject;
    }
    
    public Trainer(int id, String trFirstName, String trLastName) {
        this.id = id;
        this.trFirstName = trFirstName;
        this.trLastName = trLastName;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrFirstName() {
        return trFirstName;
    }

    public void setTrFirstName(String trFirstName) {
        this.trFirstName = trFirstName;
    }

    public String getTrLastName() {
        return trLastName;
    }

    public void setTrLastName(String trLastName) {
        this.trLastName = trLastName;
    }

    public String getTrSubject() {
        return trSubject;
    }

    public void setTrSubject(String trSubject) {
        this.trSubject = trSubject;
    }

    @Override
    public String toString() {
        return "Trainer: " +
                "id=" + id +
                ", First Name='" + trFirstName + '\'' +
                ", Last Name='" + trLastName + '\'' +
                ", Subject='" + trSubject + "' \n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.trFirstName);
        hash = 61 * hash + Objects.hashCode(this.trLastName);
        hash = 61 * hash + Objects.hashCode(this.trSubject);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trainer other = (Trainer) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.trFirstName, other.trFirstName)) {
            return false;
        }
        if (!Objects.equals(this.trLastName, other.trLastName)) {
            return false;
        }
        if (!Objects.equals(this.trSubject, other.trSubject)) {
            return false;
        }
        return true;
    }
    
    
}

