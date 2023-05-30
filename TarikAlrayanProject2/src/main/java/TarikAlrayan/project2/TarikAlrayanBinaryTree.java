/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TarikAlrayan.project2;

/*
* @file Project2
* @description spell checker project  
* @assignment 2 
* @date 9/04/2023
* @author TARIK ALRAYAN tarik.alrayan@stu.fsm.edu.tr
*/

// a class to implement the binary tree data structure 
public class TarikAlrayanBinaryTree<T extends Comparable<T>> {

    private TarikAlrayanNode<T> root;

    public TarikAlrayanBinaryTree() {

    }

    public TarikAlrayanNode getRoot() {
        return root;
    }

    public void setRoot(TarikAlrayanNode root) {
        this.root = root;
    }

    public void insert(T data) {
        if (root == null) {
            root = new TarikAlrayanNode<>(data);
        } else {
            insertNode(root, data);
        }
    }

    //insert a node to the tree
    private void insertNode(TarikAlrayanNode<T> node, T data) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new TarikAlrayanNode<>(data));
            } else {
                insertNode(node.getLeft(), data);
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new TarikAlrayanNode<>(data));
            } else {
                insertNode(node.getRight(), data);
            }
        }
    }


    //prints the tree in order
    public void printTreeInorder(TarikAlrayanNode<T> root) {
        TarikAlrayanNode<T> temp = root;
        if (temp != null) {
            printTreeInorder(temp.getLeft());
            System.out.print(temp.getData() + " ");
            printTreeInorder(temp.getRight());
        }
    }

}