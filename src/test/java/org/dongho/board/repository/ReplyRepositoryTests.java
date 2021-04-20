package org.dongho.board.repository;

import org.dongho.board.entity.Board;
import org.dongho.board.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;
    @Test
    public void insertReplies(){
        IntStream.rangeClosed(1,300).forEach(i -> {
            long idx = (long)(Math.random() * 100) + 1;
            Board board = Board.builder().bno(idx).build();

            Reply reply = Reply.builder().replyer("replyer" + (i % 10))
                    .text("댓글..." + idx)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });
    }
}
