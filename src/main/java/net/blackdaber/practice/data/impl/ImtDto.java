package net.blackdaber.practice.data.impl;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import net.blackdaber.practice.data.AbstractDto;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class ImtDto extends AbstractDto {

    int m;
    int h;
}

