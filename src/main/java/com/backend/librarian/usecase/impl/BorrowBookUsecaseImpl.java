package com.backend.librarian.usecase.impl;

import com.backend.librarian.domain.dto.BorrowRequestDTO;
import com.backend.librarian.domain.dto.BorrowResponseDTO;
import com.backend.librarian.domain.entity.Book;
import com.backend.librarian.domain.entity.BorrowRecord;
import com.backend.librarian.domain.entity.Member;
import com.backend.librarian.repository.BookRepository;
import com.backend.librarian.repository.BorrowRecordRepository;
import com.backend.librarian.repository.MemberRepository;
import com.backend.librarian.usecase.BorrowBookUsecase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowBookUsecaseImpl implements BorrowBookUsecase {
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public BorrowResponseDTO execute(BorrowRequestDTO request) {
        Member member = memberRepository.findByCode(request.getMemberCode())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Book book = bookRepository.findByCode(request.getBookCode())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        int borrowedCount = borrowRecordRepository.countByMemberAndReturnDateIsNull(member);
        if (borrowedCount >= 2) {
            throw new RuntimeException("Member cannot borrow more than 2 books");
        }

        int bookBorrowedCount = borrowRecordRepository.countByBookAndReturnDateIsNull(book);
        if (bookBorrowedCount >= 1) {
            throw new RuntimeException("Book is currently borrowed by another member");
        }

        BorrowRecord record = BorrowRecord.builder()
                .member(member)
                .book(book)
                .borrowDate(LocalDate.now())
                .build();
        borrowRecordRepository.save(record);

        return BorrowResponseDTO.builder()
                .memberCode(member.getCode())
                .bookCode(book.getCode())
                .borrowDate(record.getBorrowDate())
                .build();
    }

}
