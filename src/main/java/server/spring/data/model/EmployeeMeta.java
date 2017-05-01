package server.spring.data.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Ilya Ivanov
 */
@Embeddable
@XmlRootElement
public class EmployeeMeta {
    @Transient
    @XmlTransient
    private Long id;

    @Column(name = "firstName")
    @XmlElement(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    @XmlElement(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    @XmlElement(name = "lastName")
    private String lastName;

    protected EmployeeMeta() {}

    public EmployeeMeta(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + middleName + " " + lastName;
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        Employee employee = (Employee) parent;
        this.id = ((Employee) parent).getId();
    }
}