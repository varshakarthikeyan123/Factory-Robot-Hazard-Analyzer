import java.util.Scanner;

// UC5: Validation + exception handling
public class FactoryRobotHazardAnalyzer {

    public static void main(String[] args) {

        System.out.println("Factory Robot Hazard Analyzer");

        Scanner scanner = new Scanner(System.in);

        try {
            // UC2: Accept inputs
            System.out.print("Enter Arm Precision: ");
            double armPrecision = scanner.nextDouble();

            System.out.print("Enter Worker Density: ");
            int workerDensity = scanner.nextInt();

            scanner.nextLine(); // clear buffer

            System.out.print("Enter Machinery State (NORMAL / CRITICAL): ");
            String machineryState = scanner.nextLine();

            // UC5: Validation
            validateInputs(armPrecision, workerDensity, machineryState);

            // UC3: Calculation (no change)
            RobotHazardAuditor auditor = new RobotHazardAuditor();
            double hazardRiskScore =
                    auditor.calculateHazardRisk(
                            armPrecision,
                            workerDensity,
                            machineryState
                    );

            System.out.println("Robot Hazard Risk Score: " + hazardRiskScore);

        } catch (InvalidHazardInputException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    // UC5: Validation method
    private static void validateInputs(double armPrecision,
                                       int workerDensity,
                                       String machineryState)
            throws InvalidHazardInputException {

        if (armPrecision < 0.0 || armPrecision > 1.0) {
            throw new InvalidHazardInputException(
                    "Error: Arm precision must be between 0.0 and 1.0"
            );
        }

        if (workerDensity < 0 || workerDensity > 20) {
            throw new InvalidHazardInputException(
                    "Error: Worker density must be between 0 and 20"
            );
        }

        if (!machineryState.equalsIgnoreCase("NORMAL") &&
                !machineryState.equalsIgnoreCase("CRITICAL")) {

            throw new InvalidHazardInputException(
                    "Error: Machinery state must be NORMAL or CRITICAL"
            );
        }
    }
}

/*
 * UC3: Auditor class (unchanged)
 */
class RobotHazardAuditor {

    public double calculateHazardRisk(double armPrecision,
                                      int workerDensity,
                                      String machineryState) {

        double riskScore = armPrecision * workerDensity;

        if (machineryState.equalsIgnoreCase("CRITICAL")) {
            riskScore = riskScore * 1.5;
        }

        return riskScore;
    }
}