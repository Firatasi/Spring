package com.uzay.client.controller;

import com.uzay.client.dto.Game;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@RestController
public class ClientController {

    private final RestClient restClient;
    public ClientController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/all-games")
    public ResponseEntity<?> getAllGames() {
        ResponseEntity<List<Game>> entity = restClient
                .get()
                .uri(URI.create("/getAllGame"))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Game>>() {
                });
        return entity;
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<?> getGame(@PathVariable Long id) {
        ResponseEntity<Game> entity = restClient
                .get()
                .uri(URI.create("/getById/{id}" + id))
                .retrieve()
                .toEntity(Game.class);
        return entity;
    }

    @PostMapping("/game")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        ResponseEntity<Game> entity = restClient
                .post()
                .uri(URI.create("/api/game/addGame"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(game)
                .retrieve()
                .toEntity(Game.class);
        return entity;
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody Game game) {
        ResponseEntity<Game> entity = restClient
                .put()
                .uri(URI.create("/api/game/update/{id}" + id))
                .contentType(MediaType.APPLICATION_JSON)
                .body(game)
                .retrieve()
                .toEntity(Game.class);
        return entity;
    }

    @DeleteMapping
    public void deleteGame(@PathVariable Long id) {
        restClient
                .delete()
                .uri(URI.create("/api/game/delete/{id}" + id))
                .retrieve()
                .toBodilessEntity();
    }
}
