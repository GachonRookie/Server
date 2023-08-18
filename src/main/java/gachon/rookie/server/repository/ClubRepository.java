package gachon.rookie.server.repository;

import ch.qos.logback.core.net.server.Client;
import gachon.rookie.server.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Club findClubByClubId(Long id);
}
