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

import org.eclipse.jetty.server.Request;


public class UrlTool
{

    private String path = null;

    // TODO: work from request?
    public UrlTool(Request request)
    {
        this.path  = request.getPathInfo();
    }


    public String getElement(int position)
    {
        String element = null;
        String[] parts = this.path.split("/");
        if(parts.length >= position + 1) {
            element = parts[position];
        }
        return element;
    }


    public String getElement(int position, String default_value)
    {
        String element = this.getElement(position);
        if(element == null) {
            element = default_value;
        }
        return element;
    }


    // DEPRECATED
    public static String getPathFragment(String path, int position)
    {
        String result = null;
        String[] parts = path.split("/");
        if(parts.length >= position + 1) {
            result = parts[position];
        }
        return result;
    }

    // DEPRECATED
    public static String getPathFragmentWithDefault(String path, int position, String default_value)
    {
        String result = UrlTool.getPathFragment(path, position);
        if(result == null) {
            result = default_value;
        }
        return result;
    }

}

