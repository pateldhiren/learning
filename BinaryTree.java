package ctci;
import java.util.LinkedList;
import java.util.Queue;

class Node1
{
	int num;
	Node1 next = null;
	Node1()
	{
		num = 0;
	}
	Node1(int num)
	{
		this.num = num;
	}
}
class tNode
{
	int num;
	int level;
	int balfactor;
	int ht = 0;
	tNode left = null;
	tNode right = null;
	tNode(int n)
	{
	this.num = n;
	}
}

class holdtNode
{
	tNode t = null;
}
public class ctci43 {
    static void insert(tNode t,int num)
    {
    	tNode temp = new tNode(num);
    	
    	while(true)
    	{
    		if(num >t.num)
    		{
    			if(t.right == null)
    			{	t.right = temp;
    			return;}
    			else
    			t = t.right;
    		}
    		else
    		{
    			if(t.left == null)
    			{	t.left = temp;
    			return;}
    			else
    			t = t.left;
    		}
    	}
    	
    }
    
    static void inorder(tNode t)
    {
    	if(t!=null)
    	{
    		inorder(t.left);
    		System.out.print(t.num +" " + t.level);
    		System.out.print("    ");
    		inorder(t.right);
    	}
    }
    
    static void insertll(Node1 n,int num)
    {
    	Node1 n1 = new Node1(num);
    	while(n.next!=null)
    		n = n.next;
    	n.next = n1;
    }
    static void displayll(Node1 n[],int num)
    {
    	for(int i = 0;i<num;i++)
    	{
    		while(n[i]!=null)
    		{
    			System.out.print(n[i].num);
    			System.out.print(" ");
    			n[i]=n[i].next;
    		}
    		System.out.println();
    	}
    }
    static int leftDepth = -1, rightDepth = 100;
    static void bfs(Queue<tNode> q,Node1 n[])
    {
    	
		while(q.isEmpty()==false)
		{
			tNode temp = q.remove();
			//code to check balancing
			if(temp.left!=null)
			{
				q.add(temp.left);
				temp.left.level = temp.level + 1;
				if(n[temp.left.level] == null)
					n[temp.left.level] = new Node1(temp.left.num);
				else
				insertll(n[temp.left.level],temp.left.num);
			}
			if(temp.right!=null)
			{
				q.add(temp.right);
				temp.right.level = temp.level + 1;
				if(n[temp.right.level] == null)
					n[temp.right.level] = new Node1(temp.right.num);
				else
				insertll(n[temp.right.level],temp.right.num);
			}
		}
    }
    static void middle(tNode t,int a[],int s,int e)
    {
    	if(s<e)
    	{
    	int mid = (s+e)/2;
    	if(t.num == 0)
    		t.num = a[mid];
    	else
    	    insert(t,a[mid]);
    	middle(t,a,s,mid);
    	middle(t,a,mid+1,e);
    	}
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
    	if(Math.abs(diff)>1)
    		return false;
    	else
    		return chkBal1(t.left) && chkBal1(t.right);
    	
    }
    
    static boolean chkBST(tNode t,Integer min, Integer max)
    {
    	if(t!=null)
    	{
    	if(min != null && t.num <= min)
    		return false;
    	else if(max != null && t.num > max)
    		return false;
    	return chkBST(t.left,min,t.num) && chkBST(t.right,t.num,max);
    	
    	}
    	return true;
    }
    static holdtNode ht = new holdtNode();
    static void printSuccessor(tNode t,tNode t1)
    {
    	if(t!=null)
    	{
    		printSuccessor(t.left,t1);
    		if(ht.t !=null){
    			System.out.println(t.num);
    			ht.t = null;
    			return;
    		}
    		if(t == t1)
    		ht.t = t;
    		
    		printSuccessor(t.right,t1);
    	}
    }
    static int commonAnce=-1000;
    static boolean commonAncestor(tNode t,int num1,int num2)
    {
    	boolean left,right;
    	if(t!=null)
    	{
    		left = commonAncestor(t.left,num1,num2);
    		right = commonAncestor(t.right,num1,num2);
    		if(left == true && right == true)
    			commonAnce=t.num;
    		
    		return left || right || (t.num==num1) || (t.num==num2);
    	}
    	else
    		return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,j,a[]={2,4,5,6,8,10,20};
		tNode t = new tNode(0);
		/*for(i=0;i<a.length;i++)
		{
			insert(t,a[i]);
		}
		*/
		middle(t,a,0,a.length);
		
	   // tNode temp = new tNode(7);
		//t.left.right.left = temp;
		//temp = new tNode(6);
		//t.left.right.left.left = temp;
		insert(t,21);insert(t,22);
		//insert(t,4);insert(t,2);insert(t,6);insert(t,10);insert(t,20);		
		Node1 n [] = new Node1[10];		
		Queue<tNode>  q= new LinkedList<tNode>();
		q.add(t);
		t.level = 0;
		n[0] = new Node1(t.num);
		bfs(q,n);
		//t.left.right.num=3;
		inorder(t);
		//System.out.println();
		//displayll(n,5);
		
		boolean ans = chkBal1(t);
		System.out.println();
		if(ans == true)
			System.out.println("Balanced");
		else
			System.out.println("Unbalanced");
		
		boolean ans2= chkBST(t,null,null);
		if(ans2 == true)
			System.out.println("BST");
		else
			System.out.println("Not a BST");
		System.out.print("Successor : ");
		printSuccessor(t,t.left);
		
	   // temp = new tNode(1);
		//t.left.left.right = temp;
		boolean ans3 = commonAncestor(t,2,20);
			System.out.println("Common Ancestor : " + commonAnce);
		
	}

}
