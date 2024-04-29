package net.blackdaber.practice.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blackdaber.practice.data.CalculatorInfo;
import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.MapDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/map")
@RestController
@Validated
public class MapCalculatorController {

    Map<CalculatorType, MedicalCalculatorService> medicalCalculatorServices;

    @GetMapping("info")
    public CalculatorInfo get() {
        return new CalculatorInfo("""
                Калькулятор расчета среднего артериального давления по данным систолического и диастолического АД.
                Формула: Среднее артериальное давление (САД) = 1/3 САД + 2/3 * ДАД.
                """);
    }

    @PostMapping("result")
    public ResultInfo result(@Valid @RequestBody MapDto model) {
        var service = this.medicalCalculatorServices.get(CalculatorType.MAP);
        return service.calculate(model);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
