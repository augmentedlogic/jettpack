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

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.net.URI;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Scanner;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;



public class SimpleAssetHandler extends AbstractHandler
{

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {

        URL aURL = new URL(baseRequest.getRequestURL().toString());

        if(new File("." + aURL.getFile()).isFile()) {

            String mimetype = Files.probeContentType(new File("." + aURL.getFile()).toPath());
            response.setHeader("Cache-control", "max-age=36000");
            response.setHeader("Access-Control-Allow-Origin", "*");
            Path p = FileSystems.getDefault().getPath("", "." + aURL.getFile() );
            byte [] fileData = Files.readAllBytes(p);
            response.setContentType(mimetype);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentLength(fileData.length);
            response.getOutputStream().write(fileData);
        } else {
            response.setHeader("Cache-control", "max-age=36000");
            response.setHeader("Access-Control-Allow-Origin", "*");
            String text = "404 - File Not Found";
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println(text);
        }

        baseRequest.setHandled(true);
    }

}
