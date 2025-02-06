Requirements:
- JDK 21
- JUnit 5.8.1
- JFreeChart 1.0.19 (jar in project's libs/ folder)
- JCommon 1.0.23 (jar in project's libs/ folder)

Quick start:
- Clone the project
- Open the project folder in IntelliJ IDEA
- Navigate to /main/java/Main
- Click Run -> Run 'Main'

---

Sorting Algorithms Experiment visualizes the time it takes to sort arrays of Java Integer objects in the range 0 to 40000 (inclusive), of sizes 2^2, 2^3, …, 2^15, with the following properties:
* Random (using java.util.Random)
* 50% sorted (using java.util.Arrays.sort())
* 75% sorted
* 100% sorted
* 100% sorted, descending
  
and using the following algorithms:
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

---

The experiment is contained under ./main/java/Main.java. Running this driver creates directories ./experiment-results/data/, which contains timestamped CSV files labeled with the sorting algorithm used and the type of array performed on by the algorithm, and ./experiment-results/charts/, which contains visualizations of the data, also timestamped and labeled.

Visualizations are created using the [JFreeChart and JCommon](https://www.jfree.org/jfreechart/download.html) libraries, which are already downloaded into the /libs folder. If you are not using IntelliJ, make sure to add the /libs folder to your project.

---

Coded using:
IntelliJ IDEA 2024.2.2
IntelliJ IDEA (Ultimate Edition) 2023.3.5

Unit testing conducted in JUnit 5.8.1.

Complies with [Google Java Format](https://github.com/google/google-java-format).

---

UML diagram, created using [IntelliJ IDEA (Ultimate Edition)](https://www.jetbrains.com/idea/)

![UML Diagram](/uml-diagram/uml-diagram.png) 