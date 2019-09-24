package com.zk.sfservice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStringUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileStringUtil.class);
	
	public static StringBuilder readFileByIn(InputStream instream) {
        StringBuilder sb = new StringBuilder();
        try {
            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                // 分行读取
                while ((line = buffreader.readLine()) != null) {
                    sb.append(line);
                }
                instream.close();
            }
        } catch (java.io.FileNotFoundException e) {
            logger.info("TestFile", "The File doesn't not exist.");
        } catch (IOException e) {
            logger.info("TestFile", e.getMessage());
        }
        return sb;
    }
}
