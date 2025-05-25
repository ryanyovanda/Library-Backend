package com.backend.librarian.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReturnResponseDTO {
    private String memberCode;
    private String bookCode;
    private LocalDate returnDate;
    private boolean penalized;
    private LocalDate penaltyUntil;
}