package com.github.transformeli.desafiospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesDTO {
    private Long productId;
    private String name;
    private Integer quantity;
}
