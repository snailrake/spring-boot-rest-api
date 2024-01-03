package com.webapp.testapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "formats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Format {

    // TODO: Переделать в enum с пятью самыми популярными форматами

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
