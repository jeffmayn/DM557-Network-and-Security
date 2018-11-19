package dk.sdu.imada.rsa;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RSA {

    private Random random;
    private BigInteger primeOne;
    private BigInteger primeTwo;

    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private BigInteger z;

    public RSA(Integer bits) {
        random = new Random();
        BigInteger primeOne = BigInteger.probablePrime(bits, random);
        BigInteger primeTwo = BigInteger.probablePrime(bits, random);
        setUpNumbers(primeOne,primeTwo, null);
    }

    public RSA(BigInteger primeOne, BigInteger primeTwo) {
        random = new Random();
        setUpNumbers(primeOne, primeTwo, null);
    }

    public RSA(BigInteger primeOne, BigInteger primeTwo, BigInteger e) {
        random = new Random();
        setUpNumbers(primeOne, primeTwo, e);
    }

    private void setUpNumbers(BigInteger primeOne, BigInteger primeTwo, BigInteger chosenE) {
        this.primeOne = primeOne;
        this.primeTwo = primeTwo;
        this.n = primeOne.multiply(primeTwo);

        z = (primeOne.subtract(BigInteger.ONE)).multiply(  (primeTwo.subtract(BigInteger.ONE)) );
        if( chosenE == null) {
            e = selectE(z);
        } else {
            if( !isValidE(chosenE, z) ) {
                throw new IllegalArgumentException("The chosen e is not relative prime to z");
            }
            e = chosenE;
        }
        d = selectD(e,z);
    }

    private boolean isValidE(BigInteger e, BigInteger z) {
        return NumberHelpers.extendedEuclideanAlgorithm(e, z)[0].equals(BigInteger.ONE);
    }

    private BigInteger selectE(BigInteger z) {
        BigInteger guess = BigInteger.probablePrime(primeOne.bitLength()/3, random);
        while( !isValidE(guess, z) ) {
            guess = guess.add(BigInteger.ONE);
        }
        return guess;
    }

    private BigInteger selectD(BigInteger e, BigInteger z) {
        BigInteger d = NumberHelpers.extendedEuclideanAlgorithm(e, z)[1];
        if( d.compareTo(BigInteger.ZERO) < 0) {
            return d.add(z);
        }
        return d;
    }

    private static List<String> createBlocks(String text, int size){
      List<String> list = new ArrayList<>();

      int length = text.length();
      for (int i = 0; i < length; i += size){
        list.add(text.substring(i, Math.min(length, i+size)));
      }
      String last = list.get(list.size()-1);

      if(last.length()%5!=0){
        while (last.length()%5!=0){
          last += ' ';
        }
      }
      list.remove(list.size()-1);
      list.add(last);
      return list;
    }

    /**
     * Encrypt using the public key. Each block of 5 characters are encrypted,
     * and placed in a separate line (i.e. followed by "\n"
     * @param plaintext The text to encrypt
     * @return The encrypted message
     */
    public String encryptMessage(String plaintext) {
      BigInteger str2Number = AlphabetConversion.stringToNumber(plaintext);
      List<BigInteger> list = new ArrayList<>();

      String encryptedMessage = "";
      int count = 0;

      List<String> liste = new ArrayList<>();
      liste = createBlocks(plaintext, 5);

      for (String s : liste){
        BigInteger m = AlphabetConversion.stringToNumber(s);
        BigInteger c = m.modPow(e, n);

        encryptedMessage += c.toString() + "\n";
        count++;
      }
      return encryptedMessage;
    }

    /**
     * Decrypt using the private key. The ciphertext has a block of encrypted text on each line.
     * @param cipherText Encrypted text to decrypt
     * @return Decrypted plaintext message
     */
    public String decrypt(String cipherText) {
      String[] parts = cipherText.split("\n");
      int len = parts.length;

      String plaintext = "";
      String temp = "";
      for (int i = 0; i < len; i++){
        String myPart = parts[i];
        BigInteger c = new BigInteger(myPart);
        BigInteger m = c.modPow(d, n);
        temp = AlphabetConversion.numberToString(m);
        plaintext += temp;
        System.out.println("plaintext --> '" + plaintext + "'");
      }
      return plaintext;
    }
}
