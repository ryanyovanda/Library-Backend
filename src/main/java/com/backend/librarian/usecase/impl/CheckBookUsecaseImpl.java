package com.backend.librarian.usecase.impl;

import com.backend.librarian.domain.dto.BookResponseDTO;
import com.backend.librarian.domain.entity.Book;
import com.backend.librarian.repository.BookRepository;
import com.backend.librarian.repository.BorrowRecordRepository;
import com.backend.librarian.usecase.CheckBookUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckBookUsecaseImpl implements CheckBookUsecase {
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    @Override
    public List<BookResponseDTO> execute() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(book -> {
                    int borrowed = borrowRecordRepository.countByBookAndReturnDateIsNull(book);
                    return BookResponseDTO.builder()
                            .code(book.getCode())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .availableStock(book.getStock() - borrowed)
                            .build();
                }).collect(Collectors.toList());
    }
}
