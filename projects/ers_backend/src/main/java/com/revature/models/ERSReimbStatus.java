package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="ers_reimbursement_status")
public class ERSReimbStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reimb_status_id", nullable = false)
    private int id;

    @Column(name = "reimb_status", nullable = false, length = 10)
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSReimbStatus that = (ERSReimbStatus) o;
        return id == that.id && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "ERSReimbStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
