package com.uzay.game.controller;

import com.uzay.game.dto.request.GameRequest;
import com.uzay.game.dto.response.GameResponse;
import com.uzay.game.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/game")
@RestController
public class GameController {

    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/addGame")
    public ResponseEntity<GameResponse> saveGame(@RequestBody GameRequest gameRequest) {
        GameResponse gameResponse = gameService.addGame(gameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameResponse);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable Long id) {
        GameResponse gameResponse = gameService.getGameById(id);
        return ResponseEntity.status(HttpStatus.OK).body(gameResponse);
    }

    @GetMapping("/getAllGame")
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<GameResponse> gameResponse = gameService.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(gameResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GameResponse> updateGame(@PathVariable Long id, @RequestBody GameRequest gameRequest) {
        GameResponse gameResponse = gameService.updateGame(id, gameRequest);
        return ResponseEntity.status(HttpStatus.OK).body(gameResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

}
