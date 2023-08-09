/**
  Copyright (c) 2019 Wolfgang Hauptfleisch <dev@augmentedlogic.com>

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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;

import org.eclipse.jetty.server.Request;

public class RequestTool
{


    public static final int FILTER_API_METHOD = 1;
    public static final int FILTER_NAME = 2;
    public static final int FILTER_EMAIL = 3;
    private Request request = null;


    public RequestTool(Request request)
    {
        this.request = request;
    }


    //
    private String filterApiMethod(String m)
    {
        if(m !=null) {
            m = m.trim();

            if(m.length() > 32) {
                m = m.substring(0, 32);
            }
            if(m.matches("[a-zA-Z0-9]*")) {
                return m;
            } else {
                return null;
            }
        }
        return m;
    }


    /**
     *  trims a string without throwing an exception if string is null
     **/
    public static String safeTrim(String input)
    {
        if (input != null) {
            input = input.trim();
        }
        return input;
    }



    /**
     *  removed all html from a string
     *  if string is null, null is returned
     **/
    public static String noHTML(String html_string)
    {
        String no_html_string = null;
        if (html_string != null) {
            no_html_string = html_string.replaceAll("(\\<.*?\\>|&lt;.*?&gt;)", "").trim();
        }
        return no_html_string;
    }



    public static String alphanumeric(String input)
    {
        if (input != null) {
            input = input.replaceAll("[^A-Za-z0-9]", "");
        } else {
            input = "";
        }
        return input;
    }


    /**
     *  test method
     **/
    public static boolean isWithinLengthLimit(String input, int min, int max) {
        if(input != null) {
            int l = input.length();
            if(l >= min && l <=max ) {
                return true;
            }
        }
        return false;
    }


    /**
     *  test method
     **/
    public static boolean isWithinRange(int n, int min, int max) {
        if(n >= min && n <=max ) {
            return true;
        }
        return false;
    }



    /**
     *   main methods to extract parameters from request
     **/


    /**
     * get an integer
     * returns default value if no integer can be parsed from input parameter
     *
     **/
    public int getInt(String param, int default_value)
    {
        if(this.request.getParameter(param) !=null) {
            return Integer.parseInt(this.request.getParameter(param));
        } else {
            return default_value;
        }
    }


    //public Integer getInt(String param, int default_value)
    //{
    //    return StringParser.parseIntegerWithDefault(this.request.getParameter(param), default_value);
    //}

    public Integer getInteger(String param, int default_value)
    {
        return StringParser.parseIntegerWithDefault(this.request.getParameter(param), default_value);
    }

    public Double getDouble(String param, double default_value)
    {
        return StringParser.parseDoubleWithDefault(this.request.getParameter(param), default_value);
    }





    public Integer getIntWithDefault(String param, int default_value)
    {
        return StringParser.parseIntegerWithDefault(this.request.getParameter(param), default_value);
    }

    public Integer getIntegerWithDefault(String param, int default_value)
    {
        return StringParser.parseIntegerWithDefault(this.request.getParameter(param), default_value);
    }

    public Double getDoubleWithDefault(String param, double default_value)
    {
        return StringParser.parseDoubleWithDefault(this.request.getParameter(param), default_value);
    }



    /**
     * get an integer within a given range
     * returns default value if no integer can be parsed or if integer is outside range
     *
     **/
    public int getIntWithinRange(String param, int min, int max, int default_value)
    {
        if(this.request.getParameter(param) !=null) {
            int n = Integer.parseInt(this.request.getParameter(param));
            if(n >= min && n <=max ) {
                return n;
            } else {
                return default_value;
            }
        } else {
            return default_value;
        }

    }


    /**
     *  from request
     *  returns a string from input parameter.
     *  if no string can be parsed , default value is returned
     *  if string is longer than maxlength, string will be shortened
     *  all html is removed by default
     **/
    public String getStringWithFilterAndDefault(String param, String default_value, String... filter)
    {
        String v = this.request.getParameter(param);
        if(v !=null) {
            return RequestTool.noHTML(v.trim());
        } else {
            return default_value;
        }
    }


    /**
     *   from request
     **/
    public String getString(String param, String default_value)
    {
        String v = this.request.getParameter(param);
        if(v !=null) {
            return RequestTool.noHTML(v.trim());
        } else {
            return default_value;
        }
    }

    /**
     *   from request DEPRECATED
     **/
    public String getStringWithDefault(String param, String default_value)
    {
        String v = this.request.getParameter(param);
        if(v !=null) {
            return RequestTool.noHTML(v.trim());
        } else {
            return default_value;
        }
    }


    /**
     * from request
     * get a string from request
     * if string is null returns null
     **/
    public String getString(String param)
    {
        String v = this.request.getParameter(param);
        if(v !=null) {
            return RequestTool.noHTML(v.trim());
        } else {
            return v;
        }
    }


    public String getRawString(String param, String default_value)
    {
        String v = this.request.getParameter(param);
        if(v !=null) {
            return v.trim();
        } else {
            return default_value;
        }
    }


    /**
     * from request
     * get a string from request
     * returns default_value if null DEPRECATED
     **/
    public String getRawStringWithDefault(String param, String default_value)
    {
        String v = this.request.getParameter(param);
        if(v !=null) {
            return v.trim();
        } else {
            return default_value;
        }
    }

}

