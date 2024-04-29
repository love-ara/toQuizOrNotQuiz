package africa.semicolon.service;

import africa.semicolon.data.repository.QuizRepository;
import africa.semicolon.dto.request.*;
import africa.semicolon.dto.response.CreateQuizResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.gen5.api.Assertions.assertThrows;

@SpringBootTest
public class QuizServiceTest {
    private final QuizService quizService;
    private final QuizRepository quizRepository;

    private CreateQuizRequest createQuizRequest;
    private CreateQuizResponse createQuizResponse;
    private DeleteQuizRequest deleteQuizRequest;

    @Autowired
    public QuizServiceTest(QuizService quizService, QuizRepository quizRepository){
        this.quizService = quizService;
        this.quizRepository = quizRepository;
    }

    @BeforeEach
    public void setUp(){
        quizRepository.deleteAll();

        createQuizRequest = new CreateQuizRequest();
        createQuizRequest.setQuizName("Quiz Name");
        createQuizRequest.setQuizDescription("Quiz Description");

        CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest();
        createQuestionRequest.setQuestionContent("Question Content");
        OptionRequest optionRequest = new OptionRequest();
        optionRequest.setOptionContent("Option Content");

        createQuestionRequest.setOption(List.of(optionRequest));
        createQuestionRequest.setAnswer("answer");
        createQuizRequest.setCreateQuestionRequest(List.of(createQuestionRequest));



    }

    @Test
    public void testCreateQuiz(){
        quizService.createQuiz(createQuizRequest);
        assertThat(quizRepository.count(), is(1L));
    }

    @Test
    public void testThatAQuizCanNotBeCreatedTwice(){

        quizService.createQuiz(createQuizRequest);

        assertThrows(IllegalArgumentException.class, ()-> quizService.createQuiz(createQuizRequest));
    }

    @Test
    public void quizCanBeUpdatedTest(){

        var quiz = quizService.createQuiz(createQuizRequest);

        createQuizResponse = new CreateQuizResponse();
        createQuizResponse.setQuizId(quiz.getQuizId());

        UpdateQuizRequest updateQuizRequest = new UpdateQuizRequest();
        updateQuizRequest.setQuizId(quiz.getQuizId());
        updateQuizRequest.setQuizName(quiz.getQuizName());
        updateQuizRequest.setQuizDescription("Updated Quiz Description");

        UpdateQuestionRequest updateQuestionRequest = new UpdateQuestionRequest();
        updateQuestionRequest.setQuestionContent("Question Content");
        OptionRequest optionRequest = new OptionRequest();
        optionRequest.setOptionContent("Option Content");

        updateQuestionRequest.setOptions(List.of(optionRequest));
        updateQuestionRequest.setAnswer("answer");

        updateQuizRequest.setUpdateQuestionRequest(List.of(updateQuestionRequest));
        quizService.updateQuiz(updateQuizRequest);
    }

    @Test
    public void quizCanBeDeletedTest(){

        var quiz = quizService.createQuiz(createQuizRequest);

        deleteQuizRequest = new DeleteQuizRequest();
        deleteQuizRequest.setQuizId(quiz.getQuizId());
        assertThat(quizRepository.count(), is(1L));


        quizService.deleteQuiz(deleteQuizRequest);
        assertThat(quizRepository.count(), is(0L));
    }
}