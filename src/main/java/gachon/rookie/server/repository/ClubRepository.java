package gachon.rookie.server.repository;

import gachon.rookie.server.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    /**
     * 동아리 중에서 Active한 것 중 조건에 맞는 것 하나 가져오기
     * */
    @Query(value = "select c from Club c where c.clubId = :clubId and c.status = 'ACTIVE'")
    Optional<Club> findByIdWhereStatusActive(Long clubId);
}