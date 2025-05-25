package com.backend.librarian.repository;

import com.backend.librarian.domain.entity.Book;
import com.backend.librarian.domain.entity.BorrowRecord;
import com.backend.librarian.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByMemberAndReturnDateIsNull(Member member);

    Optional<BorrowRecord> findByMemberAndBookAndReturnDateIsNull(Member member, Book book);

    int countByMemberAndReturnDateIsNull(Member member);

    int countByBookAndReturnDateIsNull(Book book);

    List<BorrowRecord> findByReturnDateAfter(LocalDate date);
}
