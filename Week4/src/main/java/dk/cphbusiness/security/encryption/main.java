/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.security.encryption;

import java.nio.file.Paths;

/**
 *
 * @author runin
 */
public class main {
    private static final String PATH = Paths.get("").toAbsolutePath().toString() + "\\output\\";
    
    public static void main(String[] args) {
        System.out.println(PATH);
    }
}
