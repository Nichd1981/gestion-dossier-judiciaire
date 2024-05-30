package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Judgment;
import be.tftic.java.domain.enums.JudgmentDecision;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class JudgmentResponse {

    private LocalDateTime judgmentDate;
    private JudgmentDecision judgmentDecision;
    private String commentary;

    public static JudgmentResponse fromEntity(Judgment judgment){
        return JudgmentResponse.builder()
                .judgmentDate(judgment.getJudgmentDate())
                .judgmentDecision(judgment.getJudgmentDecision())
                .commentary(judgment.getCommentary())
                .build();
    }

}
