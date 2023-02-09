package com.example.modsentest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Organizer extends BaseEntity<Integer> {

    @Column
    private String name;

    @OneToMany(mappedBy ="organizer" , fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Event> events;
}

