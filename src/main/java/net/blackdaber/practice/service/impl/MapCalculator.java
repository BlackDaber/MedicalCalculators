package net.blackdaber.practice.service.impl;

import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.MapDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MapCalculator implements MedicalCalculatorService<MapDto> {

    @Override
    public ResultInfo calculate(MapDto dto) {
        var dad = dto.getDbp();
        var sad = dto.getSbp();

        var result = BigDecimal.valueOf(0.333 * sad + 0.666 * dad);

        if (result.compareTo(BigDecimal.valueOf(110)) > 0) {
            return new ResultInfo("%s Гипертензия".formatted(result.setScale(0, RoundingMode.HALF_UP)));
        }
        if (result.compareTo(BigDecimal.valueOf(70)) < 0) {
            return new ResultInfo("%s Гипотензия. Возможна гипоксия и ишемия тканей".formatted(result.setScale(0, RoundingMode.HALF_UP)));
        }
        return new ResultInfo("%s Норма".formatted(result.setScale(0, RoundingMode.HALF_UP)));
    }

    @Override
    public CalculatorType getCalculatorType() {
        return CalculatorType.MAP;
    }
}
