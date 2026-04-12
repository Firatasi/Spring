package com.uzay.game.service;

import com.uzay.game.dto.request.GameRequest;
import com.uzay.game.dto.response.GameResponse;
import com.uzay.game.entity.Game;
import com.uzay.game.exception.GameNotFoundException;
import com.uzay.game.mapper.GameMapper;
import com.uzay.game.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final  GameRepository gameRepository;
    private final GameMapper gameMapper;
    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }


    public GameResponse addGame(GameRequest gameRequest) {
        Game game = gameMapper.toGame(gameRequest);
        Game savedGame = gameRepository.save(game);
        return gameMapper.toGameResponse(savedGame);
    }

    public GameResponse getGameById(Long id) {
        return gameMapper.toGameResponse(gameRepository.findById(id).orElseThrow(()-> new GameNotFoundException(HttpStatus.NOT_FOUND.value(),"Game not found")));
    }

    public List<GameResponse> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return gameMapper.toGameResponseList(games);
    }

    public GameResponse updateGame(Long id, GameRequest gameRequest) {
        Optional<Game> game = gameRepository.findById(id);

        Game gameOne = game.get();
        gameOne.setName(gameRequest.getName());
        gameOne.setCategory(gameRequest.getCategory());
        gameOne.setPrice(gameRequest.getPrice());
        Game savedGame = gameRepository.save(gameOne);
        return gameMapper.toGameResponse(savedGame);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

}
