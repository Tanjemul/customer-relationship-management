package com.Solution.CRM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Transactional
@Table(name = "Address")
@EntityListeners(AuditingEntityListener.class)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "addressId_seq_gen")
    @SequenceGenerator(name = "addressId_seq_gen", sequenceName = "addressId_sequence", initialValue = 100, allocationSize = 1)
    private BigInteger id;

    @CreationTimestamp
    @Column(name = "createdTime", nullable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "lastUpdatedTime")
    private LocalDateTime lastUpdatedTime;

    @Column(name = "deactivatedTime")
    private LocalDateTime deactivatedTime;

    @Column(name = "deletedTime")
    private LocalDateTime deletedTime;

    @Column(nullable = false)
    private String addressLine1;
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    private String state;

    @Column(nullable = false)
    private String zipCode;

    private String specialMark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Lead lead;

}
