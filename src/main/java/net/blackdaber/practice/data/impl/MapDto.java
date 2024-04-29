package net.blackdaber.practice.data.impl;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import net.blackdaber.practice.data.AbstractDto;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class MapDto extends AbstractDto {

    @Min(1)
    @Max(10)
    int sad;

    @Min(1)
    @Max(10)
    int dad;
}
