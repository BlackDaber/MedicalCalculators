package net.blackdaber.practice.service;

import net.blackdaber.practice.data.AbstractDto;
import net.blackdaber.practice.data.CalculatorType;
import net.blackdaber.practice.data.ResultInfo;

public interface MedicalCalculatorService<T extends AbstractDto> {

    ResultInfo calculate(T dto);

    CalculatorType getCalculatorType();
}
