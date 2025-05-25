package com.backend.librarian.usecase.impl;

import com.backend.librarian.domain.dto.MemberResponseDTO;
import com.backend.librarian.domain.entity.Member;
import com.backend.librarian.repository.BookRepository;
import com.backend.librarian.repository.BorrowRecordRepository;
import com.backend.librarian.repository.MemberRepository;
import com.backend.librarian.usecase.CheckMembersUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckMemberUsecaseImpl implements CheckMembersUsecase {
    private final MemberRepository memberRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    @Override
    public List<MemberResponseDTO> execute() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(member -> {
                    int borrowed = borrowRecordRepository.countByMemberAndReturnDateIsNull(member);
                    return MemberResponseDTO.builder()
                            .code(member.getCode())
                            .name(member.getName())
                            .borrowedCount(borrowed)
                            .build();
                }).collect(Collectors.toList());

    }
}
