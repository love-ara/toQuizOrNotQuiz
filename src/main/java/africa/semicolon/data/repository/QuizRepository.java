package africa.semicolon.data.repository;

import africa.semicolon.data.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
    Quiz findByQuizTitle(String quizTitle);

    boolean existsByQuizTitle(String quizTitle);

    Quiz findByQuizPin(String quizPin);
}
