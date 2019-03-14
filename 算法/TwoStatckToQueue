import java.util.Stack;

public class TwoStatckToQueue {
	//通过两个栈实现队列
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();
	
	public void push(int node){
		stack1.push(node);
	}
	
	public int pop(){
		if (stack1.isEmpty() && stack2.isEmpty()) {
			throw new RuntimeException("queue is empty");
		}
		
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
}
