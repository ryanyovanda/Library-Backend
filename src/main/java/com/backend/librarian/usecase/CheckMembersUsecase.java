package com.backend.librarian.usecase;

import com.backend.librarian.domain.dto.MemberResponseDTO;
import com.backend.librarian.domain.entity.Member;

import java.util.List;

public interface CheckMembersUsecase {
    List<MemberResponseDTO> execute();
}
