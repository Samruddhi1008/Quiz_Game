package quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGame {
    private List<Question> questions = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startGame(Player player) {
        System.out.println("Welcome, " + player.getName() + "! Let's start the quiz.");
        for (Question question : questions) {
            displayQuestion(question);
            int userAnswer = getUserAnswer();
            if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                player.increaseScore();
            } else {
                System.out.println("Incorrect! The correct answer was: " + (question.getCorrectAnswer() + 1));
            }
        }
        displayFinalScore(player);
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    private int getUserAnswer() {
        System.out.print("Your answer (1-4): ");
        return scanner.nextInt() - 1;
    }

    private void displayFinalScore(Player player) {
        System.out.println("\nQuiz Over!");
        System.out.println(player.getName() + ", your final score is: " + player.getScore() + "/" + questions.size());
    }

    public static void main(String[] args) {
        QuizGame quizGame = new QuizGame();

        // Add sample questions
        quizGame.addQuestion(new Question(
                "What is the capital of France?",
                new String[]{"Paris", "Rome", "Madrid", "Berlin"},
                0
        ));

        quizGame.addQuestion(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Jupiter", "Mars", "Venus"},
                2
        ));

        quizGame.addQuestion(new Question(
                "Who wrote 'To Kill a Mockingbird'?",
                new String[]{"Harper Lee", "Mark Twain", "J.K. Rowling", "Charles Dickens"},
                0
        ));

        // Start the quiz with a new player
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        quizGame.startGame(player);
    }
}
