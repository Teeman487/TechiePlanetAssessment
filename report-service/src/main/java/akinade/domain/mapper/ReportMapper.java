package akinade.domain.mapper;

import akinade.domain.dto.StudentReportDto;
import akinade.domain.dto.StudentRequest;
import akinade.domain.dto.StudentResponse;
import akinade.domain.dto.SubjectScoreDTO;
import akinade.domain.model.Student;
import akinade.domain.model.StudentReport;

import java.util.stream.Collectors;

public class ReportMapper {

    public static StudentReportDto toDto(StudentReport report) {
        return new StudentReportDto(
               report.getStudentName(),
               report.getMean(),
               report.getMedian(),
                report.getMode()
        );

    }
}
