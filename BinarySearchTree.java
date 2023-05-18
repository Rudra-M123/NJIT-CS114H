import java.util.Iterator;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {

    private Node<E> root;
    private Vector<E> vector;

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

    public void insert(E data){
        if(root == null)
            root = new Node<E>(data);
        
        else
            insert(data, root);
    }

    private void insert(E data, Node<E> curr){
        if(data.compareTo(curr.data) <= 0){
            if(curr.left == null){
                curr.left = new Node<E>(data);
                return;
            }

            else{
                insert(data, curr.left);
            }
        }

        else{
            if(curr.right == null){
                curr.right = new Node<E>(data);
                return;
            }

            else{
                insert(data, curr.right);
            }
        }
        
    }

    public void remove(E data) {
        if(root != null){
            //determine if data is at the root
            if(data.compareTo(root.data) == 0){
                //0 or 1 child case
                if(root.left == null || root.right == null){
                    root = root.left != null ? root.left : root.right;
                }

                //China two-child policy
                else{
                    Node<E> iop = findIOP(root.left);
                    E temp = iop.data;
                    iop.data = root.data;
                    root.data = temp;

                    if(root.left != iop){
                        Node<E> curr = root.left;
                        goBefore(curr, iop).right = iop.left;
                    }

                    else{
                        root.left = iop.left;
                    }
                }
            }
        
            else{
                remove(data, root);
            }
        }
    }

    private void remove(E data, Node<E> curr){
        if(curr != null){
            //remove from left subtree
            if(curr.left != null && data.compareTo(curr.data) <= 0){
                //remove from root of left subtree
                if(data.compareTo(curr.left.data) == 0){
                    //0 or 1 child
                    if(curr.left.left == null || curr.left.right == null)
                        curr.left = curr.left.left != null ? curr.left.left : curr.left.right;
                    
                    //China two child policy
                    else{
                        Node<E> iop = findIOP(curr.left.left);
                        E temp = iop.data;
                        iop.data = curr.left.data;
                        curr.left.data = temp;

                        if(curr.left.left != iop){
                            //travel to the parent of IOP then assign reference to IOP.right
                            curr = curr.left.left;
                            goBefore(curr, iop).right = iop.left;                        
                        }

                        else{
                            curr.left.left = curr.left.left.left;
                        }
                    }
                }

                //remove from elsewhere within left subtree
                else{
                    remove(data, curr.left);
                }
            }

            //remove from right subtree
            else{
               //remove from root of right subtree
                if(curr.right != null && data.compareTo(curr.right.data) == 0){
                    //0 or 1 child
                    if(curr.right.left == null || curr.right.right == null)
                        curr.right = curr.right.left != null ? curr.right.left : curr.right.right;
                    
                    //China two child policy
                    else{
                        Node<E> iop = findIOP(curr.right.left);
                    
                        E temp = iop.data;
                        iop.data = curr.right.data;
                        curr.right.data = temp;
    
                        if(curr.right.left != iop){
                            //travel to the parent of IOP then assign reference to IOP.right
                            curr = curr.right.left;
                            goBefore(curr, iop).right = iop.left;
                        }
    
                        else{
                            curr.right.left = curr.right.left.left;
                        }
                    }
                }

                //remove from elsewhere within right subtree
                else{
                    remove(data, curr.right);
                }
            }
        }
    }

    private Node<E> goBefore(Node<E> curr, Node<E> iop) {
        return (curr.right != iop)? goBefore(curr.right, iop) : curr;
    }

    private Node<E> findIOP(Node<E> curr){
        return (curr.right != null)? findIOP(curr.right) : curr;
    }

    public boolean search(E data){
        return search(data, root);
    }

    private boolean search(E data, Node<E> curr){
        int cmp;
        return (curr != null) ? (((cmp = data.compareTo(curr.data)) != 0) ? (cmp < 0 ? search(data, curr.left) : search(data,curr.right)) : true) : false;
    }
}