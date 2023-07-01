package com.farmSystemBE.farmSystemBE.mapper;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;
import com.farmSystemBE.farmSystemBE.entity.Attendence;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AttendenceMapper {
    Attendence attendenceDtoToAttendence(AttendenceDto attendenceDto);
    AttendenceDto attendenceToAttendenceDto(Attendence attendence);
}
