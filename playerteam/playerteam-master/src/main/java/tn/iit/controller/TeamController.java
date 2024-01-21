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
import tn.iit.dto.request.TeamRequest;
import tn.iit.dto.response.TeamResponse;
import tn.iit.entity.Team;
import tn.iit.service.TeamService;

@RestController
@RequestMapping(value = "/team")
@AllArgsConstructor
public class TeamController {

	private TeamService mTeamService;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste des equipes", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class)) }),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Récupérer toutes les equipes ", description = "Tous les equipes sont renvoyés")
	@GetMapping()
	public ServerResponse<List<TeamResponse>> getAll() {
		return mTeamService.getAll();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipe trouvé", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Identifiant invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Equipe non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Récupérer l'Equipe par identifiant", description = "L'Equipe est renvoyé")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServerResponse<TeamResponse> getTeam(@PathVariable int id) {
		return mTeamService.getById(id);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipe trouvé", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Nom invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Equipe non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Récupérer l'Equipe par name", description = "L'Equipe est renvoyé")
	@GetMapping(value = "/nom/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServerResponse<TeamResponse> getTeamByName(@PathVariable String name) {
		return mTeamService.getTeamByName(name);
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Equipe créé", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class)) }),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Equipe existe déja", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Créer un nouveau Equipe ", description = "Une Equipe a été créé")
	@PostMapping()
	public ServerResponse<TeamResponse> createTeam(@Valid @RequestBody TeamRequest teamRequest) {
		return mTeamService.create(teamRequest);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipe mise à jour", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Identifiant invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Equipe non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Mettre à jour l'Equipe", description = "Une Equipe a été mise à jour")
	@PutMapping("/{id}")
	public ServerResponse<TeamResponse> updateTeam(@RequestBody Team team, @PathVariable int id) {
		System.out.println(team);
		return mTeamService.update(team, id);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Equipe supprimé", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Identifiant invalide fourni", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Equipe non trouvé", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé", content = @Content(mediaType = "application/json")), })
	@Operation(summary = "Supprimer l'Equipe", description = "Une Equipe a été supprimé")
	@DeleteMapping("/{id}")
	public ServerResponse<TeamResponse> deleteTeam(@PathVariable int id) {
		return mTeamService.delete(id);
	}

}
