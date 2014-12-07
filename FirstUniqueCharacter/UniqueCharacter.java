import java.util.*;

public class UniqueCharacter {
	static final String LANG = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rand = new Random();
	public static void main(String[] args){
		testUniqueCharacter();
	}

	private static void testUniqueCharacter(){
		firstUniqueCharacter("ab2cbZ2daZ");

		firstUniqueCharacter(randomString(200000));
	}

	private static void firstUniqueCharacter(String text){
		//Linked Hash map preserves order
		LinkedHashMap<String, Integer> mLHM = new LinkedHashMap<String, Integer>();

		String mChar = "";
		for(int i = 0; i<text.length(); i++){
			mChar = text.charAt(i) + "";
			Integer value = mLHM.get(mChar);
			if(value == null){
				mLHM.put(mChar, 1);
			}
			else{
				mLHM.put(mChar, value + 1);
			}
		}


		for(Map.Entry<String, Integer> mEntry : mLHM.entrySet()){
			if(mEntry.getValue() == 1){
				SOP(mEntry.getKey());
				return;
			}
		}
		SOP("-1");
	}

	private static String randomString(int len) {
		StringBuilder mSB = new StringBuilder(len);
		for (int i = 0 ; i < len; i++) {
			mSB.append(LANG.charAt(rand.nextInt(LANG.length())));
		}
		return mSB.toString();
	}

	private static void SOP(Object arg) {
		System.out.println(arg);
	}
}
