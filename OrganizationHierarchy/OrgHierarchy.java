import java.util.*;
import java.io.*;

public class OrgHierarchy {

  private static class Employee {
    String name;
    String boss;
    String title;
    String year;

    public Employee(String name, String boss, String title, String year) {
      this.name = name;
      this.boss = boss;
      this.title = title;
      this.year = year;
    }

    @Override
    public String toString() {
      return String.format("%s (%s) %s", this.name, this.title, this.year);
    }
  }

  private static HashMap<String, Employee> employeeMap;
  private static HashMap<String, SortedSet<String>> hierarchyMap;
  private static ArrayList<String> roots;
  private static BufferedWriter writer;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("org.in"));
    writer = new BufferedWriter(new FileWriter("org_solution.out"));

    int numCases = Integer.parseInt(reader.readLine());

    String line;
    String[] employees;
    for (int i = 0; i < numCases; i++) {
      resetMaps();
      line = reader.readLine();
      printToScreenAndWriteToFile(String.format("Case #%d", i+1));
      employees = line.split("--");
      for (String employee : employees) {
        Employee e = parseToEmployee(employee);
        employeeMap.put(e.name, e);
        addEmployeeToMap(hierarchyMap, e, roots);
      }

      //SOP("=====Hierarchy=====");
      for (String root : roots) {
        recursivelyPrintHierarchy(root, hierarchyMap, employeeMap, "");
      }

    }

    reader.close();
    writer.close();
  }

  private static void resetMaps() {
    employeeMap = new HashMap<String, Employee>();
    hierarchyMap = new HashMap<String, SortedSet<String>>();
    roots = new ArrayList<String>();
  }

  private static void recursivelyPrintHierarchy(String root, HashMap<String, SortedSet<String>> hierarchyMap, HashMap<String, Employee> employeeMap, String dashes) throws IOException {
    if (hierarchyMap.get(root) == null) {
      printToScreenAndWriteToFile(dashes+employeeMap.get(root));
      return;
    }

    printToScreenAndWriteToFile(dashes+employeeMap.get(root));
    for (String e : hierarchyMap.get(root)) {
      recursivelyPrintHierarchy(e, hierarchyMap, employeeMap, dashes+"-");
    }
  }

  private static Employee parseToEmployee(String line) {
    String[] words = line.split(",");
    String name = words[0];
    String boss = words[1].equals("NULL") ? null : words[1];
    String title = words[2];
    String year = words[3];

    return new Employee(name, boss, title, year);
  }

  private static void addEmployeeToMap(HashMap<String, SortedSet<String>> hierarchyMap, Employee employee, ArrayList<String> roots) {
    if (employee.boss != null) {
      SortedSet<String> eList = hierarchyMap.get(employee.boss);
      if (eList== null) {
        eList = new TreeSet<String>();
      }

      eList.add(employee.name);
      hierarchyMap.put(employee.boss, eList);
    } else {
      roots.add(employee.name); //this is root level boss
    }
  }

  private static void printToScreenAndWriteToFile(String string) throws IOException {
    SOP(string);
    writer.write(string + "\n");
  }

  private static void SOP(Object arg) {
    System.out.println(arg);
  }
}
