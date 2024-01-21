package tn.iit.controller;

import tn.iit.core.ServerResponse;
import tn.iit.dto.request.SeasonRequest;
import tn.iit.dto.response.SeasonResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.iit.entity.Season;
import tn.iit.service.SeasonService;

import java.util.List;

@RestController

@RequestMapping(value = "/season")
@AllArgsConstructor
public class SeasonController {
    private SeasonService mSeasonService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of seasons",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SeasonResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get all seasons",
            description = "All Seasons are returned"
    )
    @GetMapping()
    public ServerResponse<List<SeasonResponse>> getAll() {
        return mSeasonService.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Season Founded",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SeasonResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Season not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Get Season by id",
            description = "A Season is returned"
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<SeasonResponse> getSeason(@PathVariable String id) {
        return mSeasonService.getById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Season Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SeasonResponse.class))}),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Season already exists ",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Post a new Season",
            description = "A Season is created"
    )
    @PostMapping()
    public ServerResponse<SeasonResponse> createSeason(@Valid @RequestBody SeasonRequest seasonRequest) {
        System.out.println(seasonRequest);
        return mSeasonService.create(seasonRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Season updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SeasonResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Season not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Update Season",
            description = "A Season is updated"
    )
    @PutMapping("/{id}")
    public ServerResponse<SeasonResponse> updateSeason(@RequestBody Season season, @PathVariable String id) {
        return mSeasonService.update(season, id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Season deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SeasonResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Season not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed",
                    content = @Content(mediaType = "application/json")),
    })
    @Operation(summary = "Delete Season",
            description = "A Season is deleted"
    )
    @DeleteMapping("/{id}")
    public ServerResponse<SeasonResponse> deleteSeason(@PathVariable String id) {
        return mSeasonService.delete(id);
    }
}
