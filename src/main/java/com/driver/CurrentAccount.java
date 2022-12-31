package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
    super(name,balance);
    this.tradeLicenseId=tradeLicenseId;
    if(getBalance()<5000) throw new Exception("Insufficient Balance");
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
    boolean check=false;
    for(int i=0;i<this.tradeLicenseId.length()-1;i++){
    if(this.tradeLicenseId.charAt(i)==this.tradeLicenseId.charAt(i+1)){
    check=true;
    break;
            }
        }
    if(check){
     String s= Rearrange(this.tradeLicenseId);
    if(s.equals(""))throw new Exception("Valid License can not be generated");
    else{
     this.tradeLicenseId=s;
    }
        }
    }
    public String Rearrange(String s){
        int n = s.length(), maxFreq = 0, maxLetter = -1;
        int[] map = new int[26];
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            map[c] += 1;
            if (map[c] > maxFreq) {
                maxFreq = map[c];
                maxLetter = c;
            }
        }

        if (maxFreq - 1 > n - maxFreq) return "";
        char[] res = new char[n];
        int index = 0;
        while (map[maxLetter] != 0) {
            res[index] = (char) ('a' + maxLetter);
            map[maxLetter] -= 1;
            index += 2;
        }

        for (int i = 0; i < 26; i++) {
            while (map[i] != 0) {
                if (index >= n) index = 1;
                res[index] = (char) ('a' + i);
                map[i] -= 1;
                index += 2;
            }
        }
        return new String(res);
    }
}
