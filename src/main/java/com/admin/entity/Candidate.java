package com.admin.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CANDIDATE")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int candidateId;

    private String candidateName;
    private String primarySkill;
    private String secondarySkill;
    private String qualification;
    private String designation;
    private String noticePeriod;
    private String location;

    @ManyToMany(mappedBy = "candidates", cascade = CascadeType.ALL)
    private List<Interview> interviews;
}

