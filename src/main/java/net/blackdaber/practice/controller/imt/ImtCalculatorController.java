package net.blackdaber.practice.controller.imt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import net.blackdaber.practice.data.AbstractDto;
import net.blackdaber.practice.data.CalculatorInfo;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.exception.ErrorMessage;
import org.springframework.web.bind.annotation.RequestBody;

public interface ImtCalculatorController<T extends AbstractDto> {

    @Operation(summary = "Gets calculator information", description = "Returns Imt calculator information", tags = "info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalculatorInfo.class)))
    })
    CalculatorInfo get();

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
    ResultInfo result(@Valid @RequestBody T dto);
}
