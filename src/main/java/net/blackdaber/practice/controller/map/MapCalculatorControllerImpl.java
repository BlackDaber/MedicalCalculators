package net.blackdaber.practice.controller.map;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blackdaber.practice.data.CalculatorInfo;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.MapDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/map")
@RestController
public class MapCalculatorControllerImpl implements MapCalculatorController<MapDto> {

    MedicalCalculatorService<MapDto> medicalCalculatorServices;

    @GetMapping(value = "/info")
    public CalculatorInfo get() {
        return new CalculatorInfo("""
                Калькулятор расчета среднего артериального давления по данным систолического и диастолического АД.
                Формула: Среднее артериальное давление (САД) = 1/3 САД + 2/3 * ДАД.
                """);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/result")
    public ResultInfo result(@Valid @RequestBody MapDto dto) {
        return this.medicalCalculatorServices.calculate(dto);
    }
}
