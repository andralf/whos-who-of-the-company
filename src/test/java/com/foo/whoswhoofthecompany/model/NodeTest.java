package com.foo.whoswhoofthecompany.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NodeTest {

	@Test
	public void when_node_is_root_and_without_a_child__then_node_should_only_have_one_node() {
		Node<String> n = new Node("Test");
		assertTrue(n.getChildren().isEmpty());
		assertEquals(Node.NODE_DENOTER + n.getElement(), n.getTree());
	}

	@Test
	public void when_node_has_2_children__then_node_should_have_three_nodes() {
		Node<String> e1 = new Node("E1");
		Node<String> e1_1 = new Node("E1_1");
		Node<String> e1_2 = new Node("E1_2");
		List<Node<String>> childrenNodes = Arrays.asList(e1_1, e1_2);
		e1.getChildren().addAll(childrenNodes);

		assertTrue(!e1.getChildren().isEmpty());
		assertEquals(e1.getChildren(), childrenNodes);

		String tree = e1.getTree();
		String[] treeLineArr = tree.split(Node.CHILD_NEW_LINE);
		assertEquals(3, treeLineArr.length);
		assertEquals(Node.NODE_DENOTER + "E1", treeLineArr[0]);
		assertEquals(Node.CHILD_LEVEL_INDENTER + Node.NODE_DENOTER + e1_1.getElement(), treeLineArr[1]);
		assertEquals(Node.CHILD_LEVEL_INDENTER + Node.NODE_DENOTER + e1_2.getElement(), treeLineArr[2]);
	}

}