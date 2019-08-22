/* http://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/ */

import java.util.*;
import java.io.*;

public class Solution {
	public static int idx;
	
	public static void main(String[] args) {
		int[][] nodeinfo= {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
	}

	public static int[][] solution(int[][] nodeinfo) {
		List<node> nodes=new ArrayList<node>();
		for(int i=0;i<nodeinfo.length;i++) {
			nodes.add(new node(nodeinfo[i][0],nodeinfo[i][1],i+1));
		}
		int[][] answer=new int[2][nodes.size()];

		Collections.sort(nodes);

		tree tree=new tree(nodes.get(0));
		for(int i=1;i<nodes.size();i++) {
			tree New=new tree(nodes.get(i));
			add(tree,New);
		}

		idx=0;
		preorder(answer[0],tree);
		idx=0;
		postorder(answer[1],tree);

		return answer;
	}

	public static void add(tree root,tree New) {
		if(New.data.y<root.data.y) {
			if(New.data.x<root.data.x) {
				//root보다 x 작을 때
				if(root.left==null) {
					root.addleft(New);
					return;
				}else {
					add(root.left,New);
				}
			}else {
				//root보다 y 작을 때
				if(root.right==null) {
					root.addright(New);
					return;
				}else {
					add(root.right,New);
				}
			}
		}
	}

	public static void preorder(int[] answer,tree tree) {
		if(tree==null)
			return;
		answer[idx++]=tree.data.number;
		preorder(answer,tree.left);
		preorder(answer,tree.right);
	}

	public static void postorder(int[] answer,tree tree) {
		if(tree==null)
			return;
		postorder(answer,tree.left);
		postorder(answer,tree.right);
		answer[idx++]=tree.data.number;
	}
}

class node implements Comparable<node>{
	int x;
	int y;
	int number;
	node(int x,int y,int number){
		this.x=x;
		this.y=y;
		this.number=number;
	}

	@Override
	public int compareTo(node arg0) {
		// TODO Auto-generated method stub
		if(this.y!=arg0.y)
			return arg0.y-this.y;
		else
			return this.x-arg0.x;
	}
}

class tree{
	node data;
	tree left;
	tree right;
	tree(node data){
		this.data=data;
		this.left=null;
		this.right=null;
	}

	public void addleft(tree left){
		this.left=left;
	}

	public void addright(tree right) {
		this.right=right;
	}
}
