public class Masked {

   static String maskAccountNumber(String bankAccountNumber, char maskWith, char ignoredChar, int remainingChar) { 

      StringBuilder sb = new StringBuilder();

      int count = 0;

      for (int i = bankAccountNumber.length() - 1; i >= 0; i--) {  //read bankAccountNumber characters in reverse order

         if (Character.isDigit(bankAccountNumber.charAt(i))) { //if the character is a digit
            count++; //increase count
         }

         if (count <= remainingChar) { //for the correct number of characters not masked
            sb.append(bankAccountNumber.charAt(i)); // append original characters
         } else {
            sb.append(Character.isDigit(bankAccountNumber.charAt(i)) ? maskWith : ignoredChar); //if the character is a digit, use the masking character
         }
      }

      return (sb.reverse().toString()); //reverse the string and return it
      
   }   


   public static void main(String[] args) {

      maskAccountNumber("356-5-5059302", '*', '-', 5);
      maskAccountNumber("78-19084-84", '*', '-', 5);    
      maskAccountNumber("1875-2788-9-008-1 ", '*', '-', 6);

   }
}
