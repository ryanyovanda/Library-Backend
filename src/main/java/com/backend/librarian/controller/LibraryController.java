package com.backend.librarian.controller;
import com.backend.librarian.domain.dto.*;
import com.backend.librarian.usecase.BorrowBookUsecase;
import com.backend.librarian.usecase.CheckBookUsecase;
import com.backend.librarian.usecase.CheckMembersUsecase;
import com.backend.librarian.usecase.ReturnBookUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Library API", description = "API for book lending system")
public class LibraryController {

    private final CheckBookUsecase checkBookUsecase;
    private final CheckMembersUsecase checkMembersUsecase;
    private final BorrowBookUsecase borrowBookUsecase;
    private final ReturnBookUsecase returnBookUsecase;

    @Operation(summary = "List all books with available stock")
    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getBooks() {
        return ResponseEntity.ok(checkBookUsecase.execute());
    }

    @Operation(summary = "List all members and their borrowed count")
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDTO>> getMembers() {
        return ResponseEntity.ok(checkMembersUsecase.execute());
    }

    @Operation(summary = "Borrow a book by member")
    @PostMapping("/borrow")
    public ResponseEntity<BorrowResponseDTO> borrowBook(@RequestBody BorrowRequestDTO request) {
        return ResponseEntity.ok(borrowBookUsecase.execute(request));
    }

    @Operation(summary = "Return a book by member")
    @PostMapping("/return")
    public ResponseEntity<ReturnResponseDTO> returnBook(@RequestBody ReturnRequestDTO request) {
        return ResponseEntity.ok(returnBookUsecase.execute(request));
    }
}
