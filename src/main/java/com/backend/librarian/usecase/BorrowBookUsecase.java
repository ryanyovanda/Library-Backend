package com.backend.librarian.usecase;

import com.backend.librarian.domain.dto.BorrowRequestDTO;
import com.backend.librarian.domain.dto.BorrowResponseDTO;

public interface BorrowBookUsecase {
    BorrowResponseDTO execute(BorrowRequestDTO borrowRequestDTO);

}
