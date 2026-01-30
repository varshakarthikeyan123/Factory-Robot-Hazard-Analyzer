// UC4: Input Validation using if-else
// Validation logic is intentionally kept inside main method

import java.util.Scanner;

public class FactoryRobotHazardAnalyzer {

    public static void main(String[] args) {

        // UC1: Display system name
        System.out.println("Factory Robot Hazard Analyzer");

        Scanner scanner = new Scanner(System.in);

        // UC2: Accept inputs
        System.out.print("Enter Arm Precision (0.0 - 1.0): ");
        double armPrecision = scanner.nextDouble();

        System.out.print("Enter Worker Density (1 - 20): ");
        int workerDensity = scanner.nextInt();

        scanner.nextLine(); // clear buffer

        System.out.print("Enter Machinery State (Worn/Faulty/Critical): ");
        String machineryState = scanner.nextLine();

        // UC4: Validation using if-else

        // Validate arm precision
        if (armPrecision < 0.0 || armPrecision > 1.0) {
            System.out.println("Error: Arm precision must be 0.0-1.0");
        }
        // Validate worker density
        else if (workerDensity < 1 || workerDensity > 20) {
            System.out.println("Error: Worker density must be 1-20");
        }
        // Validate machinery state
        else if (
                !machineryState.equals("Worn") &&
                        !machineryState.equals("Faulty") &&
                        !machineryState.equals("Critical")
        ) {
            System.out.println("Error: Unsupported machinery state");
        }
        else {
            // UC3 logic reused (only if inputs are valid)

            double machineryRiskFactor = 1.0;

            if (machineryState.equals("Worn")) {
                machineryRiskFactor = 1.3;
            } else if (machineryState.equals("Faulty")) {
                machineryRiskFactor = 2.0;
            } else if (machineryState.equals("Critical")) {
                machineryRiskFactor = 3.0;
            }

            double hazardRiskScore =
                    ((1.0 - armPrecision) * 15.0) +
                            (workerDensity * machineryRiskFactor);

            System.out.println("Robot Hazard Risk Score: " + hazardRiskScore);
        }

        scanner.close();
    }
}