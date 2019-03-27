package com.foo.whoswhoofthecompany;

import com.foo.whoswhoofthecompany.model.Employee;
import com.foo.whoswhoofthecompany.model.Node;
import com.google.common.collect.ImmutableListMultimap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableListMultimap.toImmutableListMultimap;

public class EmployeeTreeGenerator {

	public String generate(List<Employee> employees) {
		Map<Integer, Node<Employee>> employeeNodeMap = employees.stream()
				.collect(Collectors.toMap(e -> e.getId(), e -> new Node<>(e)));

		ImmutableListMultimap<Integer, Employee> managers = employees.stream()
				.filter(e -> e.getManagerId() != null)
				.collect(toImmutableListMultimap(e -> e.getManagerId(), e -> e));

		employees.stream()
				.forEach(e -> {
					Node<Employee> n = employeeNodeMap.get(e.getId());
					List<Employee> directReports = managers.get(e.getId());
					if (directReports != null && !directReports.isEmpty()) {
						directReports.stream().forEach(dr -> {
							n.getChildren().add(employeeNodeMap.get(dr.getId()));
							employeeNodeMap.get(dr.getId()).setParent(n);

						});
					}
				});

		return employeeNodeMap.values().stream()
				.filter(n -> n.getElement().getManagerId() == null)
				.filter(n -> n.getParent() != null || !n.getChildren().isEmpty())
				.map(Node::getTree)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}
}
