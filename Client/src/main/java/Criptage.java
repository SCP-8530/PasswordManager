import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class Criptage {
    public static Map<String,String> Decryptage(Map<String, String> map) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        //cle
        SecretKey secretKey = CS_EncryptionUtils.getKeyFromPassword("Password","Salee");;
        IvParameterSpec ivParameterSpec = CS_EncryptionUtils.generateIv(new byte[] { (byte)0xe0, 0x4f, (byte)0xd0,
                0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
                0x30, 0x30, (byte)0x9d });

        //boucle de decryptage
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String mdp_crypter = entry.getValue();
            String mdp_decrypter = CS_EncryptionUtils.decrypt(mdp_crypter,secretKey,ivParameterSpec);
            entry.setValue(mdp_decrypter);
        }

        //return
        return map;
    }

    public static Map<String,String> Encryptage(Map<String, String> map) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        //cle
        SecretKey secretKey = CS_EncryptionUtils.getKeyFromPassword("Password","Salee");;
        IvParameterSpec ivParameterSpec = CS_EncryptionUtils.generateIv(new byte[] { (byte)0xe0, 0x4f, (byte)0xd0,
                0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
                0x30, 0x30, (byte)0x9d });

        //boucle de decryptage
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String mdp_non_crypter = entry.getValue();
            String mdp_crypter = CS_EncryptionUtils.encrypt(mdp_non_crypter,secretKey,ivParameterSpec);
            entry.setValue(mdp_crypter);
        }

        //return
        return map;
    }
}
