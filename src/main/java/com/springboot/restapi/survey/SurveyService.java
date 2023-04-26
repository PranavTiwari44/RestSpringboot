package com.springboot.restapi.survey;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Predicate;

@Service
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();

    static {
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    public List<Survey> retrieveAllSurveys() {
        return surveys;
    }

    public Survey retrieveSurveyById(String id) {
        Optional<Survey> surveyOptional = surveys.stream().filter(survey -> Objects.equals(survey.getId(), id)).findFirst();
        return surveyOptional.orElse(null);
    }

    public List<Question> retrieveAllSurveyQuestions(String surveyId) {
        Survey survey = retrieveSurveyById(surveyId);
        if(survey != null) {
            return survey.getQuestions();
        } else {
            return null;
        }
    }

    public Question retrieveSpecificSurveyQuestion(String surveyId, String questionId) {
       List<Question> questions = retrieveAllSurveyQuestions(surveyId);
        if(questions != null) {
          Optional<Question> optionalQuestion = questions.stream().filter(question -> Objects.equals(question.getId(), questionId)).findFirst();
          return optionalQuestion.orElse(null);
        } else {
            return null;
        }
    }

    public String addNewSurveyQuestion(String surveyId, Question question) {
        List<Question> questions = retrieveAllSurveyQuestions(surveyId);
        question.setId(generateRandomId());
        questions.add(question);
        return question.getId();
    }

    private static String generateRandomId() {
        SecureRandom secureRandom = new SecureRandom();
        return new BigInteger(32, secureRandom).toString();
    }

    public String deleteSurveyQuestion(String surveyId, String questionId) {
        List<Question> questions = retrieveAllSurveyQuestions(surveyId);
        if(questions != null) {
            Predicate<? super Question> predicate = q -> q.getId().equalsIgnoreCase(questionId);
            if(questions.removeIf(predicate)){
                return questionId;
            }
            else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
        List<Question> questions = retrieveAllSurveyQuestions(surveyId);
        questions.removeIf(q -> q.getId().equalsIgnoreCase(questionId));
        questions.add(question);
    }
}
