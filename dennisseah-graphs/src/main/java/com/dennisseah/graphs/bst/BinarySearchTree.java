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

public class BinarySearchTree<T extends Comparable<T>> extends BinarySearchTreeBase<T> implements IBinarySearchTree<T> {
    /**
     * Construct a empty tree.
     */
    public BinarySearchTree() {
        super();
    }

    /**
     * Construct a binary search tree with an array of values.
     * 
     * @param values values for the nodes
     * @throws InvalidBinaryTreeException if the constructed tree is invalid.
     */
    public BinarySearchTree(T[] values) throws InvalidBinaryTreeException {
        super(values);
    }

    @Override
    public boolean isBalanced() {
        return (new Validator<T>(this)).isBalanced();
    }

    @Override
    public List<BinarySearchTreeNode<T>> inorderTraversal() {
        return (new TraversalOperations<T>(root)).inorderTraversal();
    }

    @Override
    public List<BinarySearchTreeNode<T>> preorderTraversal() {
        return (new TraversalOperations<T>(root)).preorderTraversal();
    }

    @Override
    public List<BinarySearchTreeNode<T>> postorderTraversal() {
        return (new TraversalOperations<T>(root)).postorderTraversal();
    }

    @Override
    public boolean isValid() {
        return (new Validator<>(this)).isValid();
    }

    @Override
    public boolean balanced() {
        if (root == null || this.isBalanced()) {
            return false;
        }

        List<T> sortedValues = new ArrayList<>();
        for (BinarySearchTreeNode<T> node : inorderTraversal()) {
            sortedValues.add(node.getValue());
        }
        this.root = null;
        balanceInsert(sortedValues, sortedValues.size() / 2);

        return true;
    }

    private void balanceInsert(List<T> list, int index) {
        insert(list.get(index));
        if (index > 0) {
            List<T> left = list.subList(0, index);
            balanceInsert(left, left.size() / 2);
        }
        if (index + 1 < list.size()) {
            List<T> right = list.subList(index + 1, list.size());
            balanceInsert(right, right.size() / 2);
        }
    }
}