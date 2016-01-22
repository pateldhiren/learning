package ctci;

public class ctci47 {

	static void chkDependency(Node n[])
	{
		int num = n.length,i,j,count,tCount,pCount=0;
	while(true)
	{
		tCount=0;
		for(i=1;i<num;i++)
		{
			count = 0;
			if(n[i]!=null){
			for(j=0;j<n[i].count;j++)
			{
				if(n[i].next[j]!=null)
					count++;
			}
			if(count==0)
			{
				System.out.println(n[i].num);
				pCount++;
				n[i]=null;
				if(n[1]!=null)
				n[1].next[0] = n[6];
				if(n[2]!=null)
				n[2].next[0]=n[6];
				if(n[3]!=null)
				n[3].next[0]=n[4];
				if(n[4]!=null){
		        n[4].next[0]=n[1];n[4].next[1]=n[2];}
				if(n[6]!=null)
				n[6].next[0]=n[1];
				tCount++;
			}
			}
			
		}
		if(tCount==0)
			break;
	}
	if(pCount<6)
		System.out.println("All Project order not possible");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Node n[] = new Node[7];
        n[0] = null;
        n[1]=new Node(1);n[2] = new Node(2);n[3] = new Node(3);n[4] = new Node(4);
        n[5] = new Node(5);n[6] = new Node(6);
        n[1].next = new Node[1];n[2].next = new Node[1];
        n[3].next = new Node[1];n[4].next = new Node[2];
        n[1].next[0] = n[6];n[2].next[0]=n[6];n[3].next[0]=n[4];
        n[4].next[0]=n[1];n[4].next[1]=n[2];
        n[1].count=1; n[2].count=1;
        n[3].count=1; n[4].count=2;
      n[6].next = new Node[1];
      n[6].next[0]=n[1];
      n[6].count=1;
        chkDependency(n);
        
        
        }

}
