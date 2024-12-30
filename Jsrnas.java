/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsrnas;


import javax.swing.JOptionPane;

public class Jsrnas {

    public static void main(String[] args) {
        CircleClass circle = null; // Declare Circle object
        boolean running = true;

        // Show the motion calculator options and perform calculations
        while (running) {
            String[] options = {
                "Set Circle Radius", "Speed", "Velocity", "Acceleration", 
                "Force", "Period", "Frequency", "Exit"
            };

            int choice = JOptionPane.showOptionDialog(null, 
                "Choose a motion property to calculate:", 
                "Motion Calculator", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]);

            if (choice == -1) { // Exit if user closes the dialog
                int exitChoice = JOptionPane.showConfirmDialog(null, 
                    "You are about to exit. Do you want to exit?", 
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                
                if (exitChoice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Exiting the program. Goodbye!");
                    running = false;
                }
                continue; // If No, continue the loop
            }

            // Handle the selected option
            switch (choice) {
                case 0: // Set Circle Radius
                    circle = setCircleRadius();
                    break;
                case 1: // Speed
                    if (circle != null) {
                        calculateSpeed(circle);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please set the circle radius first.");
                    }
                    break;
                case 2: // Velocity
                    if (circle != null) {
                        calculateVelocity(circle);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please set the circle radius first.");
                    }
                    break;
                case 3: // Acceleration
                    if (circle != null) {
                        calculateAcceleration(circle);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please set the circle radius first.");
                    }
                    break;
                case 4: // Force
                    if (circle != null) {
                        calculateForce(circle);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please set the circle radius first.");
                    }
                    break;
                case 5: // Period
                    calculatePeriod();
                    break;
                case 6: // Frequency
                    calculateFrequency();
                    break;
                case 7: // Exit
                    runTriviaQuiz();  // Start the trivia quiz
                    running = false;  // Exit the program after trivia
                    break;
                default:
                    break;
            }
        }
    }

