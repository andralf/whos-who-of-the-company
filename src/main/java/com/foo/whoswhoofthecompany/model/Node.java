package com.foo.whoswhoofthecompany.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Node<E> {

	private E element;
	private List<Node> children;
	private Node<E> parent;

	public static final String NODE_DENOTER = "->";
	public static final String CHILD_NEW_LINE = System.lineSeparator();
	public static final String CHILD_LEVEL_INDENTER = "\t";

	public Node(E element) {
		super();
		this.element = element;
		this.children = new ArrayList<>();
	}

	public List<Node> getChildren() {
		return children;
	}

	public E getElement() {
		return element;
	}

	public Node<E> getParent() {
		return parent;
	}

	public void setParent(Node<E> parent) {
		this.parent = parent;
	}

	private int getDepth() {
		int depth = 1;
		if (parent != null) {
			depth += parent.getDepth();
		}

		return depth;
	}

	public String getTree() {
		StringBuilder sb = new StringBuilder();
		sb.append(NODE_DENOTER).append(element.toString());

		children.stream().forEach(n -> {
			sb.append(CHILD_NEW_LINE);
			IntStream.rangeClosed(1, getDepth()).forEach(i -> sb.append(CHILD_LEVEL_INDENTER));
			sb.append(n.getTree());
		});

		return sb.toString();
	}
}
