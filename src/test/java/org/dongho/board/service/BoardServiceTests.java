package org.dongho.board.service;

import lombok.extern.log4j.Log4j2;
import org.dongho.board.dto.BoardDTO;
import org.dongho.board.dto.PageRequestDTO;
import org.dongho.board.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("테스트")
                .content("테스트")
                .writerEmail("user10@aaa.com")
                .writerName("사용자10")
                .build();

        Long bno = boardService.register(boardDTO);
        log.info("BNO: "+bno );
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO , Object[]> result = boardService.getList(pageRequestDTO);

        result.getDtoList().forEach(boardDTO -> log.info(boardDTO));
    }
}
