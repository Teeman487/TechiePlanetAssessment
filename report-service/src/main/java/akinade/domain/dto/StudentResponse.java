package akinade.domain.dto;

import java.util.List;
import java.util.UUID;

public record StudentResponse(
        UUID id,
        String name,
        List<SubjectScoreDTO> scores

) {
}
