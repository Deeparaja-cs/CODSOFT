import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz1 {
    private static final int TIME_LIMIT = 10; // Time limit for each question in seconds
    private static final int TOTAL_QUESTIONS = 5;
    private static int score = 0;
    private static boolean answerSubmitted = false;
    private static Timer timer;
    private static int timeRemaining;

    // Array of questions
    private static String[] questions = {
        "1. What is the capital of France?",
        "2. Which language is used for Android development?",
        "3. Who is the author of 'Harry Potter'?",
        "4. What is the largest planet in our solar system?",
        "5. Which element has the chemical symbol 'O'?"
    };

    // 2D Array of options
    private static String[][] options = {
        {"A. Paris", "B. London", "C. Berlin", "D. Madrid"},
        {"A. Python", "B. Swift", "C. Kotlin", "D. JavaScript"},
        {"A. George Orwell", "B. J.K. Rowling", "C. Mark Twain", "D. Charles Dickens"},
        {"A. Earth", "B. Mars", "C. Jupiter", "D. Saturn"},
        {"A. Helium", "B. Oxygen", "C. Carbon", "D. Nitrogen"}
    };

    // Array of correct answers
    private static char[] correctAnswers = {'A', 'C', 'B', 'C', 'B'};

    // Array to store user responses
    private static char[] userAnswers = new char[TOTAL_QUESTIONS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < TOTAL_QUESTIONS; i++) {
            displayQuestion(i);

            answerSubmitted = false;
            startTimer(scanner, i);

            // Wait for the answer to be submitted or timer to run out
            while (!answerSubmitted) {
                // Busy-wait loop until answer is submitted or timer ends
            }

            // If answer was submitted, store it
            if (answerSubmitted) {
                System.out.println("Answer Submitted!\n");
            } else {
                System.out.println("Time up! Moving to the next question...\n");
                userAnswers[i] = ' ';
            }
        }

        // Show the final result
        displayResult();
        scanner.close();
    }

    // Display question with options
    public static void displayQuestion(int questionIndex) {
        System.out.println(questions[questionIndex]);
        for (String option : options[questionIndex]) {
            System.out.println(option);
        }
    }

    // Start the timer for each question with a visible countdown
    public static void startTimer(Scanner scanner, int questionIndex) {
        timer = new Timer();
        timeRemaining = TIME_LIMIT;

        // Timer to update remaining time every second
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    System.out.print("Time remaining: " + timeRemaining + " seconds\r");
                    timeRemaining--;
                } else {
                    answerSubmitted = true; // Time is up
                    timer.cancel();
                }
            }
        }, 0, 1000);

        // User input
        System.out.print("\nPlease select an option (A/B/C/D): ");
        String input = scanner.nextLine().toUpperCase();

        // Check for valid input
        if (input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'D') {
            userAnswers[questionIndex] = input.charAt(0);
            timer.cancel(); // Cancel the timer if the answer is submitted
            if (userAnswers[questionIndex] == correctAnswers[questionIndex]) {
                score++;
            }
        } else {
            System.out.println("Invalid input! Moving to the next question.");
        }
        answerSubmitted = true;
    }

    // Display final score and summary
    public static void displayResult() {
        System.out.println("Quiz finished!");
        System.out.println("Your score: " + score + "/" + TOTAL_QUESTIONS);
        System.out.println("Summary of answers:");

        for (int i = 0; i < TOTAL_QUESTIONS; i++) {
            System.out.println(questions[i]);
            System.out.println("Your answer: " + (userAnswers[i] == ' ' ? "No answer" : userAnswers[i]));
            System.out.println("Correct answer: " + correctAnswers[i] + "\n");
        }
    }
}
