package com.uzay.game.mapper;

import com.uzay.game.dto.request.GameRequest;
import com.uzay.game.dto.response.GameResponse;
import com.uzay.game.entity.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {
    Game toGame(GameRequest gameRequest);
    GameResponse toGameResponse(Game game);
    List<GameResponse> toGameResponseList(List<Game> games);
}
