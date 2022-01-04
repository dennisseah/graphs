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

import java.util.List;
import java.util.function.Function;

import org.junit.Test;

public class BinarySearchTreeTest {
    private Function<BinarySearchTreeNode<Integer>, Integer> fnNodeValue = new Function<BinarySearchTreeNode<Integer>, Integer>() {
        public Integer apply(BinarySearchTreeNode<Integer> node) {
            return node.getValue();
        }
    };

    private BinarySearchTree<Integer> createTree(Integer[] values) throws InvalidBinaryTreeException {
        return new BinarySearchTree<Integer>(values);
    }

    private BinarySearchTree<Integer> createTreeWithInsert(Integer[] values) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for (int i : values) {
            tree.insert(i);
        }
        return tree;
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
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 7, 1, 5, 6, 4, 2, 3 });
        Integer[] expecteds = new Integer[] { 1, 2, 3, 4, 5, 6, 7 }; // sorted
        Object[] actuals = tree.inorderTraversal().stream().map(fnNodeValue).toArray();
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void removeTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 7, 1, 5, 6, 4, 2, 3 });
        assertTrue(tree.remove(3));
    }

    @Test
    public void removeMultipleTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 7, 1, 5, 3, 6, 4, 2, 3 });
        assertTrue(tree.remove(3));
        Integer[] expecteds = new Integer[] { 1, 2, 4, 5, 6, 7 }; // sorted
        Object[] actuals = tree.inorderTraversal().stream().map(fnNodeValue).toArray();
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void removeNoneTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 7, 1, 5, 6, 4, 2, 3 });
        assertFalse(tree.remove(13));
    }

    @Test
    public void findOneTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        List<BinarySearchTreeNode<Integer>> found = tree.find(5);
        assertEquals(1, found.size());
        assertEquals(5, found.get(0).getValue().intValue());
    }

    @Test
    public void findMultipleTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 7, 7 });
        List<BinarySearchTreeNode<Integer>> found = tree.find(7);
        assertEquals(3, found.size());
        for (BinarySearchTreeNode<Integer> node : found) {
            assertEquals(7, node.getValue().intValue());
        }
    }

    @Test
    public void findNoneTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 1, 2, 3, 4, 6, 7, 7, 7 });
        List<BinarySearchTreeNode<Integer>> found = tree.find(5);
        assertEquals(0, found.size());
    }

    @Test
    public void heightTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 7, 1, 5, 6, 4, 2, 3, 8, 9 });

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
    public void isbalanceFalseTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 7, 1, 5, 6, 4, 2, 3, 8, 9 });

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
    public void isbalanceTreeTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 4, 2, 1, 3, 6, 5, 7 });
        assertTrue(tree.isBalanced());
    }

    @Test
    public void balanceFalseTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 4, 2, 1, 3, 6, 5, 7 });
        assertFalse(tree.balanced());
    }

    @Test
    public void balanceTrueTest() {
        BinarySearchTree<Integer> tree = createTreeWithInsert(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        assertEquals(9, tree.height());
        assertTrue(tree.balanced());
        assertEquals(4, tree.height());
    }
}
