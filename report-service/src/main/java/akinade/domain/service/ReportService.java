package akinade.domain.service;

import akinade.domain.model.StudentReport;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ReportService {
    Page<StudentReport> getReports(int page, int size, String nameFilter);
    StudentReport getReport(UUID id);

}
