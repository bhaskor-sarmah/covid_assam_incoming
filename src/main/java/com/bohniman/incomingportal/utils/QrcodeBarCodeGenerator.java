package com.bohniman.incomingportal.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QrcodeBarCodeGenerator {

    Logger logger = LoggerFactory.getLogger(QrcodeBarCodeGenerator.class);

    private String text;
    private CharType charType = CharType.UTF_8;
    private int size = 125;
    private String imageType = "PNG";

    private QrcodeBarCodeGenerator(String text) {
        this.text = text;
    }

    public static QrcodeBarCodeGenerator from(String text) {
        return new QrcodeBarCodeGenerator(text);
    }

    public QrcodeBarCodeGenerator withSize(int size) {
        this.size = size;
        return this;
    }

    public QrcodeBarCodeGenerator toImageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    public QrcodeBarCodeGenerator withCharType(CharType charType) {
        this.charType = charType;
        return this;
    }

    public enum ImageType {

        PNG {
            public String toString() {
                return "png";
            }
        },
        JPG {
            public String toString() {
                return "jpg";
            }
        },
        GIF {
            public String toString() {
                return "gif";
            }
        }
    }

    public enum CharType {

        UTF_8 {
            public String toString() {
                return "UTF-8";
            }
        },
        ASCII {
            public String toString() {
                return "ASCII";
            }
        },
        ISO_8859_1 {
            public String toString() {
                return "ISO 8859-1";
            }
        },
        UNICODE {
            public String toString() {
                return "UNICODE";
            }
        }
    }

    public byte[] generateQrCodeByteArray() {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, charType.toString());
        hints.put(EncodeHintType.MARGIN, 0);

        try {

            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hints);

            return getByteArrayFromBitMatrix(bitMatrix);

        } catch (Exception e) {
            logger.error("generateQrCodeByteArray", e);
        }

        return null;

    }

    public byte[] genBarCode() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, charType.toString());
        hints.put(EncodeHintType.MARGIN, 0);
        try {

            BitMatrix bitMatrix = new Code128Writer().encode(text, BarcodeFormat.CODE_128, 250, 100, hints);

            return getByteArrayFromBitMatrix(bitMatrix);

        } catch (Exception e) {

            logger.error("genBarCode", e);

        }

        return stream.toByteArray();
    }

    public byte[] getByteArrayFromBitMatrix(BitMatrix bitMatrix) {

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int matrixWidth = bitMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        try {

            ImageIO.write(image, imageType, bao);

            return bao.toByteArray();

        } catch (IOException e) {

            logger.error("getByteArrayFromBitMatrix", e);

        }

        return null;

    }

}