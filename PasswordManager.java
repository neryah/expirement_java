package PasswordManager;

import java.util.*;
import java.io.*;
import java.security.*;


public class PasswordManager {
	private Map<String,String> passwords;
	
	public PasswordManager(){
		passwords = new HashMap<String,String>();
	}
	
	public int readFile (String filename) {
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();;
			String cleanline;
			String name;
			String pass;
			while (line != null) {
				cleanline = line.split("#")[0];
				name = cleanline.split("\\s+")[0];
				try {
					pass = cleanline.split("\\s+")[1];
				} catch (ArrayIndexOutOfBoundsException e) { // if line has no spaces
					System.out.println("illegal line");
					line = reader.readLine();
					continue;
				}
				
				try {
					this.add(name, pass, true);
				} catch (UserExistsException e) { // if already exist
					e.printStackTrace();
				}
				
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public void writeFile(String filename){
		try{
			File fout = new File(filename);
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for(Map.Entry<String,String> pair : passwords.entrySet()){
				bw.write(pair.getKey() + " " + pair.getValue());
				bw.newLine();
			}
			bw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public void add(String name, String password) throws UserExistsException{
		if (this.checkPassword(name, password)){
			throw new UserExistsException(name, password); 
		}
		this.passwords.put(name, calcSHA1(password));
	}
	
	private void add(String name, String password, boolean noHash) throws UserExistsException{
		if (this.checkPassword(name, password)){
			throw new UserExistsException(name, password); 
		}
		if (noHash) {
			this.passwords.put(name, password);
		} else {
			this.passwords.put(name, calcSHA1(password));
		}
	}
	
	public boolean checkPassword(String name, String password) {
		return this.passwords.containsKey(name) && (this.passwords.get(name).compareTo(calcSHA1(password)) == 0);
	}
	
	public static String calcSHA1(String str){
		MessageDigest md;
		String ret = "";
		
		try{
			md = MessageDigest.getInstance("SHA1");
			
			md.update( str.getBytes());
			byte[] digest = md.digest();
			
			for(byte b : digest){
				String hNum = Integer.toHexString(b & 0xFF);
				
				if(hNum.length() == 1)
					hNum = "0" + hNum;
				ret = ret + hNum; 
			}
		} catch (NoSuchAlgorithmException e){
			return null;
		}
		return ret;
	}
	
	public static void main(String[] args){
		PasswordManager pm1 = new PasswordManager();
		PasswordManager pm2 = new PasswordManager();
		
		try {
			pm1.add("user1", "secret1");
			pm1.add("user2", "secret2");
			pm1.add("user3", "secret3");
			pm1.add("user4", "secret4");
			pm1.add("user2", "secret2");
			
			System.out.println("Test1 - directly added:");
			System.out.println("Should be " + true + ": " + pm1.checkPassword("user1", "secret1"));
			System.out.println("Should be " + true + ": " + pm1.checkPassword("user3", "secret3"));
			System.out.println("Should be " + false + ": " + pm1.checkPassword("user2", "secet2"));
			System.out.println("Should be " + false + ": " + pm1.checkPassword("uer1", "secret1"));
			
			pm1.writeFile("passwords.txt");
		} catch (UserExistsException e) {
			System.out.println("Error: a user already exists. ");
		}
		
		System.out.println("");
		System.out.println("loading from file...");
		pm2.readFile("passwords.txt");
			
		System.out.println("Test2 - loaded:");
		System.out.println("Should be " + true + ": " + pm2.checkPassword("user1", "secret1"));
		System.out.println("Should be " + true + ": " + pm2.checkPassword("user3", "secret3"));
		System.out.println("Should be " + false + ": " + pm2.checkPassword("user2", "secet2"));
		System.out.println("Should be " + false + ": " + pm2.checkPassword("uer1", "secret1"));
		
	}
}

class UserExistsException extends Exception {
	
	public UserExistsException(String name, String pass) {
		super(name + " already exist with password " + pass);
	}

}
