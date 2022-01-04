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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.function.Function;

import org.junit.Test;

public class BinarySearchTreeTest {
    private Function<BinarySearchTreeNode<Integer>, Integer> fnNodeValue = new Function<BinarySearchTreeNode<Integer>, Integer>() {
        public Integer apply(BinarySearchTreeNode<Integer> node) {
            return node.value;
        }
    };

    private BinarySearchTree<Integer> createTree(Integer[] values) throws InvalidBinaryTreeException {
        return new BinarySearchTree<Integer>(values);
    }

    @Test
    public void createEmptyBST() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertNull(tree.root);
    }

    @Test
    public void createBSTWithoutValues() throws InvalidBinaryTreeException {
        BinarySearchTree<String> tree = new BinarySearchTree<>(new String[] {});
        assertNull(tree.root);
    }

    @Test(expected = InvalidBinaryTreeException.class)
    public void validCheckFalse() throws InvalidBinaryTreeException {
        createTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
    }

    @Test
    public void validCheckTrue() throws InvalidBinaryTreeException {
        BinarySearchTree<Integer> tree = createTree(new Integer[] { 4, 2, 6, 1, 3, 5, 7 });
        assertTrue(tree.isValid());
    }

    @Test
    public void inorderTraversal() throws InvalidBinaryTreeException {
        BinarySearchTree<Integer> tree = createTree(new Integer[] { 4, 2, 6, 1, 3, 5, 7 });
        Integer[] expecteds = new Integer[] { 1, 2, 3, 4, 5, 6, 7 }; // sorted
        Object[] actuals = tree.inorderTraversal().stream().map(fnNodeValue).toArray();
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void preorderTraversal() throws InvalidBinaryTreeException {
        BinarySearchTree<Integer> tree = createTree(new Integer[] { 4, 2, 6, 1, 3, 5, 7 });
        Integer[] expecteds = new Integer[] { 4, 2, 1, 3, 6, 5, 7 };
        Object[] actuals = tree.preorderTraversal().stream().map(fnNodeValue).toArray();
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void postorderTraversal() throws InvalidBinaryTreeException {
        BinarySearchTree<Integer> tree = createTree(new Integer[] { 4, 2, 6, 1, 3, 5, 7 });
        Integer[] expecteds = new Integer[] { 1, 3, 2, 5, 7, 6, 4 };
        Object[] actuals = tree.postorderTraversal().stream().map(fnNodeValue).toArray();
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void insertTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        for (int i : Arrays.asList(new Integer[] { 7, 1, 5, 6, 4, 2, 3 })) {
            tree.insert(i);
        }

        Integer[] expecteds = new Integer[] { 1, 2, 3, 4, 5, 6, 7 }; // sorted
        Object[] actuals = tree.inorderTraversal().stream().map(fnNodeValue).toArray();
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void heightTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        for (int i : Arrays.asList(new Integer[] { 7, 1, 5, 6, 4, 2, 3, 8, 9 })) {
            tree.insert(i);
        }

        /*
         * Level-1......... 7
         * ...............--^--
         * Level-2....... 1....8
         * ................\....\
         * Level-3..........5....9
         * ...............--^--
         * Level-4........4....6
         * ............../
         * Level-5......2
         * ............/
         * Level-6....3
         */
        assertEquals(6, tree.height());
    }

    @Test
    public void inbalanceFalseTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        for (int i : Arrays.asList(new Integer[] { 7, 1, 5, 6, 4, 2, 3, 8, 9 })) {
            tree.insert(i);
        }

        /*
         * Level-1......... 7
         * ...............--^--
         * Level-2....... 1....8
         * ................\....\
         * Level-3..........5....9
         * ...............--^--
         * Level-4........4....6
         * ............../
         * Level-5......2
         * ............/
         * Level-6....3
         */
        assertFalse(tree.isBalanced());
    }

    @Test
    public void inbalanceTreeTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        for (int i : Arrays.asList(new Integer[] { 4, 2, 1, 3, 6, 5, 7 })) {
            tree.insert(i);
        }

        assertTrue(tree.isBalanced());
    }
}
