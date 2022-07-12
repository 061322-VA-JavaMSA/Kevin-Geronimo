package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ers_reimbursement_type")
public class ERSReimbType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reimb_type_id", nullable = false)
    private Integer id;

    @Column(name = "reimb_type", nullable = false, length = 10)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSReimbType that = (ERSReimbType) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "ERSReimbType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}