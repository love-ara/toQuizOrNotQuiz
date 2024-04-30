package africa.semicolon.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
public class Quiz {
    @Id
    private String quizId;
    private String quizName;
    private String quizDescription;
    @DBRef
    private List<Question> questions = new ArrayList<>();
}
