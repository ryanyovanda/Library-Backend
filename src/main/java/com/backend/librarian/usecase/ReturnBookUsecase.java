package com.backend.librarian.usecase;

import com.backend.librarian.domain.dto.ReturnRequestDTO;
import com.backend.librarian.domain.dto.ReturnResponseDTO;

public interface ReturnBookUsecase {
    ReturnResponseDTO execute(ReturnRequestDTO request);
}
