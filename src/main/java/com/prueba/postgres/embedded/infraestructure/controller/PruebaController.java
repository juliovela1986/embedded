package com.prueba.postgres.embedded.infraestructure.controller;



import com.prueba.postgres.embedded.application.exception.NotFoundException;
import com.prueba.postgres.embedded.application.service.impl.PruebaServiceImpl;
import com.prueba.postgres.embedded.infraestructure.dto.GlobalErrorDto;
import com.prueba.postgres.embedded.infraestructure.dto.PaginationFeaturesDto;
import com.prueba.postgres.embedded.infraestructure.dto.PruebaDto;
import com.prueba.postgres.embedded.application.exception.ControllerException;
import com.prueba.postgres.embedded.application.exception.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/configuration/prueba")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PruebaController {

    private final PruebaServiceImpl pruebaServiceImpl;

    public PruebaController(PruebaServiceImpl pruebaServiceImpl) {
        this.pruebaServiceImpl = pruebaServiceImpl;
    }

    @Operation(summary = "List all pruebas", description = "Function responsible for listing all pruebas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of pruebas", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PruebaDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GlobalErrorDto.class)))})
    @GetMapping(value = "/listAll")
    public ResponseEntity<Object> getAllPrueba(PaginationFeaturesDto paginationFeatureDto) {
        try {
            return new ResponseEntity<>(pruebaServiceImpl.getAllPrueba(paginationFeatureDto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    @Operation(summary = "Prueba update", description = "Function responsible for updating the prueba")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Prueba successfully updated", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Prueba cannot be update because it does not exist",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GlobalErrorDto.class)))})
    @PutMapping(value = "/update")
    public ResponseEntity<Void> updatePrueba(@RequestBody PruebaDto pruebaDto) {
        try {
            pruebaServiceImpl.updatePrueba(pruebaDto);
        } catch (ValidationException e) {
            throw new ValidationException(e);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Prueba insertion", description = "Function responsible for inserting a new prueba")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Prueba inserted successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GlobalErrorDto.class))),
            @ApiResponse(responseCode = "409", description = "The inserted prueba is already registered within the application",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalErrorDto.class)))})
    @PostMapping(value = "/insert")
    public ResponseEntity<Object> insertPrueba(@RequestBody PruebaDto pruebaDto) {
        try {
            pruebaServiceImpl.insertPrueba(pruebaDto);
        } catch (ValidationException e) {
            throw new ValidationException(e);
        } catch (ControllerException e) {
            throw new ControllerException(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a prueba", description = "Function responsible for deleting a prueba by passing the id and defaultPrueba")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "209", description = "Prueba successfully deleted", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GlobalErrorDto.class)))})
    @DeleteMapping(value = "/delete/{idPrueba}/{defaultPrueba}")
    public ResponseEntity<Object> deletePrueba(@PathVariable("idPrueba") Long idPrueba, @PathVariable("defaultPrueba") Boolean defaultPrueba) {
        try {
            pruebaServiceImpl.deletePrueba(idPrueba, defaultPrueba);
        } catch (Exception e) {
            throw new ValidationException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
