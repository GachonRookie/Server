package gachon.rookie.server.repository;

import gachon.rookie.server.entity.Club;
import gachon.rookie.server.entity.ClubApply;
import gachon.rookie.server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClubApplyRepository extends JpaRepository<ClubApply, Long> {

    Boolean existsByClubIdAndMemberId(@Param("clubId") Club clubId, @Param("memberId")Member memberId);

    Optional<ClubApply> findByMemberId(Long memberId);

}
