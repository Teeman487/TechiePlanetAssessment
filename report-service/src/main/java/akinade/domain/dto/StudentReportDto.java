package akinade.domain.dto;

public record StudentReportDto (
        String studentName,
        double mean,
        double median,
        int mode
) {
}
