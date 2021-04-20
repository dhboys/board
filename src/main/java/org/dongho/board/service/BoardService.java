package org.dongho.board.service;

import org.dongho.board.dto.BoardDTO;
import org.dongho.board.dto.PageRequestDTO;
import org.dongho.board.dto.PageResultDTO;
import org.dongho.board.entity.Board;
import org.dongho.board.entity.Member;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Long register(BoardDTO dto);
    // row하나를 DTO로 바꾸는 역할
    PageResultDTO<BoardDTO , Object[]> getList(PageRequestDTO pageRequestDTO);

    default Board dtoToEntity(BoardDTO dto){
       Member writer = Member.builder().email(dto.getWriterEmail()).build();

       Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(writer)
                .build();
       return board;
    }

    default BoardDTO entityToDTO(Board board , Member member , Long replyCount){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();
        return boardDTO;
    }
}
