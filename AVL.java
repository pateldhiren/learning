package ctci;

import java.util.LinkedList;
import java.util.Queue;

public class AVL {
	static void bfs(Queue<tNode> q)
    {
    	
		while(q.isEmpty()==false)
		{
			tNode temp = q.remove();
			//code to check balancing
			if(temp.left!=null)
			{
				q.add(temp.left);
				temp.left.level = temp.level + 1;
				
			}
			if(temp.right!=null)
			{
				q.add(temp.right);
				temp.right.level = temp.level + 1;
				
			}
		}
    }
	
	 static int chkhei(tNode t)
	    {
	    int temp1,temp2;
	    	if(t!=null)
	    	{
	    		temp1 = chkhei(t.left) + 1;        
	            temp2 = chkhei(t.right) + 1;
	    		t.ht = Math.max(temp1,temp2) ;
	            return Math.max(temp1,temp2);
	    	}
	    else
	        return -1;
	    }
	 static tNode insert(tNode root,int val)
	    {
		 tNode t1 = root;
	    	tNode temp = new tNode(val);
	    	if(root==null){
	    		root=temp;
	    		//root.num=val;
	    		return root;}
	    	while(true)
	    	{
	    		if(val >root.num)
	    		{
	    			if(root.right == null)
	    			{	root.right = temp;
	    			break;}
	    			else
	    			root = root.right;
	    		}
	    		else
	    		{
	    			if(root.left == null)
	    			{	root.left = temp;
	    			break;}
	    			else
	    			root = root.left;
	    		}
	    	}
	    	
	    	t1 = balance(t1);
	    	chkhei(t1);
	    	return t1;
	    }
	 static tNode balance(tNode t)
	 {
		 tNode t1 = t;
		chkBal1(t);
		if(t!=null)
    	{
			
	        if(t.balfactor >= 2)
	        {	        	
	        	if(t.left.balfactor >= 1)
	        	{
	        		t = rightRotate(t);
	        		t1 = t;
	        	}
	        	else if(t.left.balfactor <= -1)
	        	{
	        		t.left = leftRotate(t.left);
	        		t = rightRotate(t);
	        		t1 = t;
	        	}	        	
	        }
	        else if(t.balfactor <= -2)
	        {
	        	if(t.right.balfactor<=-1)
	        	{
	        		t = leftRotate(t);
	        		t1 = t;
	        	}
	        	else if(t.right.balfactor >= 1)
	        	{
	        		t.right = rightRotate(t.right);
	        		t = leftRotate(t);
	        		t1 = t;
	        	}	        	
	        }
	        if(t.left!=null)
    	t.left = balance(t.left);
	        if(t.right!=null)
        t.right = balance(t.right);
    	}
		return t1;
	 }
	static int chkBal2(tNode t)
    {
    	if(t == null)
    		return -1;
    	return Math.max(chkBal2(t.left),chkBal2(t.right)) + 1;
    }
    static boolean chkBal1(tNode t)
    {
    	if(t==null)
    		return true;
    	
    	int diff = chkBal2(t.left)-chkBal2(t.right);
    	t.balfactor = diff;
    	
    		return chkBal1(t.left) && chkBal1(t.right);
    	
    }
    static void inorder(tNode t)
    {
    	if(t!=null)
    	{
    		inorder(t.left);
    		System.out.print(t.num +" " + t.level + " " + t.balfactor + " " + t.ht);
    		System.out.print("    ");
    		inorder(t.right);
    	}
    }
    static tNode rightRotate(tNode t)
    {
    	tNode t1 = t.left;
    	tNode t2 = t1.right;
    	t1.right=t;
    	t.left=t2;
    	return t1;
    }
    static tNode leftRotate(tNode t)
    {
    	tNode t1 = t.right;
    	tNode t2 = t1.left;
    	t1.left=t;
    	t.right=t2;
    	return t1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,j,a[]={14,25,21,10,23,7,26,12,30,16,19};
		tNode t = null;
		for(i=0;i<a.length;i++)
		{
			if(a[i]==19)
				System.out.println(19);
			 t = insert(t,a[i]);
		}
		
		chkBal1(t);
		Queue<tNode>  q= new LinkedList<tNode>();
		q.add(t);
		t.level = 0;
		bfs(q);
		inorder(t);
		//t.left = leftRotate(t.left);
		//t = rightRotate(t);
		q= new LinkedList<tNode>();
		q.add(t);
		t.level = 0;
		bfs(q);
		System.out.println();
		chkBal1(t);
		inorder(t);
		
	}

}
