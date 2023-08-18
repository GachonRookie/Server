package gachon.rookie.server.repository;

import gachon.rookie.server.entity.Club;
import gachon.rookie.server.entity.ClubReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubReportRepository extends JpaRepository<ClubReport, Long> {

    @Query("select cr from ClubReport cr where cr.clubId = :clubId and cr.status = 'ACTIVE'")
    Optional<List<ClubReport>> findReportListByClubId(Club clubId);
}
