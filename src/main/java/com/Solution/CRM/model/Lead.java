package com.Solution.CRM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "Lead")
@EntityListeners(AuditingEntityListener.class)
public class Lead extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "leadId_seq_gen")
    @SequenceGenerator(name = "leadId_seq_gen", sequenceName = "leadId_sequence", initialValue = 100, allocationSize = 1)
    private BigInteger id;

    @OneToMany(mappedBy = "lead", fetch =  FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Address> address = new ArrayList<>();
}
