package oodpdmm.ch07.decorator;

public class FileOutImpl implements FileOut {

    @Override
    public void write(byte[] data) {
        System.out.println("Writing data to file: " + new String(data));
    }
}
