package com.Solution.CRM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Transactional
@Table(name = "Customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customerId_seq_gen")
    @SequenceGenerator(name = "customerId_seq_gen", sequenceName = "customerId_sequence", initialValue = 100, allocationSize = 1)
    private BigInteger id;

    @CreationTimestamp
    @Column(name = "createdTime", nullable = false)
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "customer", fetch =  FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Address> address = new ArrayList<>();
}