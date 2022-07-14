package com.revature.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ers_reimbursement")
public class ERSReimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reimb_id", nullable = false)
    private Integer id;

    @Column(name = "reimb_amount", nullable = false)
    private Double amount;

    @Column(name = "reimb_submitted", nullable = false)
    private LocalDate dateSubmitted;

    @Column(name = "reimb_resolved")
    private LocalDate dateResolved;

    @Column(name = "reimb_description", length = 250)
    private String description;

    @Column(name = "reimb_receipt")
    private byte[] receipt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reimb_author", nullable = false)
    private ERSUser author;

    @ManyToOne
    @JoinColumn(name = "reimb_resolver")
    private ERSUser resolver;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reimb_status_id", nullable = false)
    private ERSReimbStatus ersReimbStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reimb_type_id", nullable = false)
    private ERSReimbType ersReimbType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public LocalDate getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(LocalDate dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public ERSUser getAuthor() {
        return author;
    }

    public void setAuthor(ERSUser author) {
        this.author = author;
    }

    public ERSUser getResolver() {
        return resolver;
    }

    public void setResolver(ERSUser resolver) {
        this.resolver = resolver;
    }

    public ERSReimbStatus getErsReimbStatus() {
        return ersReimbStatus;
    }

    public void setErsReimbStatus(ERSReimbStatus ersReimbStatus) {
        this.ersReimbStatus = ersReimbStatus;
    }

    public ERSReimbType getErsReimbType() {
        return ersReimbType;
    }

    public void setErsReimbType(ERSReimbType ersReimbType) {
        this.ersReimbType = ersReimbType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSReimbursement that = (ERSReimbursement) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(dateSubmitted, that.dateSubmitted) && Objects.equals(dateResolved, that.dateResolved) && Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt) && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver) && Objects.equals(ersReimbStatus, that.ersReimbStatus) && Objects.equals(ersReimbType, that.ersReimbType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, dateSubmitted, dateResolved, description, receipt, author, resolver, ersReimbStatus, ersReimbType);
    }

    @Override
    public String toString() {
        return "ERSReimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateSubmitted=" + dateSubmitted +
                ", dateResolved=" + dateResolved +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", ersReimbStatus=" + ersReimbStatus +
                ", ersReimbType=" + ersReimbType +
                '}';
    }
}