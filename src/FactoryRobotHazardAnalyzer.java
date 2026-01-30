import java.util.Scanner;


public class FactoryRobotHazardAnalyzer {

    public static void main(String[] args) {


        System.out.println("Factory Robot Hazard Analyzer");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Arm Precision: ");
        double armPrecision = scanner.nextDouble();

        System.out.print("Enter Worker Density: ");
        int workerDensity = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter Machinery State: ");
        String machineryState = scanner.nextLine();


        RobotHazardAuditor auditor = new RobotHazardAuditor();
        double hazardRiskScore = auditor.calculateHazardRisk(
                armPrecision,
                workerDensity,
                machineryState
        );


        System.out.println("Robot Hazard Risk Score: " + hazardRiskScore);

        scanner.close();
    }
}


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