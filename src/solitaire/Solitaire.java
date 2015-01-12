package solitaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;


/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		
			CardNode temp = deckRear;
			
			do{
				if(temp.cardValue==27){
					temp.cardValue = temp.next.cardValue;
					temp.next.cardValue = 27;
					return;
				}
				temp=temp.next;
			}while(!temp.equals(deckRear));
			
		
		
			
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
		
			
			CardNode temp = deckRear;
			
			do{
				if(temp.cardValue==28){
					temp.cardValue = temp.next.cardValue;
					temp.next.cardValue = 28;
					break;
				}
				temp=temp.next;
			}while(!temp.equals(deckRear));
			
			
		    temp = deckRear;
			
			do{
				if(temp.cardValue==28){
					temp.cardValue = temp.next.cardValue;
					temp.next.cardValue = 28;
					return;
				}
				temp=temp.next;
			}while(!temp.equals(deckRear));
			
		
		
		
		
	    // COMPLETE THIS METHOD
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		
		int secondJoker = 0;
		CardNode newRear = new CardNode();
		CardNode originalFront = deckRear.next;
		int originalRear = deckRear.cardValue;
		CardNode temp = deckRear;
		
		if((deckRear.cardValue==27 || deckRear.cardValue==28) && (deckRear.next.cardValue==27 || deckRear.next.cardValue==28))
			return;
		
		if(deckRear.cardValue==27 || deckRear.cardValue==28){
			
			temp=deckRear.next;
			
			do{
				
				if(temp.next.cardValue==27 || temp.next.cardValue==28){
					deckRear=temp;
					return;
				}
				
				temp=temp.next;
				
			}while(deckRear.cardValue!=50);
			
			
			
		}
		
		if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28){
			temp=temp.next.next;
			do{
				if(temp.cardValue==27 || temp.cardValue==28){
					deckRear = temp;
					return;
				}
				temp=temp.next;
			}while(deckRear.cardValue!=50);
		}
		
		do{
			if(temp.next.cardValue==27 || temp.next.cardValue==28){
				newRear.cardValue = temp.cardValue;
				temp = temp.next;
				break;
			}	
			temp = temp.next;	
		}while(deckRear.cardValue!=50);
		
		CardNode temp2=temp.next.next;
		
		do{
			if(temp2.cardValue ==27 || temp2.cardValue == 28){
				newRear.next = temp2.next;
				secondJoker = temp2.cardValue;
				break;
			}
			
			temp2=temp2.next;
				
		}while(deckRear.cardValue != 50);
		
		//System.out.println(newRear.cardValue + "    " + newRear.next.cardValue);
		
		temp2 = newRear.next;
		
		do{
			if(temp2.cardValue == originalRear){
				temp2.next = temp;
				temp2=temp2.next;
				break;
			}
			
			temp2=temp2.next;
		}while(deckRear.cardValue!=50);
		
		
		
		do{
			if(temp2.cardValue == secondJoker){
				temp2.next = originalFront;
				break;
			}
			
			temp2=temp2.next;
		}while(deckRear.cardValue!=50);
		
		do{
			if(temp2.next.cardValue == newRear.cardValue){
				temp2.next = newRear;
				break;
			}
			
			temp2=temp2.next;
		}while(deckRear.cardValue!=50);
		
	
		
		deckRear = newRear;
		
		
		
		
		
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		CardNode originalFront = deckRear.next;
		int count = deckRear.cardValue;
		
		if(deckRear.cardValue==28)
			count=27;
		
		CardNode temp = deckRear.next;
		
		for(int i=1; i<count; i++){
			temp = temp.next;
		}
		
		deckRear.next = temp.next;
		int a = deckRear.next.cardValue;
		
		temp = deckRear.next;
		
		do{
			
			if(temp.next.equals(deckRear)){
				temp.next = originalFront;
				break;
			}
			
			temp=temp.next;
			
		}while(deckRear.cardValue!=50);
			
		do{
			
			if(temp.next.cardValue==a){
				temp.next=deckRear;
				break;
			}
			
			temp=temp.next;
				
			
		}while(deckRear.cardValue!=50);
		
		
		
		
		// COMPLETE THIS METHOD
	}
	
        /**
         * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
         * counts down based on the value of the first card and extracts the next card value 
         * as key, but if that value is 27 or 28, repeats the whole process until a value
         * less than or equal to 26 is found, which is then returned.
         * 
         * @return Key between 1 and 26
         */
	int getKey() {
		
		do{
		
		this.jokerA();
		this.jokerB();
		this.tripleCut();
		this.countCut();
		
		
		
		int count = deckRear.next.cardValue;
		if(count==28)
			count=27;
		
		CardNode temp = deckRear.next;
		
		for(int i=1; i<count; i++){
			temp=temp.next;
		}
		
		if(temp.next.cardValue != 27 && temp.next.cardValue != 28){
			return temp.next.cardValue;
		
		}	
		
		}while(true);
			
		
		
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    //return -1;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		String newMessage = "";
		String enc = "";
		int length = message.length();
		
		for(int i=0; i<message.length(); i++){
			if(Character.isUpperCase(message.charAt(i))){
				newMessage = newMessage + message.charAt(i);
			}
		}
		
		for(int i=0; i<newMessage.length(); i++){
			int a = this.getKey();
			char b = newMessage.charAt(i);
			int alp = b -64;
			int c = a + alp;
			if(c>26)
				c=c-26;
			enc = enc + (char)(c+64);
			
			
		}
		
		
		
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    return enc;
	    
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		
		String dec = "";
		
		for (int i=0; i<message.length(); i++){
			
			int a = this.getKey();
			char b = (char) (message.charAt(i)-64);
			if(b<=a){
				b = (char) (b + 26);
			}
			
			int c = b - a;
			char d = (char) ((char)c+64);
			
			dec = dec + d;
			
		}
		
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    
		return dec;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		Solitaire ss = new Solitaire();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter deck file name => ");									
		
		Scanner sc = new Scanner(new File(br.readLine()));
		ss.makeDeck(sc);
		printList(ss.deckRear);
		ss.jokerA();
		printList(ss.deckRear);
		
	}
	
	
	
	
	
	 
}
