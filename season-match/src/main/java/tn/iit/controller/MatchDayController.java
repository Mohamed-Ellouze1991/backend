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
import tn.iit.dto.request.MatchDayRequest;
import tn.iit.dto.request.SeasonRequest;

import tn.iit.dto.response.MatchDayResponse;


import tn.iit.entity.MatchDay;
import tn.iit.service.MatchDayService;

import java.util.List;

@RestController
@RequestMapping(value = "/matchDay")
@AllArgsConstructor
public class MatchDayController {

    private MatchDayService mMatchDayService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of MatchDays",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchDayResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get all MatchDays",
            description = "All MatchDays are returned"
    )
    @GetMapping()
    public ServerResponse<List<MatchDayResponse>> getAll() {
        return mMatchDayService.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MatchDay Founded",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchDayResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "MatchDay not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get MatchDay by id",
            description = "A MatchDay is returned"
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<MatchDayResponse> getMatchDay(@PathVariable Integer id) {
        return mMatchDayService.getById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "MatchDay Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchDayResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "MatchDay already exists ",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Post a new MatchDay",
            description = "A MatchDay is created"
    )
    @PostMapping()
    public ServerResponse<MatchDayResponse> createMatchDay(@Valid @RequestBody MatchDayRequest matchDayRequest) {
        System.out.println(matchDayRequest);
        return mMatchDayService.create(matchDayRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MatchDay updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchDayResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "MatchDay not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Update MatchDay",
            description = "A MatchDay is updated"
    )
    @PutMapping("/{id}")
    public ServerResponse<MatchDayResponse> updateMatchDay(@RequestBody MatchDay matchDay, @PathVariable Integer id) {
        return mMatchDayService.update(matchDay, id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MatchDay deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchDayResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "MatchDay not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Delete MatchDay",
            description = "A MatchDay is deleted"
    )
    @DeleteMapping("/{id}")
    public ServerResponse<MatchDayResponse> deleteMatchDay(@PathVariable Integer id) {
        return mMatchDayService.delete(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of MatchDay of season",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchDayResponse.class))}),
            @ApiResponse(responseCode = "400", description = "season n'a pas de MatchDay",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "season non trouvé",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Methode n'est pas autorisé",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Récupérer toutes les MatchDay d'un season",
            description = "Tous les MatchDay d'un season sont renvoyés")
    @GetMapping(value = "/getAllBySeason/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<List<MatchDayResponse>> getAllBySeason(@PathVariable String id) {
        System.out.println(id);
        return mMatchDayService.getAllBySeason(id);
    }


}
