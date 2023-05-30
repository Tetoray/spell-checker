/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package TarikAlrayan.project2;

/*
* @file Project2
* @description spell checker project  
* @assignment 2 
* @date 9/04/2023
* @author TARIK ALRAYAN tarik.alrayan@stu.fsm.edu.tr
*/

//this class implement a node data structer to work with binary trees
public class TarikAlrayanNode<T extends Comparable<T>> {

    private T data;
    private TarikAlrayanNode left;
    private TarikAlrayanNode right;
    
    TarikAlrayanNode(T value){
        this.data=value;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(TarikAlrayanNode left) {
        this.left = left;
    }

    public void setRight(TarikAlrayanNode right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public TarikAlrayanNode getLeft() {
        return left;
    }

    public TarikAlrayanNode getRight() {
        return right;
    }
    
    
}
