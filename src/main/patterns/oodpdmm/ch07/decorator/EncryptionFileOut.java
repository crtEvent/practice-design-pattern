package oodpdmm.ch07.decorator;

public class EncryptionFileOut extends Decorator {

    public EncryptionFileOut(FileOut delegate) {
        super(delegate);
    }

    @Override
    public void write(byte[] data) {
        byte[] encryptedData = encrypt(data);
        super.doDelegate(encryptedData);
    }

    private byte[] encrypt(byte[] data) {
        return ("Encrypted(" + new String(data) + ")").getBytes();
    }
}