    // Method to set the circle radius
    public static CircleClass setCircleRadius() {
        String input = JOptionPane.showInputDialog("Enter the radius of the circle (meters):");
        if (input == null) {
            int exitChoice = JOptionPane.showConfirmDialog(null, 
                    "Do you want to exit? (Yes or No)", 
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (exitChoice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Exiting the program. Goodbye!");
                System.exit(0); // Exit the program
            }
            return null;
        }

        try {
            double radius = Double.parseDouble(input);
            
            // Check for invalid radius (zero or negative)
            if (radius <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid input. The radius cannot be zero or negative.");
                return null;
            }
            
            return new CircleClass(radius); // Create and return a new Circle object
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for the radius.");
            return null;
        }
    }

    // Method to calculate speed
    public static void calculateSpeed(CircleClass circle) {
        String input = JOptionPane.showInputDialog("Enter the period (seconds):");
        if (input == null) return;
        try {
            double period = Double.parseDouble(input);
            double speed = (2 * Math.PI * circle.getRadius()) / period;
            
            // Display formula with actual values
            String formula = "Speed = (2 * pi * " + circle.getRadius() + ") / " + period;
            JOptionPane.showMessageDialog(null, "Formula: " + formula + "\nSpeed = " + speed + " m/s");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for the period.");
        }
    }

    // Method to calculate velocity
    public static void calculateVelocity(CircleClass circle) {
        String input = JOptionPane.showInputDialog("Enter the angular velocity (rad/s):");
        if (input == null) return;
        try {
            double angularVelocity = Double.parseDouble(input);
            double velocity = circle.calculateTangentialVelocity(angularVelocity);
            
            // Display formula with actual values
            String formula = "Velocity = " + circle.getRadius() + " * " + angularVelocity;
            JOptionPane.showMessageDialog(null, "Formula: " + formula + "\nTangential Velocity = " + velocity + " m/s");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for the angular velocity.");
        }
    }

    // Method to calculate acceleration
    public static void calculateAcceleration(CircleClass circle) {
        String input = JOptionPane.showInputDialog("Enter the speed (m/s):");
        if (input == null) return;
        try {
            double speed = Double.parseDouble(input);
            double acceleration = circle.calculateCentripetalAcceleration(speed);
            
            // Display formula with actual values
            String formula = "Acceleration = " + speed + "^2 / " + circle.getRadius();
            JOptionPane.showMessageDialog(null, "Formula: " + formula + "\nCentripetal Acceleration = " + acceleration + " m/s²");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for the speed.");
        }
    }

    // Method to calculate force
    public static void calculateForce(CircleClass circle) {
        String inputMass = JOptionPane.showInputDialog("Enter the mass of the object (kg):");
        if (inputMass == null) return;
        String inputSpeed = JOptionPane.showInputDialog("Enter the speed (m/s):");
        if (inputSpeed == null) return;

        try {
            double mass = Double.parseDouble(inputMass);
            double speed = Double.parseDouble(inputSpeed);
            double force = circle.calculateCentripetalForce(mass, speed);
            
            // Display formula with actual values
            String formula = "Force = " + mass + " * " + speed + "^2 / " + circle.getRadius();
            JOptionPane.showMessageDialog(null, "Formula: " + formula + "\nCentripetal Force = " + force + " N");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers for mass and speed.");
        }
    }

    // Method to calculate period
    public static void calculatePeriod() {
        String input = JOptionPane.showInputDialog("Enter the angular velocity (rad/s):");
        if (input == null) return;
        try {
            double angularVelocity = Double.parseDouble(input);
            double period = 2 * Math.PI / angularVelocity;
            JOptionPane.showMessageDialog(null, "Period = " + period + " seconds");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for the angular velocity.");
        }
    }

    // Method to calculate frequency
    public static void calculateFrequency() {
        String input = JOptionPane.showInputDialog("Enter the period (seconds):");
        if (input == null) return;
        try {
            double period = Double.parseDouble(input);
            double frequency = 1 / period;
            JOptionPane.showMessageDialog(null, "Frequency = " + frequency + " Hz");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for the period.");
        }
    }

    // Trivia questions and IQ calculation
    public static void runTriviaQuiz() {
        String[] questions = {
            "Surprise Questions!!!\n"
                + "\nWhat is the formula for speed?",
            "What does acceleration measure?",
            "What is the unit for force?",
            "What is the centripetal force directed toward?",
            "What does the period of an object in circular motion represent?",
            "What is the formula for the period of a rotating object?",
            "What is the relationship between velocity and angular velocity in circular motion?",
            "What is the unit for acceleration?",
            "How do you calculate the centripetal force?",
            "What is the formula for frequency in circular motion?",
            "What is the radius of the Earth's orbit?",
            "What is the primary force acting on a satellite in orbit?",
            "In a uniform circular motion, what direction is the acceleration?",
            "What type of motion does a car moving along a circular path have?",
            "What is the relationship between frequency and period?"
        };

        String[][] options = {
            {"v = d/t", "v = at", "v = m * g", "v = 1/2 * m * v^2"},
            {"Change in distance over time", "Change in velocity over time", "The amount of force applied", "The speed of an object"},
            {"Joules", "Newtons", "Kilograms", "Meters per second squared"},
            {"The center of the object’s path", "The direction of motion", "The outside of the path", "The speed of the object"},
            {"Time for one full rotation", "Force applied to the object", "Speed of the object", "Distance traveled by the object"},
            {"T = 2π/ω", "T = v/r", "T = ω/r", "T = 2πr"},
            {"v = rω", "v = ω/r", "v = r²ω", "v = ωr²"},
            {"m/s²", "m/s", "m", "kg"},
            {"Fc = m * g", "Fc = m * v²/r", "Fc = m * a", "Fc = v/r"},
            {"f = 1/T", "f = T", "f = v/r", "f = 1/r"},
            {"Approximately 150 million km", "Approximately 100 million km", "Approximately 500 million km", "Approximately 200 million km"},
            {"Gravitational force", "Frictional force", "Magnetic force", "Centripetal force"},
            {"Towards the center of the circle", "Away from the center", "Along the tangential direction", "Along the radius"},
            {"Uniform circular motion", "Linear motion", "Projectile motion", "Random motion"},
            {"f = 1/T", "f = T", "f = T²", "f = 1/T²"}
        };

        int[] correctAnswers = {0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1}; // Correct answers (0-based index)

        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            int answer = JOptionPane.showOptionDialog(null, questions[i], 
                "Trivia Question " + (i + 1), JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, options[i], options[i][0]);

            if (answer == correctAnswers[i]) {
                score++;
            }
        }

        double percentage = (double) score / questions.length * 100;
        int iq = calculateIQ(percentage);

        JOptionPane.showMessageDialog(null, "Your final score: " + score + "/15\n" + 
            "Your percentage: " + percentage + "%\n" + "Your estimated IQ: " + iq);
    }

    // Method to calculate IQ based on the percentage score
    public static int calculateIQ(double percentage) {
        if (percentage == 100) {
            return 140;
        } else if (percentage >= 80) {
            return 117;
        } else if (percentage >= 60) {
            return 97;
        } else if (percentage >= 40) {
            return 80;
        } else {
            return 70;
        }
    }
}