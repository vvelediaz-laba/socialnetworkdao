package com.solvd.socialnetworkdao.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;

public class ByteBlobConverter {
    private static final Logger logger = LogManager.getLogger(ByteBlobConverter.class);

    public static Blob convertToBlob(byte[] bytes) {
        try {
            return new SerialBlob(bytes);
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public static byte[] convertToBytes(Blob blob) {
        if (blob == null) {
            return null;
        }

        int blobLength = 0;
        try {
            blobLength = (int) blob.length();
            byte[] bytes = blob.getBytes(1, blobLength);
            blob.free();
            return bytes;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }
}

