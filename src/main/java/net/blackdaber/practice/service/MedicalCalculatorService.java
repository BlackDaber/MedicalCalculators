package net.blackdaber.practice.service;

import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.AbstractDto;
import net.blackdaber.practice.data.ResultInfo;

public interface MedicalCalculatorService {

    ResultInfo calculate(AbstractDto dto);

    CalculatorType getCalculatorType();
}
