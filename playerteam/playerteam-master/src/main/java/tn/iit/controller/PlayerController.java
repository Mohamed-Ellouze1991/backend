package tn.iit.controller;

import java.util.List;


import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import tn.iit.core.ServerResponse;
import tn.iit.dto.request.PlayerRequest;
import tn.iit.dto.request.PlayerRequestPOST;
import tn.iit.dto.response.PlayerResponse;
import tn.iit.entity.Player;
import tn.iit.service.PlayerService;

@RestController
@RequestMapping(value = "/player")
@AllArgsConstructor
public class PlayerController {

	private PlayerService mPlayerService;

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Liste des joueurs", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponse.class)) }),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Récupérer touts les joueurs ", description = "Tous les joueurs sont renvoyés")
	@GetMapping()
	public ServerResponse<List<PlayerResponse>> getAll() {
		return mPlayerService.getAll();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste des joueurs d'une equipe", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Equipe n'a pas de joueurs", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Equipe non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Récupérer toutes les joueurs d'une equipe", description = "Tous les cjoueurs d'une equipe sont renvoyés")
	@GetMapping(value = "/getAllByTeam/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServerResponse<List<PlayerResponse>> getAllByTeam(@PathVariable int id) {
		return mPlayerService.getAllByTeam(id);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Joueur trouvé", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Identifiant invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Joueur non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Récupérer le Joueur par identifiant", description = "Le Joueur est renvoyé")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServerResponse<PlayerResponse> getPlayer(@PathVariable int id) {
		return mPlayerService.getById(id);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Joueurs d'une equipe trouvés", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Identifiant invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Joueurs non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Récupérer les Joueurs d'une equipe", description = "Les Joueurs d'une equipe sont renvoyés")
	@GetMapping(value = "/playerByTeam/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServerResponse<List<PlayerResponse>> getPlayerByTeam(@PathVariable int id) {
		return mPlayerService.getAllByTeam(id);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Joueur créé", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponse.class)) }),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Compte existe déja", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Créer un nouveau Joueur ", description = "Un Joueur a été créé")
	@PostMapping()
	public ServerResponse<PlayerResponse> createPlayer(@Valid @RequestBody PlayerRequestPOST playerRequest) {
		return mPlayerService.create(playerRequest);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Joueur mise à jour", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Identifiant invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Joueur non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Mettre à jour le Joueur", description = "Un Joueur a été mise à jour")
	@PutMapping("/{id}")
	public ServerResponse<PlayerResponse> updatePlayer(@RequestBody Player player, @PathVariable int id) {
		System.out.println(player);
		return mPlayerService.update(player, id);
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Joueur supprimé", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Identifiant invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Joueur non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Supprimer le Joueur", description = "Un Joueur a été supprimé")
	@DeleteMapping("/{id}")
	public ServerResponse<PlayerResponse> deletePlayer(@PathVariable int id) {
		return mPlayerService.delete(id);
	}

}
