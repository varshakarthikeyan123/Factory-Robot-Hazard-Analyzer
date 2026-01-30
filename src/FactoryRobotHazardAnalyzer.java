import java.util.Scanner;

// UC8: Final cleanup and graceful termination
public class FactoryRobotHazardAnalyzer {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("   Factory Robot Hazard Analyzer");
        System.out.println("====================================");

        try {
            // UC2: Accept inputs
            System.out.print("Enter Arm Precision (0.0 - 1.0): ");
            double armPrecision = scanner.nextDouble();

            System.out.print("Enter Worker Density (0 - 20): ");
            int workerDensity = scanner.nextInt();

            scanner.nextLine(); // clear buffer

            System.out.print("Enter Machinery State (NORMAL / CRITICAL): ");
            String machineryState = scanner.nextLine();

            // UC5: Validation
            validateInputs(armPrecision, workerDensity, machineryState);

            // UC6: Hazard calculation
            RobotHazardAuditor auditor = new RobotHazardAuditor();
            double hazardRiskScore =
                    auditor.calculateHazardRisk(
                            armPrecision,
                            workerDensity,
                            machineryState
                    );

            // UC7: Success output
            System.out.println("------------------------------------");
            System.out.println("Hazard Analysis Completed Successfully");
            System.out.println("Final Hazard Risk Score : " + hazardRiskScore);
            System.out.println("------------------------------------");

        } catch (InvalidHazardInputException e) {
            // UC7: Error output
            System.out.println("------------------------------------");
            System.out.println("Hazard Analysis Failed");
            System.out.println(e.getMessage());
            System.out.println("------------------------------------");
        } finally {
            // UC8: Graceful cleanup
            scanner.close();
            System.out.println("Program execution completed.");
        }
    }

    // UC5: Validation logic
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
 * Hazard calculation logic (UC6)
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