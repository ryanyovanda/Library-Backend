package com.backend.librarian.domain.dto;

import lombok.Data;

@Data
public class ReturnRequestDTO {
    private String memberCode;
    private String bookCode;
}