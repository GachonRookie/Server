package gachon.rookie.server.repository;

import gachon.rookie.server.entity.ClubPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubPartRepository extends JpaRepository<ClubPart, Long> {
}
