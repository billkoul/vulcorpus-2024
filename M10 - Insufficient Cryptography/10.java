import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class WeakCryptoUtils {
    private static final String ALGORITHM = "DES"; 
    private static final byte[] KEY = "12345678".getBytes();

    public static String encrypt(String valueToEncrypt) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedValue = cipher.doFinal(valueToEncrypt.getBytes());
            return Base64.encodeToString(encryptedValue, Base64.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    public static String decrypt(String encryptedValue) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decodedValue = Base64.decode(encryptedValue, Base64.DEFAULT);
            byte[] decryptedValue = cipher.doFinal(decodedValue);
            return new String(decryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Decryption error", e);
        }
    }

    public static void main(String[] args) {
        String originalValue = "sensitiveInformation";
        String encryptedValue = encrypt(originalValue);
        String decryptedValue = decrypt(encryptedValue);

        System.out.println("Original: " + originalValue);
        System.out.println("Encrypted: " + encryptedValue);
        System.out.println("Decrypted: " + decryptedValue);
    }
}
