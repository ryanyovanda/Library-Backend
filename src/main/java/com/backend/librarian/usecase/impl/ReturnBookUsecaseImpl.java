package com.backend.librarian.usecase.impl;

import com.backend.librarian.domain.dto.ReturnRequestDTO;
import com.backend.librarian.domain.dto.ReturnResponseDTO;
import com.backend.librarian.domain.entity.Book;
import com.backend.librarian.domain.entity.BorrowRecord;
import com.backend.librarian.domain.entity.Member;
import com.backend.librarian.repository.BookRepository;
import com.backend.librarian.repository.BorrowRecordRepository;
import com.backend.librarian.repository.MemberRepository;
import com.backend.librarian.usecase.ReturnBookUsecase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ReturnBookUsecaseImpl implements ReturnBookUsecase {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    @Override
    @Transactional
    public ReturnResponseDTO execute(ReturnRequestDTO request) {
        Member member = memberRepository.findByCode(request.getMemberCode())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Book book = bookRepository.findByCode(request.getBookCode())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BorrowRecord record = borrowRecordRepository.findByMemberAndBookAndReturnDateIsNull(member, book)
                .orElseThrow(() -> new RuntimeException("This member did not borrow this book"));

        LocalDate today = LocalDate.now();
        record.setReturnDate(today);

        long days = ChronoUnit.DAYS.between(record.getBorrowDate(), today);

        boolean penalized = false;
        LocalDate penaltyUntil = null;

        borrowRecordRepository.save(record);

        return ReturnResponseDTO.builder()
                .memberCode(member.getCode())
                .bookCode(book.getCode())
                .returnDate(today)
                .penalized(penalized)
                .penaltyUntil(penaltyUntil)
                .build();
    }
}