package com.backend.jwt.dao.board;

import com.backend.jwt.dto.object.BoardListItem;
import com.backend.jwt.entity.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {


    Integer write(Board board);
    BoardListItem getBoard(Integer boardNumber);
    List<Board> getBoards();

    Integer increaseViewCount(Integer boardNumber);
}
