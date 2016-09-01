/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

package io.xplore.assets.servlet;

import io.xplore.assets.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Configuration servlet - used to inject parameters to the front end Single Page Application
 */
@WebServlet("/config")
public class ConfigServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();

        try {
            URL url = new URL(request.getRequestURL().toString());
            String defaultApiUrl = String.format("%s://%s/api/v0", url.getProtocol(), url.getAuthority());


            // Get the base API url from configuration
            String api_url = System.getProperty("api.server.url", defaultApiUrl);

            String status = "ACTIVE";
            String email = "motty.cohen@gmail.com";

            // Calculate gravatar
            String hash = MD5Util.md5Hex(email);
            String image = String.format("http://www.gravatar.com/avatar/%s?s=32", hash);

            String tokenStr = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0IiwiaWF0IjoxNDYwNTM2MjU5LCJzdWIiOiJjdXN0b21lciIsImlzcyI6IjIifQ.cHh9uW2YVszpFmvlsgHAUZMiRzXymme_RAQ9Py5lsvQ";
            String fullName = "Maccabi";

            // Create the JS code
            writer.println(String.format("var token='%s';", tokenStr));
            writer.println(String.format("var user_name='%s';", fullName));
            writer.println(String.format("var user_img='%s';", image));
            writer.println(String.format("var api_base_url='%s';", api_url));
            writer.println(String.format("var account_status='%s';", status));
            writer.close();
        } catch (Exception ex) {
            writer.close();
        }
    }

}
