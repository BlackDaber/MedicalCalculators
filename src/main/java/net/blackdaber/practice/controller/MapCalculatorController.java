package net.blackdaber.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blackdaber.practice.data.AbstractDto;
import net.blackdaber.practice.data.CalculatorInfo;
import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.MapDto;
import net.blackdaber.practice.exception.ErrorMessage;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/map")
@RestController
public class MapCalculatorController {

    Map<CalculatorType, MedicalCalculatorService<?>> medicalCalculatorServices;

    @Operation(summary = "Gets calculator information", description = "Returns Map calculator information", tags = "info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalculatorInfo.class)))
    })
    @GetMapping(value = "/info", produces = {"application/json", "application/xml"})
    public ResponseEntity<CalculatorInfo> get() {
        return ResponseEntity.ok(new CalculatorInfo("""
                Калькулятор расчета среднего артериального давления по данным систолического и диастолического АД.
                Формула: Среднее артериальное давление (САД) = 1/3 САД + 2/3 * ДАД.
                """));
    }

    @Operation(summary = "Calculate result from your data",
            description = "Calculate Map result from your data",
            tags = "calculate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultInfo.class))),
            @ApiResponse(responseCode = "422",
                    description = "Validation exception",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))}
    )
    @SuppressWarnings("unchecked")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/result")
    public ResponseEntity<ResultInfo> result(@Valid @RequestBody MapDto dto) {
        var service = (MedicalCalculatorService<AbstractDto>) this.medicalCalculatorServices.get(CalculatorType.MAP);
        return new ResponseEntity<>(service.calculate(dto), HttpStatus.OK);
    }
}
