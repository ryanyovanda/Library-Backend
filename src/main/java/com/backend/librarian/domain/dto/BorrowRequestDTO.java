package com.backend.librarian.domain.dto;

import lombok.Data;

@Data
public class BorrowRequestDTO {
    private String memberCode;
    private String bookCode;
}
