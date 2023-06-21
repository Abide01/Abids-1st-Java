import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

class FitnessTracker {
    private String name;
    private List<Exercise> exercises;

    public FitnessTracker(String name) {
        this.name = name;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public String getName() {
        return name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}

class Exercise {
    private int type;
    private String name;
    private int reps;
    private int time;

    public Exercise(int type, String name, int reps, int time) {
        this.type = type;
        this.name = name;
        this.reps = reps;
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("'").append(name).append("'");

        if (type == 1) {  // Weight Training
            builder.append(" Reps = ").append(reps);
        } else if (type == 2) {  // Cardio
            builder.append(" Time = ").append(time).append(" minutes");
        }

        return builder.toString();
    }
}

public class FitnessTrackingAppMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        FitnessTracker tracker = new FitnessTracker(userName);

        String exerciseType;
        boolean isFirstExercise = true;
        boolean quitInputFirstTime = false;
        while (true) {
            System.out.println("Select exercise type:");
            System.out.println("Press 1 for Weight Training");
            System.out.println("Press 2 for Cardio");
            System.out.println("Press 3 if done");

            System.out.print("Your choice: ");
            exerciseType = scanner.nextLine();

            if (exerciseType.equals("3")) {
                if (isFirstExercise) {
                    if (!quitInputFirstTime) {
                        System.out.println("Quitting is for losers. You are not a loser. It's time to make a change!");
                        quitInputFirstTime = true;
                        continue;
                    } else {
                        System.out.println("Are you sure you want to quit? Press 'y' for Yes or 'n' for No.");
                        String quitConfirmation = scanner.nextLine();
                        if (quitConfirmation.equalsIgnoreCase("y")) {
                            break;
                        } else if (quitConfirmation.equalsIgnoreCase("n")) {
                            continue;
                        }
                    }
                } else {
                    System.out.println("Are you sure you want to quit? Press 'y' for Yes or 'n' for No.");
                    String quitConfirmation = scanner.nextLine();
                    if (quitConfirmation.equalsIgnoreCase("y")) {
                        break;
                    } else if (quitConfirmation.equalsIgnoreCase("n")) {
                        continue;
                    }
                }
            }

            if (exerciseType.equals("1") || exerciseType.equals("2")) {
            }

            if (!exerciseType.equals("1") && !exerciseType.equals("2")) {
                System.out.println("Invalid choice. Please select a given option.");
                continue;
            }

            if (exerciseType.equals("1")) {
                handleWeightTraining(scanner, tracker);
            } else if (exerciseType.equals("2")) {
                handleCardio(scanner, tracker);
            }

            isFirstExercise = false;
        }

        List<Exercise> sortedExercises = tracker.getExercises();
        Collections.sort(sortedExercises, (e1, e2) -> e1.toString().compareToIgnoreCase(e2.toString()));

        System.out.println("Exercise Summary for " + tracker.getName());
        System.out.println("For max gains and best results, we work until we can't anymore.");

        for (int i = 0; i < sortedExercises.size(); i++) {
            Exercise exercise = sortedExercises.get(i);
            System.out.println("Exercise " + (i + 1) + ": " + exercise.toString());
        }

        if (isFirstExercise) {
            System.out.println("Today's workout has been skipped.");
        }

        System.out.println("\n" + LocalDate.now());
        System.out.println("\"The only way to achieve your goals is to work hard, stay dedicated, and never give up.\" - Michael Jordan");

        scanner.close(); // Close the scanner to release resources
    }

