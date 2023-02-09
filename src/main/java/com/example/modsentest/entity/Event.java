package com.example.modsentest.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity<Integer> {

    @Column
    private String title;

    @Column
    private String description = "";

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;
}

