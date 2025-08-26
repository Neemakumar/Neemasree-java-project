# 🚨 Incident & Accident Reporting System

## 📌 Project Overview
The **Incident & Accident Reporting System** is a **Java-based console application** that allows users to report incidents/accidents, view existing records, update report status, and delete entries.  
Data is stored **temporarily in memory** using Java Collections (`ArrayList`), meaning all data is cleared once the program ends.  

This project is designed as **Phase I (Console Version)** of a larger system, where future phases may include **database storage** and a **JavaFX-based GUI**.

---

## 🎯 Problem Statement
Manual reporting of incidents/accidents is time-consuming, prone to errors, and lacks real-time tracking.  
An automated system is needed where users can quickly log issues, update their status, and monitor progress.

---

## 👥 Target Users
- Employees and safety officers in organizations  
- Administrators monitoring workplace incidents  
- General public/community members reporting local incidents  

---

## 🛠️ Features
✔️ Add new reports (incident or accident)  
✔️ View all reports with detailed information  
✔️ Update the status of a report (Open / In Progress / Resolved)  
✔️ Delete a report (with confirmation prompt)  
✔️ Search reports by **ID**  
✔️ Search reports by **Type (Incident/Accident)**  
✔️ View summary statistics (total reports, open, resolved, etc.)  
✔️ Auto-generated **timestamps** for each report  

---

## 🏗️ OOP Concepts Used
- **Encapsulation:** Private fields in `Report` class with getters/setters  
- **Abstraction:** `ReportManager` hides implementation details of data storage  
- **Polymorphism:** `toString()` method overridden for report formatting  
- **Composition:** `ReportManager` *has-a* list of `Report` objects  
- **Modularity:** Separation of classes (`Report`, `ReportManager`, `IncidentApp`)  

---

## 📐 Class Diagram (UML)

+-------------------+
|    Report         |
+-------------------+
| -id : int         |
| -type : String    |
| -description : String |
| -status : String  |
| -createdAt : String |
+-------------------+
| +setStatus()      |
| +toString()       |
+-------------------+

+-------------------+
| ReportManager     |
+-------------------+
| -reports : ArrayList<Report> |
| -nextId : int     |
+-------------------+
| +addReport()      |
| +viewReports()    |
| +updateReport()   |
| +deleteReport()   |
| +searchReportById() |
| +searchReportByType() |
| +showSummary()    |
+-------------------+

+-------------------+
| IncidentApp (main)|
+-------------------+
| +main()           |
+-------------------+
