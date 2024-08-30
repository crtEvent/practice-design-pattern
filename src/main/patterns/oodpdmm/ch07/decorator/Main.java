package oodpdmm.ch07.decorator;

public class Main {

    public static void main(String[] args) {
        byte[] data = "Example Data".getBytes();
        FileOut delegate = new FileOutImpl();
        FileOut fileOut = new EncryptionFileOut(new ZipFileOut(delegate));
        fileOut.write(data);
    }
}
