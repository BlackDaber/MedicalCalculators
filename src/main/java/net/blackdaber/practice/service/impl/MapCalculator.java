package net.blackdaber.practice.service.impl;

import net.blackdaber.practice.data.AbstractDto;
import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.MapDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service("mapCalculator")
public class MapCalculator implements MedicalCalculatorService {

    @Override
    public ResultInfo calculate(AbstractDto dto) {
        var mapDto = (MapDto) dto;
        var dad = (double) mapDto.getDad();
        var sad = (double) mapDto.getSad();

        var result = BigDecimal.valueOf(0.333 * sad + 0.666 * dad);
        if (result.compareTo(BigDecimal.valueOf(110)) > 0) {
            return new ResultInfo("%s Гипертензия".formatted(result.setScale(0, RoundingMode.HALF_UP)));
        }
        if (result.compareTo(BigDecimal.valueOf(70)) < 0) {
            return new ResultInfo(result.setScale(0, RoundingMode. HALF_UP) + " Гипотензия. Возможна гипоксия и ишемия тканей");
        }

        return new ResultInfo(result.setScale(0, RoundingMode. HALF_UP) + " Норма");
    }

    @Override
    public CalculatorType getCalculatorType() {
        return CalculatorType.MAP;
    }
}
