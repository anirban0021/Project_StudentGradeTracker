# Project_StudentGradeTracker

A lightweight, cross-platform Java desktop application that allows teachers and instructors to easily input student names and grades, track real-time entries, and instantly generate analytical class summaries.

## Features

- **Intuitive GUI Layout:** Structured with an input panel at the top and a clear, monospace text output field at the bottom.
- **Real-Time Log:** Displays a running feed of added students with formatted, tabular spacing.
- **Smart Analytics:** Calculates total student count, precise class average, and identifies the highest and lowest scores.
- **Tie Support:** If multiple students share the highest or lowest score, the final report gracefully lists all of their names separated by commas.
- **Input Error Handling:** Includes modal pop-ups protecting against empty values, negative numbers, or invalid text inputs.
- **Quality of Life Enhancements:** Supports submission via the **Enter** key and adopts your operating system's native window aesthetics.

---

## Architecture & Layout Overview

The user interface relies on standard Java Abstract Window Toolkit (AWT) and Swing layout managers:
* **Main Window (`JFrame`):** Configured with a `BorderLayout`.
* **Input Section (`JPanel`):** Placed in the `NORTH` region, utilizing a `GridLayout(3, 2)` to align form fields and control buttons perfectly.
* **Output Console (`JScrollPane`):** Placed in the `CENTER` region, wrapping a non-editable `JTextArea` so data stays clean and scrollable when lists grow long.

---

## Prerequisites

Before running this project, ensure you have the following installed:
* **Java Development Kit (JDK):** Version 8 or higher.

## Getting Started

### 1. Clone or Download the Source
Save the code into a file named `StudentGradeTracker.java`.

### 2. Compilation
Open your terminal or command prompt, navigate to the folder containing the file, and compile it using:
```bash
javac StudentGradeTracker.java
