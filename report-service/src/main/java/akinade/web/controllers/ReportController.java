package akinade.web.controllers;

import akinade.domain.dto.StudentReportDto;
import akinade.domain.mapper.ReportMapper;
import akinade.domain.model.StudentReport;
import akinade.domain.service.ReportService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;


    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public Page<StudentReportDto> getAll(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(required = false) String filter

                                         ) {
        return reportService.getReports(page, size, filter).map(ReportMapper :: toDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentReportDto> getOne(@PathVariable UUID id) {
        StudentReport report = reportService.getReport(id);
        return ResponseEntity.ok(ReportMapper.toDto(report));
    }
}
