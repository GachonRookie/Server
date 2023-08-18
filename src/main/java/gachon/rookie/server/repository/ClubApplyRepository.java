package gachon.rookie.server.repository;

import gachon.rookie.server.entity.ClubApply;
import gachon.rookie.server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubApplyRepository extends JpaRepository<ClubApply, Long> {
    Optional<ClubApply> findByMemberId(Long memberId);
}
