package tatar.mackshchim.sm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tatar.mackshchim.sm.models.Post;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByStateOrderByPostTime(Pageable pageable, Post.State state);

    @Query(value = "SELECT post.id, post.header, post.post_time, post.state, post.text, post.author_id " +
            "FROM post JOIN user_relation " +
            "ON (author_id = user_relation.related_user_id) " +
            "WHERE ((user_relation.relation_type IN (\'FRIEND\', \'FOLLOWING\')) " +
            "AND (user_relation.relating_user_id = :userID))" +
            "ORDER BY post.post_time",
            nativeQuery = true)
    Page<Post> findAllFriendsPostsByPageOfUser(Pageable pageable, @Param("userID") Long userID);
}
