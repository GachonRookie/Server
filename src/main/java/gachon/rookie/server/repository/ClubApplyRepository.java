package gachon.rookie.server.repository;

import gachon.rookie.server.entity.ApplyStatus;
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

    // In your ClubApplyRepository interface
    @Query("SELECT COUNT(ca) FROM ClubApply ca JOIN ca.clubId c WHERE c.clubId = :clubId AND ca.gen = :gen")
    int countByClubIdAndGen(@Param("clubId") Long clubId, @Param("gen") Integer gen);

    @Query("SELECT COUNT(ca) FROM ClubApply ca WHERE ca.clubId = :clubId AND ca.gen = :gen AND ca.applyStatus = :applyStatus")
    int countByClubIdAndGenAndApplyStatus(@Param("clubId") Long clubId, @Param("gen") Integer gen, @Param("applyStatus") ApplyStatus applyStatus);

    ClubApply findByApplyId(Long id);
}
