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
import tn.iit.dto.request.match.MatchRequest;
import tn.iit.dto.response.match.MatchResponse;
import tn.iit.entity.Match;
import tn.iit.service.MatchService;

import java.util.List;

@RestController
@RequestMapping(value = "/match")
@AllArgsConstructor
public class MatchController {
    private MatchService matchService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Matchs",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get all Matchs",
            description = "All Matchs are returned"
    )
    @GetMapping()
    public ServerResponse<List<MatchResponse>> getAll() {
        return matchService.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match Founded",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Match not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get Match by id",
            description = "A Match is returned"
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<MatchResponse> getMatch(@PathVariable Integer id) {
        return matchService.getById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matchs Founded",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Match not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get Match by id match Day",
            description = "All Matchs is returned"
    )
    @GetMapping(value = "/ByMatchDay/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<List<MatchResponse>> getMatchByMatchDay(@PathVariable Integer id) {
        return matchService.getAllByMatchDayId(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Match Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Match already exists ",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Post a new Match",
            description = "A Match is created"
    )
    @PostMapping()
    public ServerResponse<MatchResponse> createMatch(@Valid @RequestBody MatchRequest matchRequest) {
        return matchService.create(matchRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Match not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Update Match",
            description = "A Match is updated"
    )
    @PutMapping("/{id}")
    public ServerResponse<MatchResponse> updateMatch(@RequestBody Match match, @PathVariable Integer id) {
        return matchService.update(match, id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match updated finished",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Match not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Update Match finished",
            description = "A Match is updated finished"
    )
    @GetMapping("/finishMatch/{id}")
    public ServerResponse<MatchResponse> updateFinishMatch(@PathVariable Integer id) {
        return matchService.updateFinishMatch(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Match deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Match not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Delete Match",
            description = "A Match is deleted"
    )
    @DeleteMapping("/{id}")
    public ServerResponse<MatchResponse> deleteMatch(@PathVariable Integer id) {
        return matchService.delete(id);
    }
}
