package com.example.session02_exercise05.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
    private Long id;
    private String type;
    private String description;
    private String status;
    private LocalDateTime timestamp;
}
