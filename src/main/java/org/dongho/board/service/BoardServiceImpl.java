package org.dongho.board.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.dongho.board.dto.BoardDTO;
import org.dongho.board.dto.PageRequestDTO;
import org.dongho.board.dto.PageResultDTO;
import org.dongho.board.entity.Board;
import org.dongho.board.entity.Member;
import org.dongho.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {

       Board board = dtoToEntity(dto);
       boardRepository.save(board);

       return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        // pageResultDTO 만들어야한다 -> Fn(Object[]을 BoardDTO로 바꾸는) 필요
        Function<Object[],BoardDTO> fn = (arr -> entityToDTO(
                (Board)arr[0] ,
                (Member)arr[1],
                (Long)arr[2])
        );
        return new PageResultDTO<>(result , fn);
    }
}
