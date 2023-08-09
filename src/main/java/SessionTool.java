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


import java.io.*;
import java.util.*;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;


/**
 *  uses disk files to store a session
 **/
public class SessionTool
{

    public static String readFromFile(String fileName) throws IOException
    {
        Objects.nonNull(fileName);
        String data = null;
        try {
            data = new String(readAllBytes(get(fileName)));
        } catch (IOException e) {
            throw e;
        }

        return data;
    }


    private static String getStringFromProperties(String key, String default_value)
    {
        Properties systemProperties = System.getProperties();
        String value = systemProperties.getProperty(key);
        if(value == null) {
            value = default_value;
        }
        return value;
    }


    // writes session file
    public static void registerSession(String token, String session_payload) throws Exception
    {
        File file = new File(getStringFromProperties("jettpack.session.dir", "cache/session") + "/"+ token);
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(session_payload);
        fileWriter.flush();
        fileWriter.close();
    }


    public static Boolean hasSession(String token) throws Exception
    {
        File f = new File(getStringFromProperties("jettpack.session.dir", "cache/session") + "/"+ token);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }


    public static int getUserId(String token) throws Exception
    {
        int user_id = 0;

        File f = new File(getStringFromProperties("jettpack.session.dir", "cache/session") + "/"+ token);
        if(f.exists() && !f.isDirectory()) {
            user_id = Integer.valueOf(readFromFile(getStringFromProperties("jettpack.session.dir", "cache/session") + "/"+ token));
        }

        return user_id;
    }

    public static Boolean invalidateSession(String token) throws Exception
    {
        File file = new File(getStringFromProperties("jettpack.session.dir", "cache/session") + "/"+ token);
        if(file.delete())
        {
            return true;
        } else {
            return false;
        }
    }


}
