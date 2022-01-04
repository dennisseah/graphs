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

class DeleteReplacementData<T extends Comparable<T>> {
    BinarySearchTreeNode<T> locateReplacementNode(BinarySearchTreeNode<T> target) {
        if (target.right == null) {
            return target.left;
        }
        if (target.left == null) {
            return target.right;
        }
        if (target.right.left == null) {
            target.right.left = target.left;
            return target.right;
        }
        return null;
    }

    T removeLeftmostNode(BinarySearchTreeNode<T> target) {
        BinarySearchTreeNode<T> pprev = target.right;
        BinarySearchTreeNode<T> prev = pprev.left;
        BinarySearchTreeNode<T> cur = prev.left;

        while (cur != null) {
            pprev = prev;
            prev = cur;
            cur = cur.left;
        }

        pprev.left = prev.right;
        return prev.getValue();
    }
}