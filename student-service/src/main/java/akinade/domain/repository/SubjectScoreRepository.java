package akinade.domain.repository;

import akinade.domain.model.SubjectScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubjectScoreRepository extends JpaRepository<SubjectScore, UUID> {
}
