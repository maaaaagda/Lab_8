package com.company;

/**
 * Created by Magdalena Polak on 18.04.2016.
 */
public class BST {

    class Node{
        Object value;
        Node left;
        Node right;
        Node(Object x)
        { value=x; left = right = null; }
    }

 /*   public Object find(Object x)
    {Node t = search(x);
        return t !=null ? t.value : null;
    }

    private Node search(Object value) {
        Node node = _root;
        int cmp=0;
        while (node != null &&(cmp = _comparator.compare(value, node.value))!=0)
            node = cmp < 0 ? node.left : node.right;
        return node;
    }
    public void insert(Object x)
    { _root= insert(x,_root); }

    protected Node insert(Object x, Node t) {
        if(t== null) t=new Node(x);
        else { int cmp=_comparator.compare(x,t.value);
            if(cmp<0) t.left=insert(x,t.left);
            else if(cmp>0) t.right=insert(x,t.right);
            else throw new DuplicateItemException(x.toString());
        }
        return t;
    }

*/
}
