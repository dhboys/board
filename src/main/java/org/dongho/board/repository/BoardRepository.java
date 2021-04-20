package org.dongho.board.repository;

import org.dongho.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // table이 아니라 entity 타입으로 명시 , # 대신 : 이용해서 parameter로 받은 것 활용
    // Board는 reply에 대한 정보가 없다 -> FK 위주로 관계를 주어서
    //  => but 조인 가능
   /* @Query("select b,count(r) from Board b left join Reply r on r.board = b " +
            "group by b")
    // Page<> -> 제네릭
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);*/

    @Query(value = "select b,w,count(r) from Board b " +
            "inner join b.writer w " +
            "left outer join Reply r on r.board = b " +
            "group by b"
             ,
             countQuery = "select count(b) from Board b")
        // Page<> -> 제네릭
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
}
