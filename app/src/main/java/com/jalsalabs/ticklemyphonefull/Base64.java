package com.jalsalabs.ticklemyphonefull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int DECODE = 0;
    public static final int DONT_GUNZIP = 4;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = (byte) 61;
    private static final byte EQUALS_SIGN_ENC = (byte) -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = (byte) 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "US-ASCII";
    public static final int URL_SAFE = 16;
    private static final byte WHITE_SPACE_ENC = (byte) -5;
    private static final byte[] _ORDERED_ALPHABET = new byte[]{(byte) 45, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 95, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122};
    private static final byte[] _ORDERED_DECODABET;
    private static final byte[] _STANDARD_ALPHABET = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] _STANDARD_DECODABET;
    private static final byte[] _URL_SAFE_ALPHABET = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    private static final byte[] _URL_SAFE_DECODABET;

    public static class InputStream extends FilterInputStream {
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        public InputStream(java.io.InputStream in) {
            this(in, 0);
        }

        public InputStream(java.io.InputStream in, int options) {
            boolean z = true;
            super(in);
            this.options = options;
            this.breakLines = (options & 8) > 0 ? true : Base64.$assertionsDisabled;
            if ((options & 1) <= 0) {
                z = Base64.$assertionsDisabled;
            }
            this.encode = z;
            this.bufferLength = this.encode ? 4 : 3;
            this.buffer = new byte[this.bufferLength];
            this.position = -1;
            this.lineLength = 0;
            this.decodabet = Base64.getDecodabet(options);
        }

        public int read() throws IOException {
            int b;
            if (this.position < 0) {
                int i;
                if (this.encode) {
                    byte[] b3 = new byte[3];
                    int numBinaryBytes = 0;
                    for (i = 0; i < 3; i++) {
                        b = this.in.read();
                        if (b < 0) {
                            break;
                        }
                        b3[i] = (byte) b;
                        numBinaryBytes++;
                    }
                    if (numBinaryBytes <= 0) {
                        return -1;
                    }
                    Base64.encode3to4(b3, 0, numBinaryBytes, this.buffer, 0, this.options);
                    this.position = 0;
                    this.numSigBytes = 4;
                } else {
                    byte[] b4 = new byte[4];
                    i = 0;
                    while (i < 4) {
                        do {
                            b = this.in.read();
                            if (b < 0) {
                                break;
                            }
                        } while (this.decodabet[b & 127] <= Base64.WHITE_SPACE_ENC);
                        if (b < 0) {
                            break;
                        }
                        b4[i] = (byte) b;
                        i++;
                    }
                    if (i == 4) {
                        this.numSigBytes = Base64.decode4to3(b4, 0, this.buffer, 0, this.options);
                        this.position = 0;
                    } else if (i == 0) {
                        return -1;
                    } else {
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            if (this.position < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            } else if (this.position >= this.numSigBytes) {
                return -1;
            } else {
                if (this.encode && this.breakLines && this.lineLength >= Base64.MAX_LINE_LENGTH) {
                    this.lineLength = 0;
                    return 10;
                }
                this.lineLength++;
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                b = bArr[i2];
                if (this.position >= this.bufferLength) {
                    this.position = -1;
                }
                return b & 255;
            }
        }

        public int read(byte[] dest, int off, int len) throws IOException {
            int i = 0;
            while (i < len) {
                int b = read();
                if (b >= 0) {
                    dest[off + i] = (byte) b;
                    i++;
                } else if (i == 0) {
                    return -1;
                } else {
                    return i;
                }
            }
            return i;
        }
    }

    public static class OutputStream extends FilterOutputStream {
        private byte[] b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public OutputStream(java.io.OutputStream out) {
            this(out, 1);
        }

        public OutputStream(java.io.OutputStream out, int options) {
            int i;
            boolean z = true;
            super(out);
            this.breakLines = (options & 8) != 0 ? true : Base64.$assertionsDisabled;
            if ((options & 1) == 0) {
                z = Base64.$assertionsDisabled;
            }
            this.encode = z;
            if (this.encode) {
                i = 3;
            } else {
                i = 4;
            }
            this.bufferLength = i;
            this.buffer = new byte[this.bufferLength];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = Base64.$assertionsDisabled;
            this.b4 = new byte[4];
            this.options = options;
            this.decodabet = Base64.getDecodabet(options);
        }

        public void write(int theByte) throws IOException {
            byte[] bArr;
            int i;
            if (this.suspendEncoding) {
                this.out.write(theByte);
            } else if (this.encode) {
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) theByte;
                if (this.position >= this.bufferLength) {
                    this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                    this.lineLength += 4;
                    if (this.breakLines && this.lineLength >= Base64.MAX_LINE_LENGTH) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            } else if (this.decodabet[theByte & 127] > Base64.WHITE_SPACE_ENC) {
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) theByte;
                if (this.position >= this.bufferLength) {
                    this.out.write(this.b4, 0, Base64.decode4to3(this.buffer, 0, this.b4, 0, this.options));
                    this.position = 0;
                }
            } else if (this.decodabet[theByte & 127] != Base64.WHITE_SPACE_ENC) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }

        public void write(byte[] theBytes, int off, int len) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(theBytes, off, len);
                return;
            }
            for (int i = 0; i < len; i++) {
                write(theBytes[off + i]);
            }
        }

        public void flushBase64() throws IOException {
            if (this.position <= 0) {
                return;
            }
            if (this.encode) {
                this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
                this.position = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void close() throws IOException {
            flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }

        public void suspendEncoding() throws IOException {
            flushBase64();
            this.suspendEncoding = true;
        }

        public void resumeEncoding() {
            this.suspendEncoding = Base64.$assertionsDisabled;
        }
    }

    static {
        boolean z;
        if (Base64.class.desiredAssertionStatus()) {
            z = $assertionsDisabled;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
        byte[] bArr = new byte[256];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = WHITE_SPACE_ENC;
        bArr[10] = WHITE_SPACE_ENC;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = WHITE_SPACE_ENC;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = WHITE_SPACE_ENC;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) 62;
        bArr[44] = (byte) -9;
        bArr[45] = (byte) -9;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) 63;
        bArr[48] = (byte) 52;
        bArr[49] = (byte) 53;
        bArr[50] = (byte) 54;
        bArr[51] = (byte) 55;
        bArr[52] = (byte) 56;
        bArr[53] = (byte) 57;
        bArr[54] = (byte) 58;
        bArr[55] = (byte) 59;
        bArr[56] = (byte) 60;
        bArr[57] = EQUALS_SIGN;
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = EQUALS_SIGN_ENC;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
        bArr[66] = (byte) 1;
        bArr[67] = (byte) 2;
        bArr[68] = (byte) 3;
        bArr[69] = (byte) 4;
        bArr[70] = (byte) 5;
        bArr[71] = (byte) 6;
        bArr[72] = (byte) 7;
        bArr[73] = (byte) 8;
        bArr[74] = (byte) 9;
        bArr[75] = NEW_LINE;
        bArr[MAX_LINE_LENGTH] = (byte) 11;
        bArr[77] = (byte) 12;
        bArr[78] = (byte) 13;
        bArr[79] = (byte) 14;
        bArr[80] = (byte) 15;
        bArr[81] = (byte) 16;
        bArr[82] = (byte) 17;
        bArr[83] = (byte) 18;
        bArr[84] = (byte) 19;
        bArr[85] = (byte) 20;
        bArr[86] = (byte) 21;
        bArr[87] = (byte) 22;
        bArr[88] = (byte) 23;
        bArr[89] = (byte) 24;
        bArr[90] = (byte) 25;
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) -9;
        bArr[96] = (byte) -9;
        bArr[97] = (byte) 26;
        bArr[98] = (byte) 27;
        bArr[99] = (byte) 28;
        bArr[100] = (byte) 29;
        bArr[101] = (byte) 30;
        bArr[102] = (byte) 31;
        bArr[103] = (byte) 32;
        bArr[104] = (byte) 33;
        bArr[105] = (byte) 34;
        bArr[106] = (byte) 35;
        bArr[107] = (byte) 36;
        bArr[108] = (byte) 37;
        bArr[109] = (byte) 38;
        bArr[110] = (byte) 39;
        bArr[111] = (byte) 40;
        bArr[112] = (byte) 41;
        bArr[113] = (byte) 42;
        bArr[114] = (byte) 43;
        bArr[115] = (byte) 44;
        bArr[116] = (byte) 45;
        bArr[117] = (byte) 46;
        bArr[118] = (byte) 47;
        bArr[119] = (byte) 48;
        bArr[120] = (byte) 49;
        bArr[121] = (byte) 50;
        bArr[122] = (byte) 51;
        bArr[123] = (byte) -9;
        bArr[124] = (byte) -9;
        bArr[125] = (byte) -9;
        bArr[126] = (byte) -9;
        bArr[127] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[129] = (byte) -9;
        bArr[130] = (byte) -9;
        bArr[131] = (byte) -9;
        bArr[132] = (byte) -9;
        bArr[133] = (byte) -9;
        bArr[134] = (byte) -9;
        bArr[135] = (byte) -9;
        bArr[136] = (byte) -9;
        bArr[137] = (byte) -9;
        bArr[138] = (byte) -9;
        bArr[139] = (byte) -9;
        bArr[140] = (byte) -9;
        bArr[141] = (byte) -9;
        bArr[142] = (byte) -9;
        bArr[143] = (byte) -9;
        bArr[144] = (byte) -9;
        bArr[145] = (byte) -9;
        bArr[146] = (byte) -9;
        bArr[147] = (byte) -9;
        bArr[148] = (byte) -9;
        bArr[149] = (byte) -9;
        bArr[150] = (byte) -9;
        bArr[151] = (byte) -9;
        bArr[152] = (byte) -9;
        bArr[153] = (byte) -9;
        bArr[154] = (byte) -9;
        bArr[155] = (byte) -9;
        bArr[156] = (byte) -9;
        bArr[157] = (byte) -9;
        bArr[158] = (byte) -9;
        bArr[159] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[161] = (byte) -9;
        bArr[162] = (byte) -9;
        bArr[163] = (byte) -9;
        bArr[164] = (byte) -9;
        bArr[165] = (byte) -9;
        bArr[166] = (byte) -9;
        bArr[167] = (byte) -9;
        bArr[168] = (byte) -9;
        bArr[169] = (byte) -9;
        bArr[170] = (byte) -9;
        bArr[171] = (byte) -9;
        bArr[172] = (byte) -9;
        bArr[173] = (byte) -9;
        bArr[174] = (byte) -9;
        bArr[175] = (byte) -9;
        bArr[176] = (byte) -9;
        bArr[177] = (byte) -9;
        bArr[178] = (byte) -9;
        bArr[179] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[181] = (byte) -9;
        bArr[182] = (byte) -9;
        bArr[183] = (byte) -9;
        bArr[184] = (byte) -9;
        bArr[185] = (byte) -9;
        bArr[186] = (byte) -9;
        bArr[187] = (byte) -9;
        bArr[188] = (byte) -9;
        bArr[189] = (byte) -9;
        bArr[190] = (byte) -9;
        bArr[191] = (byte) -9;
        bArr[192] = (byte) -9;
        bArr[193] = (byte) -9;
        bArr[194] = (byte) -9;
        bArr[195] = (byte) -9;
        bArr[196] = (byte) -9;
        bArr[197] = (byte) -9;
        bArr[198] = (byte) -9;
        bArr[199] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[222] = (byte) -9;
        bArr[223] = (byte) -9;
        bArr[224] = (byte) -9;
        bArr[225] = (byte) -9;
        bArr[226] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[229] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[240] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[250] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        _STANDARD_DECODABET = bArr;
        bArr = new byte[256];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = WHITE_SPACE_ENC;
        bArr[10] = WHITE_SPACE_ENC;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = WHITE_SPACE_ENC;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = WHITE_SPACE_ENC;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) -9;
        bArr[44] = (byte) -9;
        bArr[45] = (byte) 62;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) -9;
        bArr[48] = (byte) 52;
        bArr[49] = (byte) 53;
        bArr[50] = (byte) 54;
        bArr[51] = (byte) 55;
        bArr[52] = (byte) 56;
        bArr[53] = (byte) 57;
        bArr[54] = (byte) 58;
        bArr[55] = (byte) 59;
        bArr[56] = (byte) 60;
        bArr[57] = EQUALS_SIGN;
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = EQUALS_SIGN_ENC;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
        bArr[66] = (byte) 1;
        bArr[67] = (byte) 2;
        bArr[68] = (byte) 3;
        bArr[69] = (byte) 4;
        bArr[70] = (byte) 5;
        bArr[71] = (byte) 6;
        bArr[72] = (byte) 7;
        bArr[73] = (byte) 8;
        bArr[74] = (byte) 9;
        bArr[75] = NEW_LINE;
        bArr[MAX_LINE_LENGTH] = (byte) 11;
        bArr[77] = (byte) 12;
        bArr[78] = (byte) 13;
        bArr[79] = (byte) 14;
        bArr[80] = (byte) 15;
        bArr[81] = (byte) 16;
        bArr[82] = (byte) 17;
        bArr[83] = (byte) 18;
        bArr[84] = (byte) 19;
        bArr[85] = (byte) 20;
        bArr[86] = (byte) 21;
        bArr[87] = (byte) 22;
        bArr[88] = (byte) 23;
        bArr[89] = (byte) 24;
        bArr[90] = (byte) 25;
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) 63;
        bArr[96] = (byte) -9;
        bArr[97] = (byte) 26;
        bArr[98] = (byte) 27;
        bArr[99] = (byte) 28;
        bArr[100] = (byte) 29;
        bArr[101] = (byte) 30;
        bArr[102] = (byte) 31;
        bArr[103] = (byte) 32;
        bArr[104] = (byte) 33;
        bArr[105] = (byte) 34;
        bArr[106] = (byte) 35;
        bArr[107] = (byte) 36;
        bArr[108] = (byte) 37;
        bArr[109] = (byte) 38;
        bArr[110] = (byte) 39;
        bArr[111] = (byte) 40;
        bArr[112] = (byte) 41;
        bArr[113] = (byte) 42;
        bArr[114] = (byte) 43;
        bArr[115] = (byte) 44;
        bArr[116] = (byte) 45;
        bArr[117] = (byte) 46;
        bArr[118] = (byte) 47;
        bArr[119] = (byte) 48;
        bArr[120] = (byte) 49;
        bArr[121] = (byte) 50;
        bArr[122] = (byte) 51;
        bArr[123] = (byte) -9;
        bArr[124] = (byte) -9;
        bArr[125] = (byte) -9;
        bArr[126] = (byte) -9;
        bArr[127] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[129] = (byte) -9;
        bArr[130] = (byte) -9;
        bArr[131] = (byte) -9;
        bArr[132] = (byte) -9;
        bArr[133] = (byte) -9;
        bArr[134] = (byte) -9;
        bArr[135] = (byte) -9;
        bArr[136] = (byte) -9;
        bArr[137] = (byte) -9;
        bArr[138] = (byte) -9;
        bArr[139] = (byte) -9;
        bArr[140] = (byte) -9;
        bArr[141] = (byte) -9;
        bArr[142] = (byte) -9;
        bArr[143] = (byte) -9;
        bArr[144] = (byte) -9;
        bArr[145] = (byte) -9;
        bArr[146] = (byte) -9;
        bArr[147] = (byte) -9;
        bArr[148] = (byte) -9;
        bArr[149] = (byte) -9;
        bArr[150] = (byte) -9;
        bArr[151] = (byte) -9;
        bArr[152] = (byte) -9;
        bArr[153] = (byte) -9;
        bArr[154] = (byte) -9;
        bArr[155] = (byte) -9;
        bArr[156] = (byte) -9;
        bArr[157] = (byte) -9;
        bArr[158] = (byte) -9;
        bArr[159] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[161] = (byte) -9;
        bArr[162] = (byte) -9;
        bArr[163] = (byte) -9;
        bArr[164] = (byte) -9;
        bArr[165] = (byte) -9;
        bArr[166] = (byte) -9;
        bArr[167] = (byte) -9;
        bArr[168] = (byte) -9;
        bArr[169] = (byte) -9;
        bArr[170] = (byte) -9;
        bArr[171] = (byte) -9;
        bArr[172] = (byte) -9;
        bArr[173] = (byte) -9;
        bArr[174] = (byte) -9;
        bArr[175] = (byte) -9;
        bArr[176] = (byte) -9;
        bArr[177] = (byte) -9;
        bArr[178] = (byte) -9;
        bArr[179] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[181] = (byte) -9;
        bArr[182] = (byte) -9;
        bArr[183] = (byte) -9;
        bArr[184] = (byte) -9;
        bArr[185] = (byte) -9;
        bArr[186] = (byte) -9;
        bArr[187] = (byte) -9;
        bArr[188] = (byte) -9;
        bArr[189] = (byte) -9;
        bArr[190] = (byte) -9;
        bArr[191] = (byte) -9;
        bArr[192] = (byte) -9;
        bArr[193] = (byte) -9;
        bArr[194] = (byte) -9;
        bArr[195] = (byte) -9;
        bArr[196] = (byte) -9;
        bArr[197] = (byte) -9;
        bArr[198] = (byte) -9;
        bArr[199] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[222] = (byte) -9;
        bArr[223] = (byte) -9;
        bArr[224] = (byte) -9;
        bArr[225] = (byte) -9;
        bArr[226] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[229] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[240] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[250] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        _URL_SAFE_DECODABET = bArr;
        bArr = new byte[257];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = WHITE_SPACE_ENC;
        bArr[10] = WHITE_SPACE_ENC;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = WHITE_SPACE_ENC;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = WHITE_SPACE_ENC;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) -9;
        bArr[44] = (byte) -9;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) -9;
        bArr[48] = (byte) 1;
        bArr[49] = (byte) 2;
        bArr[50] = (byte) 3;
        bArr[51] = (byte) 4;
        bArr[52] = (byte) 5;
        bArr[53] = (byte) 6;
        bArr[54] = (byte) 7;
        bArr[55] = (byte) 8;
        bArr[56] = (byte) 9;
        bArr[57] = NEW_LINE;
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = EQUALS_SIGN_ENC;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
        bArr[65] = (byte) 11;
        bArr[66] = (byte) 12;
        bArr[67] = (byte) 13;
        bArr[68] = (byte) 14;
        bArr[69] = (byte) 15;
        bArr[70] = (byte) 16;
        bArr[71] = (byte) 17;
        bArr[72] = (byte) 18;
        bArr[73] = (byte) 19;
        bArr[74] = (byte) 20;
        bArr[75] = (byte) 21;
        bArr[MAX_LINE_LENGTH] = (byte) 22;
        bArr[77] = (byte) 23;
        bArr[78] = (byte) 24;
        bArr[79] = (byte) 25;
        bArr[80] = (byte) 26;
        bArr[81] = (byte) 27;
        bArr[82] = (byte) 28;
        bArr[83] = (byte) 29;
        bArr[84] = (byte) 30;
        bArr[85] = (byte) 31;
        bArr[86] = (byte) 32;
        bArr[87] = (byte) 33;
        bArr[88] = (byte) 34;
        bArr[89] = (byte) 35;
        bArr[90] = (byte) 36;
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) 37;
        bArr[96] = (byte) -9;
        bArr[97] = (byte) 38;
        bArr[98] = (byte) 39;
        bArr[99] = (byte) 40;
        bArr[100] = (byte) 41;
        bArr[101] = (byte) 42;
        bArr[102] = (byte) 43;
        bArr[103] = (byte) 44;
        bArr[104] = (byte) 45;
        bArr[105] = (byte) 46;
        bArr[106] = (byte) 47;
        bArr[107] = (byte) 48;
        bArr[108] = (byte) 49;
        bArr[109] = (byte) 50;
        bArr[110] = (byte) 51;
        bArr[111] = (byte) 52;
        bArr[112] = (byte) 53;
        bArr[113] = (byte) 54;
        bArr[114] = (byte) 55;
        bArr[115] = (byte) 56;
        bArr[116] = (byte) 57;
        bArr[117] = (byte) 58;
        bArr[118] = (byte) 59;
        bArr[119] = (byte) 60;
        bArr[120] = EQUALS_SIGN;
        bArr[121] = (byte) 62;
        bArr[122] = (byte) 63;
        bArr[123] = (byte) -9;
        bArr[124] = (byte) -9;
        bArr[125] = (byte) -9;
        bArr[126] = (byte) -9;
        bArr[127] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[129] = (byte) -9;
        bArr[130] = (byte) -9;
        bArr[131] = (byte) -9;
        bArr[132] = (byte) -9;
        bArr[133] = (byte) -9;
        bArr[134] = (byte) -9;
        bArr[135] = (byte) -9;
        bArr[136] = (byte) -9;
        bArr[137] = (byte) -9;
        bArr[138] = (byte) -9;
        bArr[139] = (byte) -9;
        bArr[140] = (byte) -9;
        bArr[141] = (byte) -9;
        bArr[142] = (byte) -9;
        bArr[143] = (byte) -9;
        bArr[144] = (byte) -9;
        bArr[145] = (byte) -9;
        bArr[146] = (byte) -9;
        bArr[147] = (byte) -9;
        bArr[148] = (byte) -9;
        bArr[149] = (byte) -9;
        bArr[150] = (byte) -9;
        bArr[151] = (byte) -9;
        bArr[152] = (byte) -9;
        bArr[153] = (byte) -9;
        bArr[154] = (byte) -9;
        bArr[155] = (byte) -9;
        bArr[156] = (byte) -9;
        bArr[157] = (byte) -9;
        bArr[158] = (byte) -9;
        bArr[159] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[161] = (byte) -9;
        bArr[162] = (byte) -9;
        bArr[163] = (byte) -9;
        bArr[164] = (byte) -9;
        bArr[165] = (byte) -9;
        bArr[166] = (byte) -9;
        bArr[167] = (byte) -9;
        bArr[168] = (byte) -9;
        bArr[169] = (byte) -9;
        bArr[170] = (byte) -9;
        bArr[171] = (byte) -9;
        bArr[172] = (byte) -9;
        bArr[173] = (byte) -9;
        bArr[174] = (byte) -9;
        bArr[175] = (byte) -9;
        bArr[176] = (byte) -9;
        bArr[177] = (byte) -9;
        bArr[178] = (byte) -9;
        bArr[179] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[181] = (byte) -9;
        bArr[182] = (byte) -9;
        bArr[183] = (byte) -9;
        bArr[184] = (byte) -9;
        bArr[185] = (byte) -9;
        bArr[186] = (byte) -9;
        bArr[187] = (byte) -9;
        bArr[188] = (byte) -9;
        bArr[189] = (byte) -9;
        bArr[190] = (byte) -9;
        bArr[191] = (byte) -9;
        bArr[192] = (byte) -9;
        bArr[193] = (byte) -9;
        bArr[194] = (byte) -9;
        bArr[195] = (byte) -9;
        bArr[196] = (byte) -9;
        bArr[197] = (byte) -9;
        bArr[198] = (byte) -9;
        bArr[199] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[222] = (byte) -9;
        bArr[223] = (byte) -9;
        bArr[224] = (byte) -9;
        bArr[225] = (byte) -9;
        bArr[226] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[229] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[240] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[250] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        bArr[256] = (byte) -9;
        _ORDERED_DECODABET = bArr;
    }

    private static final byte[] getAlphabet(int options) {
        if ((options & 16) == 16) {
            return _URL_SAFE_ALPHABET;
        }
        if ((options & 32) == 32) {
            return _ORDERED_ALPHABET;
        }
        return _STANDARD_ALPHABET;
    }

    private static final byte[] getDecodabet(int options) {
        if ((options & 16) == 16) {
            return _URL_SAFE_DECODABET;
        }
        if ((options & 32) == 32) {
            return _ORDERED_DECODABET;
        }
        return _STANDARD_DECODABET;
    }

    private Base64() {
    }

    private static byte[] encode3to4(byte[] b4, byte[] threeBytes, int numSigBytes, int options) {
        encode3to4(threeBytes, 0, numSigBytes, b4, 0, options);
        return b4;
    }

    private static byte[] encode3to4(byte[] source, int srcOffset, int numSigBytes, byte[] destination, int destOffset, int options) {
        int i;
        int i2;
        int i3 = 0;
        byte[] ALPHABET = getAlphabet(options);
        if (numSigBytes > 0) {
            i = (source[srcOffset] << 24) >>> 8;
        } else {
            i = 0;
        }
        if (numSigBytes > 1) {
            i2 = (source[srcOffset + 1] << 24) >>> 16;
        } else {
            i2 = 0;
        }
        i2 |= i;
        if (numSigBytes > 2) {
            i3 = (source[srcOffset + 2] << 24) >>> 24;
        }
        int inBuff = i2 | i3;
        switch (numSigBytes) {
            case 1:
                destination[destOffset] = ALPHABET[inBuff >>> 18];
                destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 63];
                destination[destOffset + 2] = EQUALS_SIGN;
                destination[destOffset + 3] = EQUALS_SIGN;
                break;
            case 2:
                destination[destOffset] = ALPHABET[inBuff >>> 18];
                destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 63];
                destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 63];
                destination[destOffset + 3] = EQUALS_SIGN;
                break;
            case 3:
                destination[destOffset] = ALPHABET[inBuff >>> 18];
                destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 63];
                destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 63];
                destination[destOffset + 3] = ALPHABET[inBuff & 63];
                break;
        }
        return destination;
    }

    public static void encode(ByteBuffer raw, ByteBuffer encoded) {
        byte[] raw3 = new byte[3];
        byte[] enc4 = new byte[4];
        while (raw.hasRemaining()) {
            int rem = Math.min(3, raw.remaining());
            raw.get(raw3, 0, rem);
            encode3to4(enc4, raw3, rem, 0);
            encoded.put(enc4);
        }
    }

    public static void encode(ByteBuffer raw, CharBuffer encoded) {
        byte[] raw3 = new byte[3];
        byte[] enc4 = new byte[4];
        while (raw.hasRemaining()) {
            int rem = Math.min(3, raw.remaining());
            raw.get(raw3, 0, rem);
            encode3to4(enc4, raw3, rem, 0);
            for (int i = 0; i < 4; i++) {
                encoded.put((char) (enc4[i] & 255));
            }
        }
    }

    public static String encodeObject(Serializable serializableObject) throws IOException {
        return encodeObject(serializableObject, 0);
    }

    public static String encodeObject(Serializable serializableObject, int options) throws IOException {
        IOException e;
        Throwable th;
        if (serializableObject == null) {
            throw new NullPointerException("Cannot serialize a null object.");
        }
        ByteArrayOutputStream baos = null;
        java.io.OutputStream b64os = null;
        GZIPOutputStream gzos = null;
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            try {
                java.io.OutputStream b64os2 = new OutputStream(baos2, options | 1);
                if ((options & 2) != 0) {
                    GZIPOutputStream gzos2;
                    try {
                        gzos2 = new GZIPOutputStream(b64os2);
                    } catch (IOException e2) {
                        e = e2;
                        b64os = b64os2;
                        baos = baos2;
                        try {
                            throw e;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        b64os = b64os2;
                        baos = baos2;
                        try {
                            oos.close();
                        } catch (Exception e3) {
                        }
                        try {
                            gzos.close();
                        } catch (Exception e4) {
                        }
                        try {
                            b64os.close();
                        } catch (Exception e5) {
                        }
                        try {
                            baos.close();
                        } catch (Exception e6) {
                        }
                        throw th;
                    }
                    try {
                        oos = new ObjectOutputStream(gzos2);
                        gzos = gzos2;
                    } catch (IOException e7) {
                        e = e7;
                        gzos = gzos2;
                        b64os = b64os2;
                        baos = baos2;
                        throw e;
                    } catch (Throwable th4) {
                        th = th4;
                        gzos = gzos2;
                        b64os = b64os2;
                        baos = baos2;
                        oos.close();
                        gzos.close();
                        b64os.close();
                        baos.close();
                        throw th;
                    }
                }
                oos = new ObjectOutputStream(b64os2);
                oos.writeObject(serializableObject);
                try {
                    oos.close();
                } catch (Exception e8) {
                }
                try {
                    gzos.close();
                } catch (Exception e9) {
                }
                try {
                    b64os2.close();
                } catch (Exception e10) {
                }
                try {
                    baos2.close();
                } catch (Exception e11) {
                }
                try {
                    return new String(baos2.toByteArray(), PREFERRED_ENCODING);
                } catch (UnsupportedEncodingException e12) {
                    return new String(baos2.toByteArray());
                }
            } catch (IOException e13) {
                e = e13;
                baos = baos2;
                throw e;
            } catch (Throwable th5) {
                th = th5;
                baos = baos2;
                oos.close();
                gzos.close();
                b64os.close();
                baos.close();
                throw th;
            }
        } catch (IOException e14) {
            e = e14;
            throw e;
        }
    }

    public static String encodeBytes(byte[] source) {
        String encoded = null;
        try {
            encoded = encodeBytes(source, 0, source.length, 0);
        } catch (IOException ex) {
            if (!$assertionsDisabled) {
                throw new AssertionError(ex.getMessage());
            }
        }
        if ($assertionsDisabled || encoded != null) {
            return encoded;
        }
        throw new AssertionError();
    }

    public static String encodeBytes(byte[] source, int options) throws IOException {
        return encodeBytes(source, 0, source.length, options);
    }

    public static String encodeBytes(byte[] source, int off, int len) {
        String encoded = null;
        try {
            encoded = encodeBytes(source, off, len, 0);
        } catch (IOException ex) {
            if (!$assertionsDisabled) {
                throw new AssertionError(ex.getMessage());
            }
        }
        if ($assertionsDisabled || encoded != null) {
            return encoded;
        }
        throw new AssertionError();
    }

    public static String encodeBytes(byte[] source, int off, int len, int options) throws IOException {
        byte[] encoded = encodeBytesToBytes(source, off, len, options);
        try {
            return new String(encoded, PREFERRED_ENCODING);
        } catch (UnsupportedEncodingException e) {
            return new String(encoded);
        }
    }

    public static byte[] encodeBytesToBytes(byte[] source) {
        byte[] encoded = null;
        try {
            return encodeBytesToBytes(source, 0, source.length, 0);
        } catch (IOException ex) {
            if ($assertionsDisabled) {
                return encoded;
            }
            throw new AssertionError("IOExceptions only come from GZipping, which is turned off: " + ex.getMessage());
        }
    }

    public static byte[] encodeBytesToBytes(byte[] source, int off, int len, int options) throws IOException {
        IOException e;
        Throwable th;
        if (source == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        } else if (off < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + off);
        } else if (len < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + len);
        } else if (off + len > source.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[]{Integer.valueOf(off), Integer.valueOf(len), Integer.valueOf(source.length)}));
        } else if ((options & 2) != 0) {
            ByteArrayOutputStream baos = null;
            GZIPOutputStream gzos = null;
            OutputStream b64os = null;
            try {
                OutputStream b64os2;
                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                try {
                    b64os2 = new OutputStream(baos2, options | 1);
                } catch (IOException e2) {
                    e = e2;
                    baos = baos2;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    baos = baos2;
                    try {
                        gzos.close();
                    } catch (Exception e3) {
                    }
                    try {
                        b64os.close();
                    } catch (Exception e4) {
                    }
                    try {
                        baos.close();
                    } catch (Exception e5) {
                    }
                    throw th;
                }
                try {
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(b64os2);
                    try {
                        gZIPOutputStream.write(source, off, len);
                        gZIPOutputStream.close();
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e6) {
                        }
                        try {
                            b64os2.close();
                        } catch (Exception e7) {
                        }
                        try {
                            baos2.close();
                        } catch (Exception e8) {
                        }
                        return baos2.toByteArray();
                    } catch (IOException e9) {
                        e = e9;
                        b64os = b64os2;
                        gzos = gZIPOutputStream;
                        baos = baos2;
                        throw e;
                    } catch (Throwable th4) {
                        th = th4;
                        b64os = b64os2;
                        gzos = gZIPOutputStream;
                        baos = baos2;
                        gzos.close();
                        b64os.close();
                        baos.close();
                        throw th;
                    }
                } catch (IOException e10) {
                    e = e10;
                    b64os = b64os2;
                    baos = baos2;
                    throw e;
                } catch (Throwable th5) {
                    th = th5;
                    b64os = b64os2;
                    baos = baos2;
                    gzos.close();
                    b64os.close();
                    baos.close();
                    throw th;
                }
            } catch (IOException e11) {
                e = e11;
                throw e;
            }
        } else {
            boolean breakLines = (options & 8) != 0 ? true : $assertionsDisabled;
            int encLen = ((len / 3) * 4) + (len % 3 > 0 ? 4 : 0);
            if (breakLines) {
                encLen += encLen / MAX_LINE_LENGTH;
            }
            byte[] outBuff = new byte[encLen];
            int d = 0;
            int e12 = 0;
            int len2 = len - 2;
            int lineLength = 0;
            while (d < len2) {
                encode3to4(source, d + off, 3, outBuff, e12, options);
                lineLength += 4;
                if (breakLines && lineLength >= MAX_LINE_LENGTH) {
                    outBuff[e12 + 4] = NEW_LINE;
                    e12++;
                    lineLength = 0;
                }
                d += 3;
                e12 += 4;
            }
            if (d < len) {
                encode3to4(source, d + off, len - d, outBuff, e12, options);
                e12 += 4;
            }
            if (e12 > outBuff.length - 1) {
                return outBuff;
            }
            Object finalOut = new byte[e12];
            System.arraycopy(outBuff, 0, finalOut, 0, e12);
            return finalOut;
        }
    }

    private static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset, int options) {
        if (source == null) {
            throw new NullPointerException("Source array was null.");
        } else if (destination == null) {
            throw new NullPointerException("Destination array was null.");
        } else if (srcOffset < 0 || srcOffset + 3 >= source.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[]{Integer.valueOf(source.length), Integer.valueOf(srcOffset)}));
        } else if (destOffset < 0 || destOffset + 2 >= destination.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[]{Integer.valueOf(destination.length), Integer.valueOf(destOffset)}));
        } else {
            byte[] DECODABET = getDecodabet(options);
            int outBuff;
            if (source[srcOffset + 2] == EQUALS_SIGN) {
                destination[destOffset] = (byte) ((((DECODABET[source[srcOffset]] & 255) << 18) | ((DECODABET[source[srcOffset + 1]] & 255) << 12)) >>> 16);
                return 1;
            } else if (source[srcOffset + 3] == EQUALS_SIGN) {
                outBuff = (((DECODABET[source[srcOffset]] & 255) << 18) | ((DECODABET[source[srcOffset + 1]] & 255) << 12)) | ((DECODABET[source[srcOffset + 2]] & 255) << 6);
                destination[destOffset] = (byte) (outBuff >>> 16);
                destination[destOffset + 1] = (byte) (outBuff >>> 8);
                return 2;
            } else {
                outBuff = ((((DECODABET[source[srcOffset]] & 255) << 18) | ((DECODABET[source[srcOffset + 1]] & 255) << 12)) | ((DECODABET[source[srcOffset + 2]] & 255) << 6)) | (DECODABET[source[srcOffset + 3]] & 255);
                destination[destOffset] = (byte) (outBuff >> 16);
                destination[destOffset + 1] = (byte) (outBuff >> 8);
                destination[destOffset + 2] = (byte) outBuff;
                return 3;
            }
        }
    }

    public static byte[] decode(byte[] source) throws IOException {
        byte[] decoded = null;
        return decode(source, 0, source.length, 0);
    }

    public static byte[] decode(byte[] source, int off, int len, int options) throws IOException {
        if (source == null) {
            throw new NullPointerException("Cannot decode null source array.");
        } else if (off < 0 || off + len > source.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", new Object[]{Integer.valueOf(source.length), Integer.valueOf(off), Integer.valueOf(len)}));
        } else if (len == 0) {
            return new byte[0];
        } else {
            if (len < 4) {
                throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + len);
            }
            byte[] DECODABET = getDecodabet(options);
            byte[] outBuff = new byte[((len * 3) / 4)];
            int outBuffPosn = 0;
            byte[] b4 = new byte[4];
            int b4Posn = 0;
            int i = off;
            while (true) {
                int b4Posn2 = b4Posn;
                if (i >= off + len) {
                    b4Posn = b4Posn2;
                    break;
                }
                byte sbiDecode = DECODABET[source[i] & 255];
                if (sbiDecode >= WHITE_SPACE_ENC) {
                    if (sbiDecode >= EQUALS_SIGN_ENC) {
                        b4Posn = b4Posn2 + 1;
                        b4[b4Posn2] = source[i];
                        if (b4Posn > 3) {
                            outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn, options);
                            b4Posn = 0;
                            if (source[i] == EQUALS_SIGN) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        b4Posn = b4Posn2;
                    }
                    i++;
                } else {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", new Object[]{Integer.valueOf(source[i] & 255), Integer.valueOf(i)}));
                }
            }
            byte[] out = new byte[outBuffPosn];
            System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
            return out;
        }
    }

    public static byte[] decode(String s) throws IOException {
        return decode(s, 0);
    }

    public static byte[] decode(String s, int options) throws IOException {
        IOException e;
        Throwable th;
        if (s == null) {
            throw new NullPointerException("Input string was null.");
        }
        byte[] bytes;
        try {
            bytes = s.getBytes(PREFERRED_ENCODING);
        } catch (UnsupportedEncodingException e2) {
            bytes = s.getBytes();
        }
        bytes = decode(bytes, 0, bytes.length, options);
        boolean dontGunzip = (options & 4) != 0 ? true : $assertionsDisabled;
        if (bytes != null && bytes.length >= 4 && !dontGunzip && 35615 == ((bytes[0] & 255) | ((bytes[1] << 8) & 65280))) {
            ByteArrayInputStream bais = null;
            GZIPInputStream gzis = null;
            ByteArrayOutputStream baos = null;
            byte[] buffer = new byte[2048];
            try {
                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                try {
                    ByteArrayInputStream bais2 = new ByteArrayInputStream(bytes);
                    try {
                        GZIPInputStream gzis2 = new GZIPInputStream(bais2);
                        while (true) {
                            try {
                                int length = gzis2.read(buffer);
                                if (length < 0) {
                                    break;
                                }
                                baos2.write(buffer, 0, length);
                            } catch (IOException e3) {
                                e = e3;
                                baos = baos2;
                                gzis = gzis2;
                                bais = bais2;
                                try {
                                    e.printStackTrace();
                                    try {
                                        baos.close();
                                    } catch (Exception e4) {
                                    }
                                    try {
                                        gzis.close();
                                    } catch (Exception e5) {
                                    }
                                    try {
                                        bais.close();
                                    } catch (Exception e6) {
                                    }
                                    return bytes;
                                } catch (Throwable th2) {
                                    th = th2;
                                    try {
                                        baos.close();
                                    } catch (Exception e7) {
                                    }
                                    try {
                                        gzis.close();
                                    } catch (Exception e8) {
                                    }
                                    try {
                                        bais.close();
                                    } catch (Exception e9) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                baos = baos2;
                                gzis = gzis2;
                                bais = bais2;
                                baos.close();
                                gzis.close();
                                bais.close();
                                throw th;
                            }
                        }
                        bytes = baos2.toByteArray();
                        try {
                            baos2.close();
                        } catch (Exception e10) {
                        }
                        try {
                            gzis2.close();
                        } catch (Exception e11) {
                        }
                        try {
                            bais2.close();
                        } catch (Exception e12) {
                        }
                    } catch (IOException e13) {
                        e = e13;
                        baos = baos2;
                        bais = bais2;
                        e.printStackTrace();
                        baos.close();
                        gzis.close();
                        bais.close();
                        return bytes;
                    } catch (Throwable th4) {
                        th = th4;
                        baos = baos2;
                        bais = bais2;
                        baos.close();
                        gzis.close();
                        bais.close();
                        throw th;
                    }
                } catch (IOException e14) {
                    e = e14;
                    baos = baos2;
                    e.printStackTrace();
                    baos.close();
                    gzis.close();
                    bais.close();
                    return bytes;
                } catch (Throwable th5) {
                    th = th5;
                    baos = baos2;
                    baos.close();
                    gzis.close();
                    bais.close();
                    throw th;
                }
            } catch (IOException e15) {
                e = e15;
                e.printStackTrace();
                baos.close();
                gzis.close();
                bais.close();
                return bytes;
            }
        }
        return bytes;
    }

    public static Object decodeToObject(String encodedObject) throws IOException, ClassNotFoundException {
        return decodeToObject(encodedObject, 0, null);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x0031=Splitter:B:25:0x0031, B:16:0x0027=Splitter:B:16:0x0027} */
    public static java.lang.Object decodeToObject(java.lang.String r9, int r10, final java.lang.ClassLoader r11) throws java.io.IOException, java.lang.ClassNotFoundException {
        /*
        r4 = decode(r9, r10);
        r0 = 0;
        r5 = 0;
        r3 = 0;
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ IOException -> 0x0026, ClassNotFoundException -> 0x0030 }
        r1.<init>(r4);	 Catch:{ IOException -> 0x0026, ClassNotFoundException -> 0x0030 }
        if (r11 != 0) goto L_0x001f;
    L_0x000e:
        r6 = new java.io.ObjectInputStream;	 Catch:{ IOException -> 0x0040, ClassNotFoundException -> 0x003d, all -> 0x003a }
        r6.<init>(r1);	 Catch:{ IOException -> 0x0040, ClassNotFoundException -> 0x003d, all -> 0x003a }
        r5 = r6;
    L_0x0014:
        r3 = r5.readObject();	 Catch:{ IOException -> 0x0040, ClassNotFoundException -> 0x003d, all -> 0x003a }
        r1.close();	 Catch:{ Exception -> 0x0036 }
    L_0x001b:
        r5.close();	 Catch:{ Exception -> 0x0038 }
    L_0x001e:
        return r3;
    L_0x001f:
        r6 = new com.jalsalabs.ticklemyphonefull.Base64$1;	 Catch:{ IOException -> 0x0040, ClassNotFoundException -> 0x003d, all -> 0x003a }
        r6.<init>(r1, r11);	 Catch:{ IOException -> 0x0040, ClassNotFoundException -> 0x003d, all -> 0x003a }
        r5 = r6;
        goto L_0x0014;
    L_0x0026:
        r2 = move-exception;
    L_0x0027:
        throw r2;	 Catch:{ all -> 0x0028 }
    L_0x0028:
        r7 = move-exception;
    L_0x0029:
        r0.close();	 Catch:{ Exception -> 0x0032 }
    L_0x002c:
        r5.close();	 Catch:{ Exception -> 0x0034 }
    L_0x002f:
        throw r7;
    L_0x0030:
        r2 = move-exception;
    L_0x0031:
        throw r2;	 Catch:{ all -> 0x0028 }
    L_0x0032:
        r8 = move-exception;
        goto L_0x002c;
    L_0x0034:
        r8 = move-exception;
        goto L_0x002f;
    L_0x0036:
        r7 = move-exception;
        goto L_0x001b;
    L_0x0038:
        r7 = move-exception;
        goto L_0x001e;
    L_0x003a:
        r7 = move-exception;
        r0 = r1;
        goto L_0x0029;
    L_0x003d:
        r2 = move-exception;
        r0 = r1;
        goto L_0x0031;
    L_0x0040:
        r2 = move-exception;
        r0 = r1;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jalsalabs.ticklemyphonefull.Base64.decodeToObject(java.lang.String, int, java.lang.ClassLoader):java.lang.Object");
    }

    public static void encodeToFile(byte[] dataToEncode, String filename) throws IOException {
        IOException e;
        Throwable th;
        if (dataToEncode == null) {
            throw new NullPointerException("Data to encode was null.");
        }
        OutputStream bos = null;
        try {
            OutputStream bos2 = new OutputStream(new FileOutputStream(filename), 1);
            try {
                bos2.write(dataToEncode);
                try {
                    bos2.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                bos = bos2;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                bos = bos2;
                try {
                    bos.close();
                } catch (Exception e4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            throw e;
        }
    }

    public static void decodeToFile(String dataToDecode, String filename) throws IOException {
        IOException e;
        Throwable th;
        OutputStream bos = null;
        try {
            OutputStream bos2 = new OutputStream(new FileOutputStream(filename), 0);
            try {
                bos2.write(dataToDecode.getBytes(PREFERRED_ENCODING));
                try {
                    bos2.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                bos = bos2;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                bos = bos2;
                try {
                    bos.close();
                } catch (Exception e4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            throw e;
        }
    }

    public static byte[] decodeFromFile(String filename) throws IOException {
        IOException e;
        Throwable th;
        byte[] decodedData = null;
        InputStream bis = null;
        try {
            File file = new File(filename);
            byte[] buffer = null;
            int length = 0;
            if (file.length() > 2147483647L) {
                throw new IOException("File is too big for this convenience method (" + file.length() + " bytes).");
            }
            buffer = new byte[((int) file.length())];
            InputStream bis2 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0);
            while (true) {
                try {
                    int numBytes = bis2.read(buffer, length, 4096);
                    if (numBytes < 0) {
                        break;
                    }
                    length += numBytes;
                } catch (IOException e2) {
                    e = e2;
                    bis = bis2;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bis = bis2;
                    try {
                        bis.close();
                    } catch (Exception e3) {
                    }
                    throw th;
                }
            }
            decodedData = new byte[length];
            System.arraycopy(buffer, 0, decodedData, 0, length);
            try {
                bis2.close();
            } catch (Exception e4) {
            }
            return decodedData;
        } catch (IOException e5) {
            e = e5;
            throw e;
        }
    }

    public static String encodeFromFile(String filename) throws IOException {
        IOException e;
        Throwable th;
        InputStream bis = null;
        try {
            File file = new File(filename);
            byte[] buffer = new byte[Math.max((int) ((((double) file.length()) * 1.4d) + 1.0d), 40)];
            int length = 0;
            InputStream bis2 = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1);
            while (true) {
                try {
                    int numBytes = bis2.read(buffer, length, 4096);
                    if (numBytes < 0) {
                        break;
                    }
                    length += numBytes;
                } catch (IOException e2) {
                    e = e2;
                    bis = bis2;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bis = bis2;
                    try {
                        bis.close();
                    } catch (Exception e3) {
                    }
                    throw th;
                }
            }
            String encodedData = new String(buffer, 0, length, PREFERRED_ENCODING);
            try {
                bis2.close();
            } catch (Exception e4) {
            }
            return encodedData;
        } catch (IOException e5) {
            e = e5;
            throw e;
        }
    }

    public static void encodeFileToFile(String infile, String outfile) throws IOException {
        IOException e;
        Throwable th;
        String encoded = encodeFromFile(infile);
        java.io.OutputStream out = null;
        try {
            java.io.OutputStream out2 = new BufferedOutputStream(new FileOutputStream(outfile));
            try {
                out2.write(encoded.getBytes(PREFERRED_ENCODING));
                try {
                    out2.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                out = out2;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                out = out2;
                try {
                    out.close();
                } catch (Exception e4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            throw e;
        }
    }

    public static void decodeFileToFile(String infile, String outfile) throws IOException {
        IOException e;
        Throwable th;
        byte[] decoded = decodeFromFile(infile);
        java.io.OutputStream out = null;
        try {
            java.io.OutputStream out2 = new BufferedOutputStream(new FileOutputStream(outfile));
            try {
                out2.write(decoded);
                try {
                    out2.close();
                } catch (Exception e2) {
                }
            } catch (IOException e3) {
                e = e3;
                out = out2;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                out = out2;
                try {
                    out.close();
                } catch (Exception e4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            throw e;
        }
    }
}
