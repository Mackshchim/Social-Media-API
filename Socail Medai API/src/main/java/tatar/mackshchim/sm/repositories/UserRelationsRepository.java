package tatar.mackshchim.sm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tatar.mackshchim.sm.models.UserRelation;

import java.util.Optional;

@Repository
public interface UserRelationsRepository extends JpaRepository<UserRelation, Long> {
    Optional<UserRelation> findByRelatingUserIdAndRelatedUserId(Long relatingUserID, Long relatedUserID);


}
