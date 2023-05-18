/**
 *  File: SortedList.java
 *  Professor: Professor Kapleau
 *  Name: Rudra Mehta (rm279@njit.edu)
 *  Course: CS114
 *  Section: H02
 *  
 *  The code below is the template for generating a list that is sorted as per the comparable interface.
 *  It includes methods to add, remove, retrieve, and search for elements. Detailed comments are below.
 * 
 *  kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=
 */

//import the Iterator class
import java.util.Iterator;

class SortedList<E extends Comparable<? super E>> extends List<E> {
    
    //Iterator method     
    @Override
    public Iterator<E> iterator(){
        return new Iterator<E>(){
            public boolean hasNext(){
                return curr != null;
            }
    
            public E next(){
                E temp = curr.data;
                curr = curr.next;
                return temp;
            }
            private Node<E> curr = head;
        };
    }
    
    //Overrided insert method - inserts data nodes at the start of the list 
    public void insert(E data){
        Node<E> temp = new Node<E>(data);
        if (head == null || data.compareTo(head.data) < 0) {
            temp.next = head;
            head = temp;
        }
        
        else
            insert(head, temp, data);
    }

    //Overloaded insert method - inserts a data node at positions other than the start of the list
    private void insert(Node<E> curr, Node<E> temp, E data) {
        if(curr.next == null || data.compareTo(curr.next.data) < 0) {
            temp.next = curr.next;
            curr.next = temp;
        }
        else
            insert(curr.next, temp, data);
    }

    //Overrided remove method - removes the first data node from the list
    public void remove(E data){
        if(head != null){
            if(data.compareTo(head.data) == 0)
                head = head.next;

            else
                remove(head, data);
        }
    }

    //Overloaded remove method - removes a data node from positions other than the start of the list
    private void remove(Node<E> curr, E data){
        if(curr.next != null){
            if(data.compareTo(curr.next.data) == 0){
                curr.next = curr.next.next;
                return; //breaks out of recursion after data node found at the position following the current one
            }
            remove(curr.next, data);
        }
    }
    
    //Overrided retrieve method
    public E retrieve(int index){
        return retrieve(head, index);
    }

    //Overloaded retrieve method - retrieves data from the node at the user provided index, if it exists in the list
    private E retrieve(Node<E> curr, int index){
        return((index >=0 && curr != null) ? (index == 0 ? curr.data : retrieve(curr.next, --index)) : null);
    }

    //Overrided search method
    public boolean search(E data){
        return search(head, data);
    }

    //Overloaded search method - Returns a boolean value determining if the user prodvided data exists in the list
    private boolean search(Node<E> curr, E data){
        return((curr != null) ? (data.compareTo(curr.data) == 0 ? true : search(curr.next, data)) : false);
    }
}
