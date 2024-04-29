package net.blackdaber.practice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blackdaber.practice.data.CalculatorInfo;
import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.ImtDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/imt")
@RestController
@Validated
public class ImtCalculatorController {

    Map<CalculatorType, MedicalCalculatorService> medicalCalculatorServices;

    @GetMapping("info")
    public CalculatorInfo get() {
        return new CalculatorInfo("""
                Этот калькулятор позволяет быстро и просто рассчитать индекс массы тела(ИМТ).
                Формула I=m/h*h, где: m — масса тела в килограммах; h — рост в метрах; измеряется в кг/м².
                Калькулятор рачитывает показатели в следующих интервалах: рост не более 300 см; вес не менее 10 кг.
                """);
    }

    @PostMapping("result")
    public ResultInfo result(@Validated @RequestBody ImtDto dto) {
        var service = this.medicalCalculatorServices.get(CalculatorType.IMT);
        return service.calculate(dto);
    }
}
