import java.util.NoSuchElementException;

public class EvaluarExpresion {
	
	static MyStack<String> stack = new MyStack<>();
	static MyQueue<String> queue = new MyQueue<>();
	static MyQueue<String> tempQueue = new MyQueue<>();
	static String expressionResulted = "";
	
	public static void principal(String expression) {
		divideExpression(expression);
		flushStack();
		double result = calculate(queue);
		
		while(!tempQueue.isEmpty()) {
			expressionResulted += tempQueue.dequeue() + " ";
		}
		
		System.out.println("Expresion postfija: " + expressionResulted);
		System.out.println("Resultado: " + result);
	}
	
	private static void divideExpression(String expression) {
		String[] expressionDivided = expression.split(" ");
		convertExpresion(expressionDivided);
	}
	
	private static void convertExpresion(String[] expression) {
		for(int i = 0; i < expression.length; i++) {
			try {
				double temp = Double.parseDouble(expression[i]);
				queue.enqueue(expression[i]);
			} catch (NumberFormatException e) {
				if(stack.size() == 0) {
					stack.push(expression[i]);
				}else if(expression[i].compareTo("(") == 0) {
					stack.push(expression[i]);
				}else if(expression[i].compareTo(")") == 0){
					operatorsInsideP();
				}else {
					evaluateTerm(expression[i]);
				}
			}
		}
	}
	
	private static void evaluateTerm(String operator) {
		if(stack.top().compareTo("(") == 0) {
			stack.push(operator);
		}else {
			while(!stack.isEmpty()) {
				if(priority(operator) < priority(stack.top())) {
					queue.enqueue(stack.pop());
					if(stack.size() == 0) {
						stack.push(operator);
						break;
					}else {
						continue;
					}
				}else if(priority(operator) == priority(stack.top())){
					queue.enqueue(stack.pop());
					stack.push(operator);
					break;
				}else {
					stack.push(operator);
					break;
				} 
			}
		}
	}

	private static void flushStack() {
		while(!stack.isEmpty()) {
			queue.enqueue(stack.pop());
		}
	}
	
	private static void operatorsInsideP() {
		while(stack.top().compareTo("(") != 0) {
			queue.enqueue(stack.pop());
		}
		stack.pop();
	}
	
	private static int priority(String operator) {
		switch (operator) {
		case "+":
			return 1;
		case "-":
			return 1;
		case "*":
			return 2;
		case "/":
			return 2;
		case ")":
			return 4;
		default:
			return 3;
		}
	}

	private static double calculate(MyQueue<String> queue) throws NumberFormatException{
		while(!queue.isEmpty()) {
			String temp = queue.dequeue();
			try {
				double term = Double.parseDouble(temp);
				stack.push((String) temp);
				tempQueue.enqueue(temp);
			} catch (NumberFormatException e) {
				try {
					tempQueue.enqueue(temp);
					double partialResult = operations(Double.parseDouble(stack.pop()),Double.parseDouble(stack.pop()),(String) temp);
					stack.push(partialResult + "");
				} catch (NoSuchElementException ex) {
					throw new NumberFormatException("There is a syntax error in the expression given");
				}
			}
		}
		return Double.parseDouble(stack.pop());
	}

	private static double operations(double first, double second, String operator) throws NumberFormatException{
		if(operator.compareTo("+") == 0) {
			return second + first;
		}else if(operator.compareTo("-") == 0) {
			return second - first;
		}else if(operator.compareTo("*") == 0) {
			return second * first;
		}else if(operator.compareTo("/") == 0) {
			return second / first;
		}else if(operator.compareTo("^") == 0) {
			return (double) Math.pow(second, first);
		}else {
			throw new NumberFormatException("There is an invalid operator in the expression.");
		}
	}
	
	public static void main(String[] args) {
		principal(args[0]);
	}
}
