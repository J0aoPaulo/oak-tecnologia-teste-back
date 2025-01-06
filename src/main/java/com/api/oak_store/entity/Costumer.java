package com.api.oak_store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "costumers")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID costumerId;

    @Column(nullable = false)
    @NonNull
    String name;

    @Column(nullable = false)
    @NonNull
    String email;

    @Column(nullable = false)
    @NonNull
    String password;
}
