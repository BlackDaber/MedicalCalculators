package net.blackdaber.practice.controller.imt;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blackdaber.practice.data.CalculatorInfo;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.ImtDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/imt")
@RestController
public class ImtCalculatorControllerImpl implements ImtCalculatorController<ImtDto> {

    MedicalCalculatorService<ImtDto> medicalCalculatorServices;

    @Override
    @GetMapping(value = "/info")
    public CalculatorInfo get() {
        return new CalculatorInfo("""
                Этот калькулятор позволяет быстро и просто рассчитать индекс массы тела(ИМТ).
                Формула I=m/h*h, где: m — масса тела в килограммах; h — рост в метрах; измеряется в кг/м².
                Калькулятор рачитывает показатели в следующих интервалах: рост не более 300 см; вес не менее 10 кг.
                """);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/result")
    public ResultInfo result(@Valid @RequestBody ImtDto dto) {
        return this.medicalCalculatorServices.calculate(dto);
    }
}
