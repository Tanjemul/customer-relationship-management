package com.Solution.CRM.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {
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
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String mobile;
    private String telephone;
}