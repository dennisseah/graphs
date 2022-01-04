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

public abstract class BinarySearchTreeBase<T extends Comparable<T>> {
    BinarySearchTreeNode<T> root;

    /**
     * Construct a empty tree.
     */
    BinarySearchTreeBase() {
        this.root = null;
    }

    /**
     * Construct a binary search tree with an array of values.
     * 
     * @param values values for the nodes
     * @throws InvalidBinaryTreeException if the constructed tree is invalid.
     */
    BinarySearchTreeBase(T[] values) throws InvalidBinaryTreeException {
        this.root = this.build(values);
        if (!this.isValid()) {
            throw new InvalidBinaryTreeException("Tree is invalid");
        }
    }

    /**
     * Return the root node.
     *
     * @return root node
     */
    public BinarySearchTreeNode<T> getRoot() {
        return this.root;
    }

    /**
     * Set the root node
     *
     * @param root root node.
     */
    public void setRoot(BinarySearchTreeNode<T> root) {
        this.root = root;
    }

    /**
     * Insert value into tree
     *
     * @param value node value.
     */
    public void insert(T value) {
        BinarySearchTreeNode<T> node = new BinarySearchTreeNode<>(value);

        if (this.root == null) {
            this.root = node;
        } else {
            insert(this.root, node);
        }
    }

    public abstract boolean isValid();

    /**
     * Return height of the tree.
     *
     * @return height.
     */
    public int height() {
        return height(root);
    }

    /**
     * Return height of the sub tree.
     *
     * @param node root node of sub tree.
     * @return height of the sub tree.
     */
    public int height(BinarySearchTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Construct a binary search tree with an array of values.
     * The format of the array is as follows
     * [root, root.left, root.right, root.left.left, root.left.right,
     * root.right.left, root.right.right, ...]
     * item-0 is the root node
     * item-1 is the root's left node
     * item-2 is the root's right node
     * the left and right node of node is 2 to the power node index and 2 to the
     * power node index + 1
     * respectively.
     * e.g.
     * the left and right node of root's left node (which is index 1 in the array)
     * is
     * (2pow1) and (2pow1 +1) respectively.
     * 
     * [ 4, 2, 6, 1, 3, 5, 7 ]
     * ..|..L .R
     * ..+-+---+
     *
     * [ 4, 2, 6, 1, 3, 5, 7 ]
     * .....|.... L. R
     * .....+-----+--+
     * 
     * [ 4, 2, 6, 1, 3, 5, 7 ]
     * ........|....... L. R
     * ........+--------+--+
     * 
     * @param values values for the nodes
     * @return root of tree.
     */
    private BinarySearchTreeNode<T> build(T[] values) {
        BinarySearchTreeNode<T> newRoot = null;

        if (values != null && values.length > 0) {
            newRoot = new BinarySearchTreeNode<>(values[0]);

            if (values.length > 1) {
                newRoot.left = new BinarySearchTreeNode<>(values[1]);
                this.buildInsert(newRoot.left, values, 1);

                newRoot.right = new BinarySearchTreeNode<>(values[2]);
                this.buildInsert(newRoot.right, values, 2);
            }
        }
        return newRoot;
    }

    private void buildInsert(BinarySearchTreeNode<T> parent, T[] values, int idx) {
        int sz = values.length;
        int pow = (int) Math.pow(2, idx);
        int leftIdx = pow + 1;
        int rightIdx = pow + 2;

        if (leftIdx < sz) {
            parent.left = new BinarySearchTreeNode<>(values[leftIdx]);
            this.buildInsert(parent.left, values, leftIdx);
        }
        if (rightIdx < sz) {
            parent.right = new BinarySearchTreeNode<>(values[rightIdx]);
            this.buildInsert(parent.right, values, rightIdx);
        }
    }

    private void insert(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> node) {
        if (node.getValue().compareTo(parent.getValue()) > 0) {
            if (parent.right == null) {
                parent.right = node;
            } else {
                this.insert(parent.right, node);
            }
        } else {
            if (parent.left == null) {
                parent.left = node;
            } else {
                this.insert(parent.left, node);
            }
        }
    }
}
