public class Linked_list {
	public static class Node{
		public int val;
		public Node next;
	}
	
	public static class DoubleNode{
		public int val;
		public DoubleNode pre;
		public DoubleNode next;
	}
	
	/*
	 * 单向链表反转
	 */
	public static Node reverseNode(Node head){
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	/*
	 * 双向链表反转
	 */
	public static DoubleNode reverseDoubleNode(DoubleNode head){
		DoubleNode pre = null;
		DoubleNode next = null;
		while(head != null){
			next = head.next;
			head.pre = next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	/*
	 * 单链表删除指定节点
	 * 
	 */
	public static void deleteOne(Node head, Node toBeDeleted){
		if(head == null || toBeDeleted == null) return;
		if (head.next == null) { //链表只有一个节点时
			head = null;
			toBeDeleted = null;
		} else if (toBeDeleted.next != null) { //将下一个节点的值覆盖当前节点
			toBeDeleted.val = toBeDeleted.next.val;
			toBeDeleted.next = toBeDeleted.next.next;
		} else {  //目标节点处于链表尾部时，遍历链表找到前一个节点
			Node current = head;
			while (current.next != toBeDeleted) {
				current = current.next;
			}
			current.next = null;
		}
	}
	
	/*
	 * 得到链表中倒数第K个节点
	 */
	public static Node findKthToTail(Node head, int k){
		Node p = head;
		Node q = head;
		
		int i = 0;
		for(; p != null; i++){
			if (i >= k) {
				q = q.next;
			}
			p = p.next;
		}
		
		return i < k ? null : q;
	}
	
	/*
	 * 合并两个有序链表，结果依旧有序
	 */
	public static Node merge(Node list1, Node list2){
		if(list1 == null) return list1;
		if(list2 == null) return list2;
		
		Node head = new Node(); //新建节点用来存合并的链表
		Node root = head;
		
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				head.next = list1;
				head = list1;
				list1 = list1.next;
			} else {
				head.next = list2;
				head = list2;
				list2 = list2.next;
			}
		}
		
		// 未结束的链表连接到合并后的链表尾部
		if (list1 != null) head.next = list1;
		if (list2 != null) head.next = list2;
		return root.next;
	}
}
