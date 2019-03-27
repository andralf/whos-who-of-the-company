package com.foo.whoswhoofthecompany;

import com.foo.whoswhoofthecompany.model.Employee;
import com.foo.whoswhoofthecompany.model.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeTreeGeneratorTest {

	private static EmployeeTreeGenerator employeeTreeGenerator;

	@Before
	public void before() {
		employeeTreeGenerator = new EmployeeTreeGenerator();
	}

	@Test
	public void when_all_employees_are_valid__then_tree_should_contain_the_hierarchy_of_all_employees() {
		List<Employee> employees = getEmployees();

		String tree = employeeTreeGenerator.generate(employees);
		System.out.println(tree);
		String[] treeLineArr = tree.split(Node.CHILD_NEW_LINE);
		assertEquals(employees.size(), treeLineArr.length);
		assertEquals(Node.NODE_DENOTER + "Jamie", treeLineArr[0]);
		assertEquals(Node.CHILD_LEVEL_INDENTER + Node.NODE_DENOTER + "Alan", treeLineArr[1]);
		assertEquals(Node.CHILD_LEVEL_INDENTER + Node.CHILD_LEVEL_INDENTER +
				Node.NODE_DENOTER + "Martin", treeLineArr[2]);
		assertEquals(Node.CHILD_LEVEL_INDENTER + Node.CHILD_LEVEL_INDENTER +
				Node.NODE_DENOTER + "Alex", treeLineArr[3]);
		assertEquals(Node.CHILD_LEVEL_INDENTER + Node.NODE_DENOTER + "Steve", treeLineArr[4]);
		assertEquals(Node.CHILD_LEVEL_INDENTER + Node.CHILD_LEVEL_INDENTER +
				Node.NODE_DENOTER + "David", treeLineArr[5]);
	}

	private List<Employee> getEmployees() {
		return Arrays.asList(
				new Employee(100, "Alan", 150),
				new Employee(220, "Martin", 100),
				new Employee(150, "Jamie", null),
				new Employee(275, "Alex", 100),
				new Employee(400, "Steve", 150),
				new Employee(190, "David", 400)
		);
	}

	@Test
	public void when_an_employee_has_an_non_existing_manager__then_it_should_not_be_displayed_on_the_tree() {
		List<Employee> employees = new ArrayList<>(getEmployees());
		Employee employeeWithoutManager = new Employee(49, "Mary", 310);
		employees.add(employeeWithoutManager);

		String tree = employeeTreeGenerator.generate(employees);
		System.out.println(tree);
		String[] treeLineArr = tree.split(Node.CHILD_NEW_LINE);
		assertEquals(6, treeLineArr.length);
		assertTrue(!tree.contains(employeeWithoutManager.getName()));
	}

	@Test
	public void when_an_employee_has_an_no_manager_and_no_direct_reports__then_it_should_not_be_displayed_on_the_tree() {
		List<Employee> employees = new ArrayList<>(getEmployees());
		Employee employeeWithoutManagerAndDirectReports = new Employee(333, "Drake", null);
		employees.add(employeeWithoutManagerAndDirectReports);

		String tree = employeeTreeGenerator.generate(employees);
		System.out.println(tree);
		String[] treeLineArr = tree.split(Node.CHILD_NEW_LINE);
		assertEquals(6, treeLineArr.length);
		assertTrue(!tree.contains(employeeWithoutManagerAndDirectReports.getName()));
	}
}