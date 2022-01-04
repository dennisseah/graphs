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

public class BinarySearchTreeNode<T extends Comparable<T>> {
    private T value;
    BinarySearchTreeNode<T> left;
    BinarySearchTreeNode<T> right;

    /**
     * Instantiate an instance of this class with node value.
     * the left and right nodes shall be null.
     *
     * @param value node value.
     */
    public BinarySearchTreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Instantiate an instance of this class.
     *
     * @param value node value.
     * @param left  reference to left node.
     * @param right reference to right node.
     */
    public BinarySearchTreeNode(
            T value,
            BinarySearchTreeNode<T> left,
            BinarySearchTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Return the node value.
     * 
     * @return node value.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Set the node value.
     *
     * @param value node value.
     * @throws NullValueException if node value is null
     */
    public void setValue(T value) throws NullValueException {
        if (value == null) {
            throw new NullValueException("Node value is required.");
        }
        this.value = value;
    }

    /**
     * Return the reference of left node.
     *
     * @return reference to left node.
     */
    public BinarySearchTreeNode<T> getLeft() {
        return this.left;
    }

    /**
     * Set the left node.
     *
     * @param left reference to left node.
     */
    public void setLeft(BinarySearchTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Return the reference of right node.
     *
     * @return reference to right node.
     */
    public BinarySearchTreeNode<T> getRight() {
        return this.right;
    }

    /**
     * Set the right node.
     *
     * @param right reference to right node.
     */
    public void setRight(BinarySearchTreeNode<T> right) {
        this.right = right;
    }
}