    private static void handleWeightTraining(Scanner scanner, FitnessTracker tracker) {
        System.out.println("Select a weight training exercise:");
        System.out.println("1. Push-ups");
        System.out.println("2. Squats");
        System.out.println("3. Lunges");
        System.out.println("4. Planks");
        System.out.println("5. Sit-ups");
        System.out.println("6. Burpees");
        System.out.println("7. Dumbbell curls");
        System.out.println("8. Bench press");
        System.out.println("9. Custom workout");
        System.out.println("0. Go back to exercise type selection");

        System.out.print("Your choice: ");
        String exerciseChoice = scanner.nextLine();

        if (exerciseChoice.equals("9")) {
            System.out.print("Enter custom exercise name: ");
            String exerciseName = scanner.nextLine();
            
            int exerciseSets;
            while (true) {
                System.out.print("Enter number of sets: ");
                String setsInput = scanner.nextLine();
                
                try {
                    exerciseSets = Integer.parseInt(setsInput);
                    if (exerciseSets > 0) {
                        break; // Valid input, exit the loop
                    } else {
                        System.out.println("Invalid input. Please enter a number greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            int exerciseTime = 0;  // Set time to 0 for weight training exercises

            Exercise exercise = new Exercise(1, exerciseName, exerciseSets, exerciseTime);
            tracker.addExercise(exercise);
            System.out.println("Exercise added successfully!\n");
        } else if (exerciseChoice.equals("0")) {
            // Go back to exercise type selection
            return;
        } else {
            int choice;
            try {
                choice = Integer.parseInt(exerciseChoice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please select a given option.");
                return;
            }
            
            if (choice < 1 || choice > 8) {
                System.out.println("Invalid choice. Please select a given option.");
                return;
            }
            
            List<String> weightTrainingExercises = List.of(
                "Push-ups", "Squats", "Lunges", "Planks", "Sit-ups", "Burpees", "Dumbbell curls", "Bench press"
            );
            String exerciseName = weightTrainingExercises.get(choice - 1);
            
            int exerciseSets;
            while (true) {
                System.out.print("Enter number of sets: ");
                String setsInput = scanner.nextLine();
                
                try {
                    exerciseSets = Integer.parseInt(setsInput);
                    if (exerciseSets > 0) {
                        break; // Valid input, exit the loop
                    } else {
                        System.out.println("Invalid input. Please enter a number greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            int exerciseTime = 0;  // Set time to 0 for weight training exercises

            Exercise exercise = new Exercise(1, exerciseName, exerciseSets, exerciseTime);
            tracker.addExercise(exercise);
            System.out.println("Exercise added successfully!\n");
        }
    }

    private static void handleCardio(Scanner scanner, FitnessTracker tracker) {
        System.out.println("Select a cardio exercise:");
        System.out.println("1. Running");
        System.out.println("2. Cycling");
        System.out.println("3. Swimming");
        System.out.println("4. Jumping jacks");
        System.out.println("5. Jump rope");
        System.out.println("6. High knees");
        System.out.println("7. Mountain climbers");
        System.out.println("8. Stair climbing");
        System.out.println("9. Custom workout");
        System.out.println("0. Go back to exercise type selection");

        System.out.print("Your choice: ");
        String exerciseChoice = scanner.nextLine();

        if (exerciseChoice.equals("9")) {
            System.out.print("Enter custom exercise name: ");
            String exerciseName = scanner.nextLine();
            
            int exerciseTime;
            while (true) {
                System.out.print("Enter exercise time (in minutes): ");
                String timeInput = scanner.nextLine();
                
                try {
                    exerciseTime = Integer.parseInt(timeInput);
                    if (exerciseTime > 0) {
                        break; // Valid input, exit the loop
                    } else {
                        System.out.println("Invalid input. Please enter a number greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            int exerciseSets = 0;  // Set sets to 0 for cardio exercises

            Exercise exercise = new Exercise(2, exerciseName, exerciseSets, exerciseTime);
            tracker.addExercise(exercise);
            System.out.println("Exercise added successfully!\n");
        } else if (exerciseChoice.equals("0")) {
            // Go back to exercise type selection
            return;
        } else {
            int choice;
            try {
                choice = Integer.parseInt(exerciseChoice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please select a given option.");
                return;
            }
            
            if (choice < 1 || choice > 8) {
                System.out.println("Invalid choice. Please select a given option.");
                return;
            }
            
            List<String> cardioExercises = List.of(
                "Running", "Cycling", "Swimming", "Jumping jacks", "Jump rope", "High knees", "Mountain climbers",
                "Stair climbing"
            );
            String exerciseName = cardioExercises.get(choice - 1);
            
            int exerciseTime;
            while (true) {
                System.out.print("Enter exercise time (in minutes): ");
                String timeInput = scanner.nextLine();
                
                try {
                    exerciseTime = Integer.parseInt(timeInput);
                    if (exerciseTime > 0) {
                        break; // Valid input, exit the loop
                    } else {
                        System.out.println("Invalid input. Please enter a number greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            int exerciseSets = 0;  // Set sets to 0 for cardio exercises

            Exercise exercise = new Exercise(2, exerciseName, exerciseSets, exerciseTime);
            tracker.addExercise(exercise);
            System.out.println("Exercise added successfully!\n");
        }
    }
}




