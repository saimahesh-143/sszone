
package org.jsp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static ArrayList<Task> tasks = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			displayMenu();
			int choice = getUserChoice();

			switch (choice) {
			case 1:
				addTask();
				break;
			case 2:
				removeTask();
				break;
			case 3:
				listTasks();
				break;
			case 4:
				System.out.println("Exiting Task Manager. Goodbye!");
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 4.");
			}
		}
	}

	private static void displayMenu() {
		System.out.println("\n==== Task Manager Menu ====");
		System.out.println("1. Add Task");
		System.out.println("2. Remove Task");
		System.out.println("3. List Tasks");
		System.out.println("4. Exit");
	}

	private static int getUserChoice() {
		int choice = 0;
		boolean validInput = false;

		while (!validInput) {
			try {
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Consume the invalid input
			}
		}

		return choice;
	}

	private static void addTask() {
		System.out.println("\n==== Add Task ====");
		scanner.nextLine(); // Consume the newline character left by previous nextInt()

		System.out.print("Enter task name: ");
		String name = scanner.nextLine();

		System.out.print("Enter task description: ");
		String description = scanner.nextLine();

		System.out.print("Enter due date: ");
		String dueDate = scanner.nextLine();

		Task newTask = new Task(name, description, dueDate);
		tasks.add(newTask);

		System.out.println("Task added successfully!");
	}

	private static void removeTask() {
		System.out.println("\n==== Remove Task ====");
		listTasks();

		if (tasks.isEmpty()) {
			System.out.println("No tasks to remove.");
			return;
		}

		int taskIndex = getTaskIndex();

		if (taskIndex != -1) {
			tasks.remove(taskIndex);
			System.out.println("Task removed successfully!");
		}
	}

	private static void listTasks() {
		System.out.println("\n==== List of Tasks ====");

		if (tasks.isEmpty()) {
			System.out.println("No tasks available.");
			return;
		}

		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);
			System.out.println("Task #" + (i + 1));
			System.out.println("Name: " + task.name);
			System.out.println("Description: " + task.description);
			System.out.println("Due Date: " + task.dueDate);
			System.out.println("---------------------");
		}
	}

	private static int getTaskIndex() {
		System.out.print("Enter the number of the task to remove: ");
		int taskNumber = scanner.nextInt();

		if (taskNumber < 1 || taskNumber > tasks.size()) {
			System.out.println("Invalid task number. Please enter a valid task number.");
			return -1;
		}

		return taskNumber - 1;
	}

}

class Task {
	String name;
	String description;
	String dueDate;

	public Task(String name, String description, String dueDate) {
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
	}
}
