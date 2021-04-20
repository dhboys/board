package org.dongho.board.repository;

import lombok.extern.log4j.Log4j2;
import org.dongho.board.entity.Board;
import org.dongho.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoards(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            // 1~100 중 random한 숫자
            int idx = (int)(Math.random() * 100) + 1;
            // 회원 만들기(Board에 writer에 넣을)
            Member member = Member.builder().email("user" + idx + "@aaa.com").build();
            Board board = Board.builder()
                    .title("TITLE.." + i)
                    .content("CONTENT" + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void testRead(){
        Optional<Board> result = boardRepository.findById(100L);
        log.info(result);

        if(result.isPresent()){
            Board board = result.get();
            log.info("===============================");
            log.info(board);
        } // end if
    }

    @Test
    public void testGetList(){
         Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
         Page<Board> result = boardRepository.findAll(pageable);
         result.getContent().forEach(i -> log.info(i));
    }

    @Test
    public void testBoardWithReplyCount(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(arr -> log.info(Arrays.toString(arr)));
    }
}
