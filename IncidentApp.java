import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Report class
class Report {
    private int id;
    private String type;      
    private String description;
    private String status;       
    private String createdAt;    

    public Report(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.status = "Open"; // default
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format(
            "--------------------------------------------------\n" +
            "Report ID   : %d\n" +
            "Type        : %s\n" +
            "Description : %s\n" +
            "Status      : %s\n" +
            "Created At  : %s\n" +
            "--------------------------------------------------",
            id, type, description, status, createdAt
        );
    }
}

// ReportManager class
class ReportManager {
    private ArrayList<Report> reports = new ArrayList<>();
    private int nextId = 1;

    public void addReport(String type, String description) {
        reports.add(new Report(nextId++, type, description));
        System.out.println("\n✅ Report added successfully.\n");
    }

    public void viewReports() {
        if (reports.isEmpty()) {
            System.out.println("\n⚠️ No reports available.\n");
        } else {
            System.out.println("\n===== All Reports =====");
            for (Report r : reports) {
                System.out.println(r);
            }
        }
    }

    public void updateReport(int id, String newStatus) {
        for (Report r : reports) {
            if (r.getId() == id) {
                r.setStatus(newStatus);
                System.out.println("\n✅ Report ID " + id + " updated to '" + newStatus + "'.\n");
                return;
            }
        }
        System.out.println("\n❌ Report not found.\n");
    }

    public void deleteReport(int id) {
        for (Report r : reports) {
            if (r.getId() == id) {
                reports.remove(r);
                System.out.println("\n🗑️ Report ID " + id + " deleted successfully.\n");
                return;
            }
        }
        System.out.println("\n❌ Report not found.\n");
    }

    public void searchReportById(int id) {
        for (Report r : reports) {
            if (r.getId() == id) {
                System.out.println("\n===== Report Found =====");
                System.out.println(r);
                return;
            }
        }
        System.out.println("\n❌ Report with ID " + id + " not found.\n");
    }

    public void searchReportByType(String type) {
        boolean found = false;
        System.out.println("\n===== Search Results for Type: " + type + " =====");
        for (Report r : reports) {
            if (r.getType().equalsIgnoreCase(type)) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\n❌ No reports of type '" + type + "' found.\n");
        }
    }

    public void showSummary() {
        int open = 0, progress = 0, resolved = 0;
        for (Report r : reports) {
            switch (r.getStatus().toLowerCase()) {
                case "open" -> open++;
                case "in progress" -> progress++;
                case "resolved" -> resolved++;
            }
        }
        System.out.println("\n===== Report Summary =====");
        System.out.println("Total Reports  : " + reports.size());
        System.out.println("Open           : " + open);
        System.out.println("In Progress    : " + progress);
        System.out.println("Resolved       : " + resolved);
    }
}

// Main App
public class IncidentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReportManager manager = new ReportManager();

        while (true) {
            System.out.println("\n=== Incident & Accident Reporting System ===");
            System.out.println("1. Add Report");
            System.out.println("2. View All Reports");
            System.out.println("3. Update Report Status");
            System.out.println("4. Delete Report");
            System.out.println("5. Search Report by ID");
            System.out.println("6. Search Report by Type");
            System.out.println("7. Show Summary");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter type (Incident/Accident): ");
                    String type = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    manager.addReport(type, desc);
                }
                case 2 -> manager.viewReports();
                case 3 -> {
                    System.out.print("Enter report ID to update: ");
                    int idToUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new status (Open/In Progress/Resolved): ");
                    String status = sc.nextLine();
                    manager.updateReport(idToUpdate, status);
                }
                case 4 -> {
                    System.out.print("Enter report ID to delete: ");
                    int idToDelete = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        manager.deleteReport(idToDelete);
                    } else {
                        System.out.println("❌ Delete cancelled.");
                    }
                }
                case 5 -> {
                    System.out.print("Enter report ID to search: ");
                    int id = sc.nextInt();
                    manager.searchReportById(id);
                }
                case 6 -> {
                    System.out.print("Enter type to search (Incident/Accident): ");
                    String typeSearch = sc.nextLine();
                    manager.searchReportByType(typeSearch);
                }
                case 7 -> manager.showSummary();
                case 8 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}

