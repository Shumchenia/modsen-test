package com.example.modsentest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place extends BaseEntity<Integer> {

    @Column
    private String name;

    @Column
    private String adress;

    @OneToMany(mappedBy = "place")
    @ToString.Exclude
    private List<Event> events;
}

