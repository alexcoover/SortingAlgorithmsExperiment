# Sorting Algorithms Experiment

## Table of Contents

1. [Description](#description)
2. [Requirements](#requirements)
3. [Demo](#demo)
4. [Running the Experiment](#running-the-experiment)
5. [Development Environment](#development-environment)
6. [UML Diagram](#uml-diagram)

## Description

The Sorting Algorithms Experiment project uses Java to visualize the time it takes to sort arrays consisting of Integers in the range of 0 to 40000 (inclusive). These arrays range in size from 2^2, 2^3, …, to 2^15, with the following properties:
* Random elements (using java.util.Random)
* First 50% sorted (using java.util.Arrays.sort())
* First 75% sorted
* 100% sorted
* 100% sorted, descending

It tests the following algorithms:
* Bubble Sort
* Bucket Sort
* Counting Sort
* Heap Sort
* Insertion Sort
* Merge Sort
* Quick Sort (first element as pivot)
* Quick Sort (median of threes pivot)
* Quick Sort (random element as pivot)
* Radix Sort
* Selection Sort
* Shell Sort

The experiment creates CSV and chart data showing the time in microseconds for each array size. These files are created for each sorting algorithm and array group combination.

---

## Requirements

- JDK 21
- JUnit 5.8.1
- JFreeChart 1.0.19 (jar in project's libs/ folder)
- JCommon 1.0.23 (jar in project's libs/ folder)

Other versions may work but have not been tested.

---

## Demo

**Experiment**

https://github.com/user-attachments/assets/28624446-b78d-47d8-b8f2-9c52b0651f8c

**Generated Charts**

https://github.com/user-attachments/assets/e0289f4d-cb04-444f-ae88-8e5499b2e0be

---

## Running the Experiment

### Quick Start
- Clone the project
- Open the project folder in IntelliJ IDEA
- Navigate to /main/java/Main
- Click Run -> Run 'Main'

### Data Output
The experiment driver is contained under ./main/java/Main.java. Running this driver creates directories ./experiment-results/data/, which contains timestamped CSV files labeled with the sorting algorithm used and the type of array performed on by the algorithm, and ./experiment-results/charts/, which contains visualizations of the data, also timestamped and labeled.

Visualizations are created using the [JFreeChart and JCommon](https://www.jfree.org/jfreechart/download.html) libraries, which are already downloaded into the /libs folder. If you are not using IntelliJ, make sure to add the /libs folder to your project.

---

## Development Environment

- Coded using:
  - IntelliJ IDEA 2024.2.2
  - IntelliJ IDEA (Ultimate Edition) 2023.3.5
- Unit testing conducted in JUnit 5.8.1.
- Complies with [Google Java Format](https://github.com/google/google-java-format).

---

## UML Diagram

UML diagram created using [IntelliJ IDEA (Ultimate Edition)](https://www.jetbrains.com/idea/).

![UML Diagram](/uml-diagram/uml-diagram.png) 
