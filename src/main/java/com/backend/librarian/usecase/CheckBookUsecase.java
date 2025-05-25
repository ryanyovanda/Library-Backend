package com.backend.librarian.usecase;

import com.backend.librarian.domain.dto.BookResponseDTO;

import java.util.List;


public interface CheckBookUsecase {
    List<BookResponseDTO> execute();
}
