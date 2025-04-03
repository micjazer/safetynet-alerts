package com.safetynet.alerts.controller;

import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.FloodDTO;
import com.safetynet.alerts.dto.StationCoverageDTO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Tag(name = "Fire station API")
@RestController
public class FireStationController {

    private final FireStationService fireStationService;

    @Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }



    @Operation(summary = "Creates a fire station", description = "Creates a fire station with his number and the address.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fire station has been created"),
            @ApiResponse(responseCode = "400", description = "Fire station with specified data are not valid.",
                    content = {@Content(schema = @Schema(implementation = Map.class))}),
            @ApiResponse(responseCode = "409", description = "Fire station with specified number and address already exists.",
                    content = {@Content(schema = @Schema(implementation = Error.class))})
    })
    @PostMapping("/firestation")
    public ResponseEntity<Void> create(@RequestBody @Valid FireStation fireStation) {
        Logger.info("Request to create a new fire station : {}", fireStation);
        return fireStationService.create(fireStation);
    }


    @Operation(summary = "Updates a fire station", description = "Updates a fire station by the address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fire station has been updated"),
            @ApiResponse(responseCode = "400", description = "Fire station with specified data are not valid.",
                    content = {@Content(schema = @Schema(implementation = Map.class))}),
            @ApiResponse(responseCode = "404", description = "Fire station with specified lastName and firstName was not found.",
                    content = {@Content(schema = @Schema(implementation = Error.class))})
    })
    @PutMapping("/firestation")
    public ResponseEntity<Void> update(@RequestBody @Valid FireStation fireStation) {
        Logger.info("Request to update a fire station : {}", fireStation);
        return fireStationService.update(fireStation);
    }


    @Operation(summary = "Deletes a fire station", description = "Deletes a fire station by the number or the address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fire station has been deleted"),
            @ApiResponse(responseCode = "404", description = "Fire station with specified number or address was not found.",
                    content = {@Content(schema = @Schema(implementation = Error.class))})
    })
    @DeleteMapping("/firestation")
    public ResponseEntity<Void> delete(@RequestBody @Valid FireStation fireStation) {
        Logger.info("Request to delete a fire station : {}", fireStation);
        return fireStationService.delete(fireStation);
    }


    @Operation(summary = "Get the list of persons coverage by a station", description = "Get the list of persons coverage by a station number. The list contains the persons information (lastName, firstName, address, phone) and the number of children and adults.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of persons for the specified station number.",
                    content = {@Content(schema = @Schema(implementation = StationCoverageDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Station with specified station number was not found.",
                    content = {@Content(schema = @Schema(implementation = Error.class))})
    })
    @GetMapping("/firestation")
    public StationCoverageDTO getPersonsStationCoverage(
            @RequestParam("stationnumber")
            @Parameter(description = "The station number where we want to obtain the people covered by this one", example = "1")
            int stationNumber) {
        return fireStationService.getPersonsStationCoverage(stationNumber);
    }


    @Operation(summary = "Get all homes for each stations", description = "Get all homes for each stations order by station number and group by address. The information of each resident contains the last name, first name, the phone number, the age and the medical record.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of information for specified stations numbers.",
                    content = {@Content(schema = @Schema(implementation = FloodDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Specified stations numbers were not found.",
                    content = {@Content(schema = @Schema(implementation = Error.class))})
    })
    @GetMapping("/flood/stations")
    public List<FloodDTO> getHomesByStations(
            @RequestParam("stations")
            @Parameter(description = "The numbers of the stations for which we want to obtain residents' information", example = "[1, 2]")
            List<Integer> stations) {
        Logger.info("Request to get homes by this one or several stations : {}", stations);
        return fireStationService.getHomesByStations(stations);
    }


    @Operation(summary = "Get all resident phone numbers for station coverage", description = "Get all resident phone numbers for station coverage.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of resident phone numbers for station coverage.",
                    content = {@Content(schema = @Schema(implementation = Set.class))}),
            @ApiResponse(responseCode = "404", description = "Specified station number was not found.",
                    content = {@Content(schema = @Schema(implementation = Error.class))})
    })
    @GetMapping("/phonealert")
    public Set<String> getPersonsPhonesByStation(
            @RequestParam("firestation")
            @Parameter(description = "The station number where we want to obtain all the telephone numbers of the people covered by it", example = "1")
            int stationNumber) {
        Logger.info("Request to get telephone numbers of persons covered by the station : {}", stationNumber);
        return fireStationService.getPersonsPhonesByStation(stationNumber);
    }


    @Operation(summary = "Get all resident and the fire station by a specified address", description = "Get all resident and the fire station by a specified address. The list contains the persons information (last name, first name, phone, age, the medical record), and the fire station number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of resident and the fire station by a specified address.",
                    content = {@Content(schema = @Schema(implementation = FireDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Specified address was not found.",
                    content = {@Content(schema = @Schema(implementation = Error.class))})
    })
    @GetMapping("/fire")
    public FireDTO getPersonsAndStationByAddress(
            @RequestParam("address")
            @Parameter(description = "The address from which you wish to obtain information on the inhabitants and the station number", example = "\"1509 Culver St\"")
            String address) {
        Logger.info("Request to get persons and the station by the address : {}", address);
        return fireStationService.getPersonsAndStationByAddress(address);
    }
}
