/**
  Copyright (c) 2019 Wolfgang Hauptfleisch <wh@augmentedlogic.com>

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
 **/
package com.augmentedlogic.jettpack;

import java.security.SecureRandom;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.Format;
import java.text.DateFormat;

public class MimeTool {

    public static String getMimetype(String filename) {

        String extension = "";
        String mimetype = "";

        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i+1);
        }

        switch ( extension ) {
            case "css":
                mimetype = "text/css";
                break;
            case "js":
                mimetype = "application/javascript";
                break;
            case "json":
                mimetype = "application/json";
                break;
            case "png":
                mimetype = "image/png";
                break;

            case "svg":
                mimetype = "image/svg+xml";
                break;

            case "ttf":
                mimetype = "application/x-font-ttf";
                break;

            case "otf":
                mimetype = "application/x-font-opentype";
                break;

            case "woff":
                mimetype = "application/font-woff";
                break;

            case "woff2":
                mimetype = "application/font-woff2";
                break;

            case "eot":
                mimetype = "application/vnd.ms-fontobject";
                break;

            case "sfnt":
                mimetype = "application/font-sfnt";
                break;

            default:
                mimetype = "text/html";

        }

        return mimetype;
    }




}
