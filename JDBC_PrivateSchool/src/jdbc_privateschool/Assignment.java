
package jdbc_privateschool;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Georgia_Papavgeri
 */
public class Assignment {

    private int id;
    private String title;
    private String description;
    private LocalDateTime subDateTime;
    private String oralMark;
    private String totalMark;

    public Assignment(int id, String title, String description, LocalDateTime subDateTime, String oralMark, String totalMark) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }
    
    public Assignment(int id, String title, String description, LocalDateTime subDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }

    public String getOralMark() {
        return oralMark;
    }

    public void setOralMark(String oralMark) {
        this.oralMark = oralMark;
    }

    public String getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(String totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "Assignment:" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", subDateTime=" + subDateTime + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.subDateTime);
        hash = 79 * hash + Objects.hashCode(this.oralMark);
        hash = 79 * hash + Objects.hashCode(this.totalMark);
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
        final Assignment other = (Assignment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.oralMark, other.oralMark)) {
            return false;
        }
        if (!Objects.equals(this.totalMark, other.totalMark)) {
            return false;
        }
        if (!Objects.equals(this.subDateTime, other.subDateTime)) {
            return false;
        }
        return true;
    }
    
    
}
