import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

public class WeakCrypto {
    private static final String ALGORITHM = "DES"; // Weak encryption algorithm
    private static final byte[] KEY = "MyKey123".getBytes(); // Hard-coded, short and guessable key

    public static String encrypt(String valueToEnc) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedValue = cipher.doFinal(valueToEnc.getBytes());
        return Base64.encodeToString(encryptedValue, Base64.DEFAULT);
    }

    public static String decrypt(String encryptedValue) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedValue = Base64.decode(encryptedValue, Base64.DEFAULT);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue);
    }
}