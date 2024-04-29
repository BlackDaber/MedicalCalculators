package net.blackdaber.practice.service.impl;

import net.blackdaber.practice.data.AbstractDto;
import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.ResultInfo;
import net.blackdaber.practice.data.impl.ImtDto;
import net.blackdaber.practice.service.MedicalCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service("imtCalculator")
public class ImtCalculator implements MedicalCalculatorService {

    @Override
    public ResultInfo calculate(AbstractDto dto) {
        var imtDto = (ImtDto) dto;
        var height = (double) imtDto.getH() / 100;
        var result = BigDecimal.valueOf((double) imtDto.getM() / (height * height));

        return new ResultInfo(result.setScale(2, RoundingMode.HALF_UP) + " Выраженный дефицит массы тела");
    }

    @Override
    public CalculatorType getCalculatorType() {
        return CalculatorType.IMT;
    }
}
