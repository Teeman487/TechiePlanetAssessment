package akinade.domain.dto;

import akinade.domain.model.Subject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Map;

public record StudentRequest (
        @NotBlank String name,

        @Size(min = 5, max = 5)
        Map<Subject, @Min(0) @Max(100) Integer> scores
) {}
