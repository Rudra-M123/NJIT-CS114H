/**
 *  File: BinarySearchTree.java
 *  Professor: Professor Kapleau
 *  Course: CS114
 *  Section: H02
 *  
 *  The code below contains is my implementation of a binary search tree. Further comments are below.
 * 
 *  kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=
 */

//import required packages
import java.util.Iterator;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {

    private Node<E> root;
    private Vector<E> vector;
    private Node<E> iop;

    //method implementation was provided beforehand
    public Iterator<E> iterator() {
        vector = new Vector<E>();
        traverse(root);
        return vector.iterator();
    }

    //method implementation was provided beforehand
    private void traverse(Node<E> curr){
        if(curr!= null){
            traverse(curr.left);
            vector.add(curr.data);
            traverse(curr.right);
        }
    }

    //method to insert at the root since tree is empty
    public void insert(E data){
        if(root == null)
            root = new Node<E>(data);
        
        else
            insert(data, root);
    }

    //method to insert at leaf since tree is not empty 
    private void insert(E data, Node<E> curr){
        //move through left subtree(s) and insert
        if(data.compareTo(curr.data) <= 0){
            if(curr.left == null){
                curr.left = new Node<E>(data);
                return;
            }

            else
                insert(data, curr.left);
            
        }
        //move through right subtree(s) and insert
        else{
            if(curr.right == null){
                curr.right = new Node<E>(data);
                return;
            }

            else
                insert(data, curr.right);

        }
        
    }

    //method to remove root node if data is located there
    public void remove(E data) {
        if(root != null){
            //determine if data is at the root
            if(data.compareTo(root.data) == 0){
                //0 or 1 child case
                if(root.left == null || root.right == null){
                    root = root.left != null ? root.left : root.right;
                }

                //two children case
                else{
                    iop = findIOP(root.left);
                    E temp = iop.data;
                    iop.data = root.data;
                    root.data = temp;

                    if(root.left != iop){
                        Node<E> curr = root.left;
                        beforeIOP(curr).right = iop.left;
                    }

                    else{
                        root.left = iop.left;
                    }
                }
            }
        
            else
                remove(data, root);            
        }
    }

    //method to remove from the node from either of the subtrees
    private void remove(E data, Node<E> curr){
        if(curr != null){
            //remove from left subtree
            if(curr.left != null && data.compareTo(curr.data) <= 0){
                //remove from root of left subtree
                if(data.compareTo(curr.left.data) == 0){
                    //0 or 1 child
                    if(curr.left.left == null || curr.left.right == null)
                        curr.left = curr.left.left != null ? curr.left.left : curr.left.right;
                    
                    //two children case
                    else{
                        iop = findIOP(curr.left.left);
                        E temp = iop.data;
                        iop.data = curr.left.data;
                        curr.left.data = temp;

                        if(curr.left.left != iop){
                            //travel to the parent of IOP then assign reference to IOP.right
                            curr = curr.left.left;
                            beforeIOP(curr).right = iop.left;                        
                        }

                        else
                            curr.left.left = curr.left.left.left;
                    }
                }

                //remove from elsewhere within left subtree
                else
                    remove(data, curr.left);
            }

            //remove from right subtree
            else{
               //remove from root of right subtree
                if(curr.right != null && data.compareTo(curr.right.data) == 0){
                    //0 or 1 child
                    if(curr.right.left == null || curr.right.right == null)
                        curr.right = curr.right.left != null ? curr.right.left : curr.right.right;
                    
                    //two children case
                    else{
                        iop = findIOP(curr.right.left);
                        E temp = iop.data;
                        iop.data = curr.right.data;
                        curr.right.data = temp;
    
                        if(curr.right.left != iop){
                            //travel to the parent of IOP then assign reference to IOP.right
                            curr = curr.right.left;
                            beforeIOP(curr).right = iop.left;
                        }
    
                        else
                            curr.right.left = curr.right.left.left;
                    }
                }

                //remove from elsewhere within right subtree
                else
                    remove(data, curr.right);
            }
        }
    }

    //helper method to locate the node prior to the IOP
    private Node<E> beforeIOP(Node<E> curr){
        return (curr.right != iop)? beforeIOP(curr.right) : curr;
    }

    //helper method to locate the node considered IOP to the parameter node
    private Node<E> findIOP(Node<E> curr){
        return (curr.right != null)? findIOP(curr.right) : curr;
    }

    public boolean search(E data){
        return search(data, root);
    }

    private boolean search(E data, Node<E> curr){
        int cmp;
        return (curr != null) ? (((cmp = data.compareTo(curr.data)) != 0) ? ((cmp < 0) ? search(data, curr.left) : search(data, curr.right)) : true) : false;
    }
}
