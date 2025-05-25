package com.backend.librarian.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberResponseDTO {
    private Long id;
    private String code;
    private String name;
    private int borrowedCount;
}
