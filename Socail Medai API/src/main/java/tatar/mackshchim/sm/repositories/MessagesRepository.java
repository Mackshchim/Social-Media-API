package tatar.mackshchim.sm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tatar.mackshchim.sm.models.Message;

import java.util.Optional;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM message WHERE " +
            "(sender_id = :firstUserID OR sender_id = :secondUserID) " +
            "AND (recipient_id = :firstUserID OR recipient_id = :secondUserID) " +
            "ORDER BY send_time",
    nativeQuery = true)
    Optional<Page<Message>> findAllMessagesBetween(Pageable pageable,
                                                   @Param("firstUserID") Long firstUserID,
                                                   @Param("secondUserID") Long secondUserID);
}
