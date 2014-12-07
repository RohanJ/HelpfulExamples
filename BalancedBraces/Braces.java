import java.util.*;

public class Braces {
	public static void main(String[] args){
		SOP("Hello World");
		testBraces();
	}

	public static void testBraces() {
		String[] expressions = { ")(){}", "[]({})", "([])", "{()[]}", "([)]" };
		balancedBraces(expressions);
	}

	private static void balancedBraces(String[] expressions){
		String[] openingBraces = { "(", "[", "{" };
		String[] closingBraces = { ")", "]", "}" };

		for(int i = 0; i<expressions.length; i++){
			if(areBracesBalanced(expressions[i], openingBraces, closingBraces)){
				SOP(expressions[i] + " IS BALANCED --> 1");
			}
			else{
				SOP(expressions[i] + " IS NOT BALANCED --> 0");
			}
		}
	}

	private static Boolean areBracesBalanced(String expression, String[] openingBraces, String[] closingBraces){
		Stack<String> mStack = new Stack<String>();
		String mChar = "";
		for(int i = 0; i<expression.length(); i++){
			mChar = expression.charAt(i) + "";
			if(isTypeOf(mChar, openingBraces)){
				mStack.push(mChar);
			}
			else{
				//indicating its type of closingBraces
				if(!mStack.empty()){
					if(!isBraceOppositeOf(mStack.pop(), mChar)) return false;
				}
				else return false;
			}
		}

		if(mStack.empty()) return true;
		else return false;
	}

	private static Boolean isTypeOf(String brace, String[] braces){
		for(int i = 0; i<braces.length; i++){
			if(braces[i].equals(brace)) return true;
		}
		return false;
	}

	private static Boolean isBraceOppositeOf(String brace1, String brace2){
		if(brace1.equals("(") && brace2.equals(")")) return true;
		if(brace1.equals("[") && brace2.equals("]")) return true;
		if(brace1.equals("{") && brace2.equals("}")) return true;
		return false;
	}

	private static void SOP(Object arg) {
		System.out.println(arg);
	}
}