import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinarySearchTree<Integer>();
        int num = args.length == 1? Integer.parseInt(args[0]) : 11;
        Random rand =  new Random(1);
        
        System.out.println("insert");
        for(int i =0; i < num; ++i){
                int n = rand.nextInt(num);
                tree.insert(n);
                System.out.print(n + ": ");
                for(Integer j : tree){
                    System.out.print(j + " ");
                }

                System.out.println();
        }

        System.out.println("search");
        for(int i =0; i < num/2; ++i){
            int n = rand.nextInt(num);
            System.out.println(n + ": " + tree.search(n));            
        }

        rand = new Random(1);
        System.out.println("remove");
        for(int i =0; i < num; ++i){
            int n = rand.nextInt(num);
            tree.remove(n);
            System.out.print(n + ": ");
            for(Integer j : tree){
                System.out.print(j + " ");
            }

            System.out.println();
        }
    }
}

abstract class BinaryTree<E> implements Iterable<E> {

    protected class Node<T> {

        protected Node(T data) {
            this.data = data;
        }

        protected T data;
        protected Node<T> left;
        protected Node<T> right;
    }

    public abstract void insert(E data);
    public abstract void remove(E data);
    public abstract boolean search(E data);

    protected Node<E> root;
}
