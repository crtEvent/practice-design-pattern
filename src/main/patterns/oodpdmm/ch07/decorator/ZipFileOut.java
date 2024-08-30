package oodpdmm.ch07.decorator;

public class ZipFileOut extends Decorator {

    public ZipFileOut(FileOut delegate) {
        super(delegate);
    }

    @Override
    public void write(byte[] data) {
        byte[] zipData = zip(data);
        super.doDelegate(zipData);
    }

    private byte[] zip(byte[] data) {
        return ("Zipped(" + new String(data) + ")").getBytes();
    }
}
