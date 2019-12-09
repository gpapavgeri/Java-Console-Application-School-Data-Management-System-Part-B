
package jdbc_privateschool;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Georgia_Papavgeri
 */
public class Student {

    private int id;
    private String stFirstName;
    private String stLastName;
    private LocalDate stDateOfBirth;
    private Double stTuitionFees;
    private List<Course> courses;

    public Student(int id, String stFirstName, String stLastName, LocalDate stDateOfBirth, Double stTuitionFees) {
        this.id = id;
        this.stFirstName = stFirstName;
        this.stLastName = stLastName;
        this.stDateOfBirth = stDateOfBirth;
        this.stTuitionFees = stTuitionFees;
    }

    public Student(int id, String stFirstName, String stLastName) {
        this(id, stFirstName, stLastName, null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStFirstName() {
        return stFirstName;
    }

    public void setStFirstName(String stFirstName) {
        this.stFirstName = stFirstName;
    }

    public String getStLastName() {
        return stLastName;
    }

    public void setStLastName(String stLastName) {
        this.stLastName = stLastName;
    }

    public LocalDate getStDateOfBirth() {
        return stDateOfBirth;
    }

    public void setStDateOfBirth(LocalDate stDateOfBirth) {
        this.stDateOfBirth = stDateOfBirth;
    }

    public Double getStTuitionFees() {
        return stTuitionFees;
    }

    public void setStTuitionFees(Double stTuitionFees) {
        this.stTuitionFees = stTuitionFees;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student:" +
                "id=" + id +
                ", First Name='" + stFirstName + '\'' +
                ", Last Name='" + stLastName + '\'' +
                ", Date Of Birth=" + stDateOfBirth +
                ", Tuition Fees=" + stTuitionFees+ "\n";
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.stFirstName);
        hash = 29 * hash + Objects.hashCode(this.stLastName);
        hash = 29 * hash + Objects.hashCode(this.stDateOfBirth);
        hash = 29 * hash + Objects.hashCode(this.stTuitionFees);
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
        final Student other = (Student) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.stFirstName, other.stFirstName)) {
            return false;
        }
        if (!Objects.equals(this.stLastName, other.stLastName)) {
            return false;
        }
        if (!Objects.equals(this.stDateOfBirth, other.stDateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.stTuitionFees, other.stTuitionFees)) {
            return false;
        }
        return true;
    }
    
    
}
