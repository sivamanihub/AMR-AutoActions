package smk.resorce;
class DualOutputStream_v3 extends java.io.OutputStream {
    private final java.io.OutputStream out1;
    private final java.io.OutputStream out2;

    public DualOutputStream_v3(java.io.OutputStream out1, java.io.OutputStream out2) {
        this.out1 = out1;
        this.out2 = out2;
    }

    @Override
    public void write(int b) throws java.io.IOException {
        out1.write(b);
        out2.write(b);
    }

    @Override
    public void flush() throws java.io.IOException {
        out1.flush();
        out2.flush();
    }

    @Override
    public void close() throws java.io.IOException {
        out1.close();
        out2.close();
    }
}
