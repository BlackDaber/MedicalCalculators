package net.blackdaber.practice.service.impl;

import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.ImtDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ImtCalculator implements MedicalCalculatorService<ImtDto> {

    @Override
    public ResultInfo calculate(ImtDto dto) {
        var height = (double) dto.getHeight() / 100;
        var result = BigDecimal.valueOf((double) dto.getWeight() / (height * height));

        return new ResultInfo(
                "%s Выраженный дефицит массы тела".formatted(result.setScale(2, RoundingMode.HALF_UP))
        );
    }
}
