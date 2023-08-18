package gachon.rookie.server.repository;

import gachon.rookie.server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
