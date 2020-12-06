package HomeWork_12thWeek;

public class StringCheck {
    public static void main(String[] args){
        String str1 = "faaabbaaaaaabbaaaeabdddddaacaaeeabbbaaaaeddaaaeaacaaddaadaaaaeffaaabbbccccffdaaeaddbbeeacccdacccddaa";
        String str2 = "faaabbaaaaaabbaaaeabdddddaacaaeeabbbaaaaeddaaaeaacaaddaadaaaaeffaaabbbccccffdaaeaddbbeeacccdacccddaa";

        boolean isCheck = true;
        for(int i = 0; i < str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                isCheck = false;
                System.out.println(i);
                break;
            }
        }
        System.out.println(isCheck);
    }
}
