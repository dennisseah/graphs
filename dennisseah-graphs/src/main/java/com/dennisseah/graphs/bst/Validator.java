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

public class Validator<T extends Comparable<T>> {
    private BinarySearchTree<T> tree;

    Validator(BinarySearchTree<T> tree) {
        this.tree = tree;
    }

    boolean isBalanced() {
        return isBalanced(this.tree.root);
    }

    /**
     * Return true if the tree is valid.
     * e.g. of invalid tree is
     * 
     * . . .1
     * . ./ . \
     * . 2 . . 3
     * where is should be
     * 
     * . . .2
     * . ./ . \
     * . 1 . . 3
     *
     * @return true if tree is valid.
     */
    boolean isValid() {
        return this.isValid(tree.root);
    }

    private boolean isBalanced(BinarySearchTreeNode<T> node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(tree.height(node.left) - tree.height(node.right)) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private boolean validCheck(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> node, boolean lesser) {
        if (node == null) {
            return true;
        }

        if (lesser) {
            if (parent.getValue().compareTo(node.getValue()) <= 0) {
                return false;
            }
        } else {
            if (parent.getValue().compareTo(node.getValue()) > 0) {
                return false;
            }
        }

        return this.validCheck(node, node.left, true) && this.validCheck(node, node.right, false);
    }

    @SuppressWarnings("java:S1126")
    private boolean isValid(BinarySearchTreeNode<T> node) {
        if (node == null) {
            return true;
        }

        if (!this.validCheck(node, node.left, true) || !this.isValid(node.left)) {
            return false;
        }

        if (!this.validCheck(node, node.right, false) || !this.isValid(node.right)) {
            return false;
        }

        return true;
    }
}
