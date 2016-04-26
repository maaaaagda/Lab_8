package com.company;
import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Created by Magdalena Polak on 18.04.2016.
 */
public class BinaryTree {
    public static  Node root;
    int allNodes = 0;
    public BinaryTree(){
        this.root = null;
    }
    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
    public boolean find(int id){
        Node current = root;
        while(current!=null){
            if(current.data==id){
                return true;
            }else if(current.data>id){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return false;
    }
    public boolean delete(int id){
        Node parent = root;
        Node current = root;
        boolean isleft
                = false;
        while(current.data!=id){
            parent = current;
            if(current.data>id){
                isleft
                        = true;
                current = current.left;
            }else{
                isleft
                        = false;
                current = current.right;
            }
            if(current ==null){
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if(current.left==null && current.right==null){
            if(current==root){
                root = null;
            }
            if(isleft
                    ==true){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //Case 2 : if node to be deleted has only one child
        else if(current.right==null){
            if(current==root){
                root = current.left;
            }else if(isleft
                    ){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
        }
        else if(current.left==null){
            if(current==root){
                root = current.right;
            }else if(isleft
                    ){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
        }else if(current.left!=null && current.right!=null){

            //now we have found the minimum element in the right sub tree
            Node successor	 = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isleft
                    ){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            successor.left = current.left;
        }
        allNodes--;
        return true;
    }

    public Node getSuccessor(Node deleleNode){
        Node successsor =null;
        Node successsorParent =null;
        Node current = deleleNode.right;
        while(current!=null){
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if(successsor!=deleleNode.right){
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }
    public Node nastepnik(int data){
        Node deleleNode = new Node(data);
        Node successsor =null;
        Node successsorParent =null;
        Node current = deleleNode.right;
        while(current!=null){
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }

        if(successsor!=deleleNode.right){
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public void insert(int id){
        Node newNode = new Node(id);
        if(root==null){
            root = newNode;
            allNodes++;
            return;
        }
        Node current = root;
        Node parent = null;
        while(true){
            parent = current;
            if(id<current.data){
                current = current.left;
                if(current==null){
                    parent.left = newNode;
                    allNodes++;
                    return;
                }
            }else{
                current = current.right;
                if(current==null){
                    parent.right = newNode;
                    allNodes++;
                    return;
                }
            }
        }
    }
    void printPostorder(Node node)    {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.data + " ");
    }
    void printInorder(Node node)    {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.data + " ");

        /* now recur on right child */
        printInorder(node.right);
    }
    void printPreorder(Node node)    {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.data + " ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
    }
    void printLevelOrder()    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(root, i);
    }
    int height(Node root)    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }
    int heightRoot(Node root)    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return(lheight);
            else return(rheight);
        }
    }
    int countLeaves(Node node){
        if( node == null )
            return 0;
        if( node.left == null && node.right == null ) {
            return 1;
        } else {
            return countLeaves(node.left) + countLeaves(node.right);
        }
    }
    int next(int id)    {
        Node current = root;
        while(current!=null) {
            if (current.data == id) {
                if (current.right != null) {
                    current = current.right;
                    while (current.left != null) {
                        current = current.left;
                        right = null;
                        nodeFound = false;
                    }
                }
                else {

                   current =  findRecur(root, current);
                }
                return current.data;
            } else if (current.data > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return -1;
        }
    public static Node n = null;
    public static Boolean nodeFound = false;
    public Node right = null;
    Node findRecur(Node root, Node x){
        if(root==null) return null;
        if(root==x||( n = findRecur(root.left,x))!=null||(n=findRecur(root.right,x))!=null){
            System.out.println("root" + root.data );
            if(n!=null){
                if(root.left==n ) {
                    if(nodeFound==false)
                    {
                        right = root;
                    }
                    System.out.println("wtf" + root.data+" motfound " + nodeFound + "right" + right.data + " n " + n.data);
                    nodeFound = true;
                    return right;
                }
            }
            if(right != null)
                root =right;
            return root;
        }
        return null;
    }
    Node findRecurPrv(Node root, Node x){
        if(root==null) return null;
        if(root==x||( n = findRecurPrv(root.right,x))!=null||(n=findRecurPrv(root.left,x))!=null){
          //  System.out.println("root" + root.data + " nnn" + n.data);
            if(n!=null){
                System.out.println("roortright" + root.right.data  + " n "+ n.data );
                if(root.left==n ) {
                    if(nodeFound==false)
                    {
                        right = root;
                    }
                    System.out.println("wtf" + root.data+" motfound " + nodeFound + "right" + right.data);
                    nodeFound = true;
                    return right;
                }
            }
            if(right != null)
                root =right;
            return root;
        }
        return null;    /*
        if(root==null) return null;
        if(root==x||(n = findRecur(root.left,x))!=null||(n=findRecur(root.right,x))!=null){

            if(n!=null){
                if(root.left==n){
                    nodeFound = true;
                    System.out.println("The In Order Successor for '" + x.data + "' is "+ root.data );
                    return null;
                }
            }
            return root;
        }
        return null;*/
    }
    int previous(int id)    {
        Node current = root;
        while(current!=null){
            System.out.println("root" + current.data);
            if(current.data==id){
                System.out.println("weszło");
                if (current.left != null) {
                    current = current.left;
                    while (current.right != null) {
                        current = current.right;
                    }
                }
                else {
                    System.out.println("buedw");
                    current = findRecurPrv(root, current);
                    System.out.println("blaaa");

                }
                return current.data;
            }
            else if(current.data>id){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return -1;

    }
    int max(Node node)    {
        if(root!=null){

            if(node.right != null) {
               return max(node.right);
            }
            return node.data;
        }
        return -1;
    };
    int min(Node node)    {
        if(root!=null){

            if(node.left != null) {
                return min(node.left);
            }
            return node.data;
        }
        return -1;
    };
    int insideNodes()    {
        return allNodes - countLeaves(root) ;
    }
    int sum = 0;
    int outsideNodes(Node node)    {

        if( node == null )
            return 0;


        if( node.left == null && node.right == null ) {
          return sum + 2;
        }
        else if(node.left == null )
    {
        sum++;
        return outsideNodes(node.right);
    }
        else if(node.right == null )
        {
            sum++;
            return outsideNodes( node.left);
        }
        else
        {
           return outsideNodes(node.left) +  outsideNodes(node.right);
        }

    }
    void printGivenLevel (Node root ,int level)    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }
    public void display(Node root){
        if(root!=null){
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }
    public void displayVert(Node root){
        if(root!=null){

            System.out.print(root.data+"\n");
            System.out.println(root.left.data + " "+root.right.data);
        }
    }

        static int successor, predecessor;

        public void successorPredecessor(Node root, int val) {
            if (root != null) {
                if (root.data == val) {
                    // go to the right most element in the left subtree, it will the
                    // predecessor.
                    if (root.left != null) {
                        Node t = root.left;
                        while (t.right != null) {
                            t = t.right;
                        }
                        predecessor = t.data;
                    }
                    if (root.right != null) {
                        // go to the left most element in the right subtree, it will
                        // the successor.
                        Node t = root.right;
                        while (t.left != null) {
                            t = t.left;
                        }
                        successor = t.data;
                    }
                } else if (root.data > val) {
                    // we make the root as successor because we might have a
                    // situation when value matches with the root, it wont have
                    // right subtree to find the successor, in that case we need
                    // parent to be the successor
                    successor = root.data;
                    successorPredecessor(root.left, val);
                } else if (root.data < val) {
                    // we make the root as predecessor because we might have a
                    // situation when value matches with the root, it wont have
                    // right subtree to find the predecessor, in that case we need
                    // parent to be the predecessor.
                    predecessor = root.data;
                    successorPredecessor(root.right, val);
                }
            }
        }
    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(isRowEmpty==false)
        {

            Stack localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
            {
                Node temp = (Node) globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.data);
                    localStack.push(temp.left
                    );
                    localStack.push(temp.right);
                    if(temp.left != null ||temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<emptyLeaf*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("****......................................................****");
    }


    public static void main(String arg[]){
        BinaryTree b = new BinaryTree();
        b.insert(30);b.insert(20);b.insert(40);
        b.insert(10);b.insert(25);b.insert(50); b.insert(35);b.insert(15);b.insert(32);
      //  b.insert(2);b.insert(10);b.insert(9); b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        System.out.println("Original Tree : ");
        b.display(b.root);
        System.out.println("");
        System.out.println("Check whether Node with value 20 exists : " + b.find(20));
     //   System.out.println("Delete Node with no children (2) : " + b.delete(2));
        b.display(root);
      //  System.out.println("\nDelete Node with one child (40) : " + b.delete(40));
        b.display(root);
      //  System.out.println("\nDelete Node with Two children (10) : " + b.delete(10));
        b.display(root);
        System.out.println("\nPrint level by level:");
        b.printLevelOrder();
        System.out.println("\nPreorder");
        b.printPreorder(root);
        System.out.println("\nInorder");
        b.printInorder(root);
        System.out.println("\nPostorder");
        b.printPostorder(root);
        System.out.println("\nHeight: " + b.heightRoot(root) + " Leaves: " +b.countLeaves(root)
                + " Max: " + b.max(root) + " Min: " + b.min(root) + " InsideNodes: " + b.insideNodes() + " OutsideNodes: " + b.outsideNodes(root));

        b.successorPredecessor(root,15);
            System.out.println("\nNastepnik 15 is : " + successor
                    + " and poprzednik is : " + predecessor);
            b.successorPredecessor(root, 50);
        b.displayTree();


    }
}



