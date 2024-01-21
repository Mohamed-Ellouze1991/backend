package tn.iit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.iit.core.ServerResponse;
import tn.iit.dto.request.match.*;
import tn.iit.dto.response.match.*;
import tn.iit.service.DetailsMatchService;

import java.util.List;

@RestController
@RequestMapping(value = "/details-match")
@AllArgsConstructor
public class DetailsMatchController {

    private DetailsMatchService mDetailsMatchService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All scorer of home team in a specific match",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get all scorer of home team in a specific match",
            description = "All scorer of home team in a specific match are returned"
    )
    @GetMapping(value = "/all-scorer-home-team-of-match/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<List<ScorerHomeTeamResponse>> getAllScorerHomeTeamOfMatch(@PathVariable Integer id) {
        return mDetailsMatchService.getAllScorerHomeTeamOfMatch(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All scorer of away team in a specific match",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get all scorer of away team in a specific match",
            description = "All scorer of away team in a specific match are returned"
    )
    @GetMapping(value = "/all-scorer-away-team-of-match/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<List<ScorerAwayTeamResponse>> getAllScorerAwayTeamOfMatch(@PathVariable Integer id) {
        return mDetailsMatchService.getAllScorerAwayTeamOfMatch(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All substitution of home team in a specific match",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get all substitution of home team in a specific match",
            description = "All substitution of home team in a specific match are returned"
    )
    @GetMapping(value = "/all-substitution-home-team-of-match/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<List<SubstitutionHomeTeamResponse>> getAllSubstitutionHomeTeamOfMatch(@PathVariable Integer id) {
        return mDetailsMatchService.getAllSubstitutionHomeTeamOfMatch(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All substitution of away team in a specific match",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get all substitution of away team in a specific match",
            description = "All substitution of away team in a specific match are returned"
    )
    @GetMapping(value = "/all-substitution-away-team-of-match/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<List<SubstitutionAwayTeamResponse>> getAllSubstitutionAwayTeamOfMatch(@PathVariable Integer id) {
        return mDetailsMatchService.getAllSubstitutionAwayTeamOfMatch(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Scorer of home team in match Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Id of home team or match not exist",
                    content = @Content(mediaType = "application/json")),

    })
    @Operation(summary = "Post a new scorer of home team in match",
            description = "A scorer of home team in match is created"
    )
    @PostMapping(value = "/add-scorer-home-in-match")
    public ServerResponse<ScorerHomeTeamResponse> createScorerHomeTeeam(@Valid @RequestBody ScorerHomeTeamRequest scorerHomeTeamRequest) {
        return mDetailsMatchService.createScorerHomeTeeam(scorerHomeTeamRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Scorer of away team in match Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Id of away team or match not exist",
                    content = @Content(mediaType = "application/json")),

    })
    @Operation(summary = "Post a new scorer of away team in match",
            description = "A scorer of away team in match is created"
    )
    @PostMapping(value = "/add-scorer-away-in-match")
    public ServerResponse<ScorerAwayTeamResponse> createScorerAwayTeeam(@Valid @RequestBody ScorerAwayTeamRequest scorerAwayTeamRequest) {
        return mDetailsMatchService.createScorerAwayTeeam(scorerAwayTeamRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Substitution of home team in match Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Id of home team or match or one of the players not exist",
                    content = @Content(mediaType = "application/json")),

    })
    @Operation(summary = "Post a new substitution of home team in match",
            description = "A substitution of home team in match is created"
    )
    @PostMapping(value = "/add-substitution-home-in-match")
    public ServerResponse<SubstitutionHomeTeamResponse> createSubstitutionHomeTeeam(@Valid @RequestBody SubstitutionHomeTeamRequest substitutionHomeTeamRequest) {
        return mDetailsMatchService.createSubstitutionHomeTeeam(substitutionHomeTeamRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Substitution of away team in match Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Id of away team or match or one of the players not exist",
                    content = @Content(mediaType = "application/json")),

    })
    @Operation(summary = "Post a new substitution of away team in match",
            description = "A substitution of home away in match is created"
    )
    @PostMapping(value = "/add-substitution-away-in-match")
    public ServerResponse<SubstitutionAwayTeamResponse> createSubstitutionAwayTeeam(@Valid @RequestBody SubstitutionAwayTeamRequest substitutionAwayTeamRequest) {
        return mDetailsMatchService.createSubstitutionAwayTeeam(substitutionAwayTeamRequest);
    }
}
