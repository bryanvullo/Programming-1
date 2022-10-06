// This is the Wjaelcome Class which prints out a welcome message
public class Welcome {

    static String strMessage = "Welcome to COMP1202!\n";

    public static void main(String[] args){
        char[] chars = strMessage.toCharArray();
        // iterate over characters in string
        for (int i = 0; i < chars.length; i++){
            boolean pause = true;
            // dont pause if it is the last character
            if(i==chars.length-1){
                pause = false;
            }
            // dont pause if the character is a space
            if(Character.isWhitespace(chars[i])){
                pause = false;
            }
            System.out.print(chars[i]);
            if(pause){
                // wait for 100 milliseconds
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    System.out.println("Error: "+e);
                }
            }
        }
    }
}