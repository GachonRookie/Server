package gachon.rookie.server.repository;

import gachon.rookie.server.entity.Club;
import gachon.rookie.server.entity.ClubRecruit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRecruitRepository extends JpaRepository<ClubRecruit, Long> {

    @Query(value = "select cr from ClubRecruit cr where cr.clubId = :clubId and cr.status = 'ACTIVE' order by cr.createdAt desc")
    Optional<List<ClubRecruit>> findByClubId(
           @Param("clubId") Club clubId,
           Pageable limitOne
    );

    @Query("SELECT cr FROM ClubRecruit cr ORDER BY cr.recruitEndDate ASC")
    Optional<List<ClubRecruit>> findAllOrderByRecruitEndDate();

    @Query("SELECT cr FROM ClubRecruit cr WHERE cr.clubId = :clubId AND cr.gen = :gen")
    ClubRecruit findByClubIdAndGen(@Param("clubId") Long clubId, @Param("gen") Integer gen);


}
