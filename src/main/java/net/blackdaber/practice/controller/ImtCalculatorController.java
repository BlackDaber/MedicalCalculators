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
import net.blackdaber.practice.data.impl.ImtDto;
import net.blackdaber.practice.exception.ErrorMessage;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/imt")
@RestController
public class ImtCalculatorController {

    Map<CalculatorType, MedicalCalculatorService<?>> medicalCalculatorServices;

    @Operation(summary = "Gets calculator information", description = "Returns Imt calculator information", tags = "info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalculatorInfo.class)))
    })
    @GetMapping(value = "/info")
    public CalculatorInfo get() {
        return new CalculatorInfo("""
                Этот калькулятор позволяет быстро и просто рассчитать индекс массы тела(ИМТ).
                Формула I=m/h*h, где: m — масса тела в килограммах; h — рост в метрах; измеряется в кг/м².
                Калькулятор рачитывает показатели в следующих интервалах: рост не более 300 см; вес не менее 10 кг.
                """);
    }

    @Operation(summary = "Calculate result from your data",
            description = "Calculate Imt result from your data",
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
    public ResultInfo result(@Valid @RequestBody ImtDto dto) {
        var service = (MedicalCalculatorService<AbstractDto>) this.medicalCalculatorServices.get(CalculatorType.IMT);
        return service.calculate(dto);
    }
}
