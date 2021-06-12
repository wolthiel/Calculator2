import java.util.Scanner;

public class Postfix {
	
	
	public int evaluate(String pfx) throws Exception {
		
		String ffff =  infixToPostfix(pfx);
		
		StackAsList stackforDigits = new StackAsList();
		int result = 0;
		int firstdigit = 0;
		int seconddigit = 0;

		char[] c = ffff.toCharArray();
		
		for (char chr: c) {
			if(Character.isDigit(chr)){
				stackforDigits.addFirstNode(chr);	
			} else if (!(Character.isDigit(chr))){
				
				firstdigit = Integer.parseInt(stackforDigits.removeFirst().toString());
				seconddigit = Integer.parseInt(stackforDigits.removeFirst().toString());
				
				switch(chr) {
				case '+':
					result = seconddigit+firstdigit;
					break;
				case '/':
					result = seconddigit/firstdigit;
					break;
				case '-':	
					result = seconddigit-firstdigit;
					break;
				case '*' :
					result = seconddigit*firstdigit;
					break;	
				}
				stackforDigits.addFirstNode(result);
				}
		}
		return result;
	}    
	    
//	public static String infixToPostfix(String exp)
//    {
//    	char operator = 0;
//    	StackAsList stackforDigits = new StackAsList();
//    	char[] c = exp.toCharArray();
//    	String string;
//    	StackAsList.StackListIterator iterator = stackforDigits.new StackListIterator();
//		
//		for (char chr: c) {
//			if(Character.isDigit(chr)){
//				iterator.add(chr);
//			} else if (!(Character.isDigit(chr))){
//									
//				switch(chr) {
//				case '+':
//					operator = chr;
//				case '/':
//					operator = chr;
//				case '-':	
//					operator = chr;
//				case '*' :
//					operator = chr;
//				}
//		}
//    }
//		iterator.add(operator);
//		string = stackforDigits.allNodesToString();
//		return string;
//
//}
	
	public String infixToPostfix(String ifx) throws Exception {
		StackAsList stackforDigits = new StackAsList();
		char[] characters = ifx.toCharArray();
		String result = "";
		Object op = '(';
		
		for(char t:characters) {
			if(Character.isDigit(t)) {
				result = result + t;
			}else if(t == '(') {
				stackforDigits.addFirstNode(t);
			}else if(t == ')') {
				while(stackforDigits.peek() == op){
					result = result + stackforDigits.peek();	
					stackforDigits.removeFirst();
			}
				if(stackforDigits.peek() == op) { 
				stackforDigits.removeFirst();
				}
			}
			else if(t == '+' || t == '-' || t == '*' || t == '/') {
				while(!(stackforDigits.peek().equals(!(highPrecedence(t)) || (lowPrecedence(t))))) {
					result = result + stackforDigits.peek();
					stackforDigits.removeFirst();
				}
				stackforDigits.addFirstNode(t);
			}
		}
		while(!stackforDigits.isEmpty()) {
			result = result + stackforDigits.peek();
			stackforDigits.removeFirst();		
		}
		return result;
	}
	
	private boolean highPrecedence(char c) {
		return (c == '*' || c == '/');
	}
	
	private boolean lowPrecedence(char c) {
		return (c == '+' || c == '-');
	}
		
		public void InputofUserInConsole() throws Exception {
			Scanner scan = new Scanner(System.in);
			System.out.print("Input: ");
			String console = scan.nextLine();
			System.out.println("Output: " + evaluate(console));
		}
}

