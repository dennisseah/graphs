/**
 * MIT License
 *
 * Copyright (c) 2022 Dennis Seah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.dennisseah.graphs.bst;

import java.util.ArrayList;
import java.util.List;

public class TraversalOperations<T extends Comparable<T>> {
    private BinarySearchTreeNode<T> root;

    public TraversalOperations(BinarySearchTreeNode<T> root) {
        this.root = root;
    }

    /**
     * Return a list of nodes after doing a in-order traversal.
     *
     * @return list of nodes
     */
    List<BinarySearchTreeNode<T>> inorderTraversal() {
        List<BinarySearchTreeNode<T>> nodes = new ArrayList<>();
        this.inorderTraversal(this.root, nodes);
        return nodes;
    }

    /**
     * Return a list of nodes after doing a pre-order traversal.
     *
     * @return list of nodes
     */
    public List<BinarySearchTreeNode<T>> preorderTraversal() {
        List<BinarySearchTreeNode<T>> nodes = new ArrayList<>();
        this.preorderTraversal(this.root, nodes);
        return nodes;
    }

    /**
     * Return a list of nodes after doing a post-order traversal.
     *
     * @return list of nodes
     */
    public List<BinarySearchTreeNode<T>> postorderTraversal() {
        List<BinarySearchTreeNode<T>> nodes = new ArrayList<>();
        this.postorderTraversal(this.root, nodes);
        return nodes;
    }

    private void inorderTraversal(BinarySearchTreeNode<T> node, List<BinarySearchTreeNode<T>> nodes) {
        if (node != null) {
            this.inorderTraversal(node.left, nodes);
            nodes.add(node);
            this.inorderTraversal(node.right, nodes);
        }
    }

    private void preorderTraversal(BinarySearchTreeNode<T> node, List<BinarySearchTreeNode<T>> nodes) {
        if (node != null) {
            nodes.add(node);
            this.preorderTraversal(node.left, nodes);
            this.preorderTraversal(node.right, nodes);
        }
    }

    private void postorderTraversal(BinarySearchTreeNode<T> node, List<BinarySearchTreeNode<T>> nodes) {
        if (node != null) {
            this.postorderTraversal(node.left, nodes);
            this.postorderTraversal(node.right, nodes);
            nodes.add(node);
        }
    }
}
