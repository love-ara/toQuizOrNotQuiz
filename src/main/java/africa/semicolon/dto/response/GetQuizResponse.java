package africa.semicolon.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetQuizResponse {
    private String QuizTitle;
    private List<GetQuestionResponse> getQuestionResponse = new ArrayList<>();
    private String quizPin;
}
