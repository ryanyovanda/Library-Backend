package com.backend.librarian.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookResponseDTO {
    private String code;
    private String title;
    private String author;
    private int availableStock;
}
