/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Version servlet
 */
@WebServlet("/version")
public class VersionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check Manifest file based on generated build number
        Manifest manifest = new Manifest();
        manifest.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/MANIFEST.MF"));

        Attributes atts = manifest.getMainAttributes();

        PrintWriter writer = response.getWriter();
        writer.println(String.format("Implementation-Versio:'%s';", atts.getValue("Implementation-Version")));
        writer.println(String.format("Implementation-Build:'%s';", atts.getValue("Implementation-Build")));
    }

}
