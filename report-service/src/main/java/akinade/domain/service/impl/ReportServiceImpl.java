package akinade.domain.service.impl;

import akinade.domain.client.StudentClient;
import akinade.domain.dto.StudentResponse;
import akinade.domain.model.StudentReport;
import akinade.domain.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final StudentClient client;  // sync with other service

    public ReportServiceImpl(StudentClient client) {
        this.client = client;
    }

    public Page<StudentReport> getReports(int page, int size, String nameFilter){
        StudentResponse[] students = client.getAllStudents();

        List<StudentReport> reports = Arrays.stream(students)
                .filter(s -> nameFilter == null ||
                        s.name().toLowerCase().contains(nameFilter.toLowerCase()))
                .map(this::generateReport).collect(Collectors.toList());

        int start = Math.min(page * size, reports.size());
        int end = Math.min(start + size, reports.size());

        List<StudentReport> paged = reports.subList(start, end);
        return new PageImpl<>(paged, PageRequest.of(page, size), reports.size());
    }

    public StudentReport getReport(UUID id){
        return generateReport(client.getStudent(id));
    }


    private StudentReport generateReport(StudentResponse student){
        List<Integer> studentScores = student.scores().stream()
                .map(s -> s.score()).toList();
        double mean = computeMean(studentScores);
        double median = computeMedian(studentScores);
        int mode = computeMode(studentScores);
        return new StudentReport(student.name(), studentScores, mean, median, mode);
    }

    private double computeMean(List<Integer> scores){
        return scores.stream().mapToInt(i -> i).average().orElse(0);
    }



    private double computeMedian(List<Integer> scores){
            List<Integer> sorted = new ArrayList<>(scores);
            Collections.sort(sorted);

            int n = sorted.size();
            if(n %2 ==1) //  31 ...
                return sorted.get(n/2);
        return (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
    }

    private int computeMode(List<Integer> scores){
        if (scores.isEmpty()) return 0;

        Map<Integer, Long> freq = scores.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        return freq.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }
}
