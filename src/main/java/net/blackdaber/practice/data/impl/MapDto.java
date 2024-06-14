package net.blackdaber.practice.data.impl;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Max(1000)
    @Schema(example = "163", description = "Систолическое АД")
    int sbp;

    @Min(1)
    @Max(1000)
    @Schema(example = "52", description = "Диастолическое АД")
    int dbp;
}
