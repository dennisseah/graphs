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

import java.util.List;

public abstract interface IBinarySearchTreeBase<T extends Comparable<T>> {
    /**
     * Return the root node.
     *
     * @return root node
     */
    BinarySearchTreeNode<T> getRoot();

    /**
     * Set the root node
     *
     * @param root root node.
     */
    void setRoot(BinarySearchTreeNode<T> root);

    /**
     * Insert value into tree
     *
     * @param value node value.
     */
    void insert(T value);

    /**
     * Return a list of nodes that matches a given value.
     *
     * @param value value to match.
     * @return list of matching nodes.
     */
    List<BinarySearchTreeNode<T>> find(T value);

    /**
     * Remove nodes from tree.
     *
     * @param value value to match.
     * @return true if there are one or more nodes that are removed.
     */
    boolean remove(T value);

    /**
     * Return true if tree is valid.
     * 
     * @return true if tree is valid.
     */
    abstract boolean isValid();
}
